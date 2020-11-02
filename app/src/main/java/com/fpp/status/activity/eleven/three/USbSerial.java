package com.fpp.status.activity.eleven.three;

import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;


import com.fpp.status.utils.ByteUtil;
import com.fpp.status.utils.LogUtil;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import com.hoho.android.usbserial.util.SerialInputOutputManager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class USbSerial  {

    public static final String TAG = USbSerial.class.getSimpleName();
    public static UsbSerialPort sPort;
    private SerialInputOutputManager mSerialIoManager;
    private byte[] reciveData;
    private String recData = "";
    private boolean isOpen = false;
    private final ExecutorService mExecutor = Executors.newSingleThreadExecutor();

    public boolean isOpen() {
        return isOpen;
    }

    private SerialInputOutputManager.Listener lister = new SerialInputOutputManager.Listener() {

        @Override
        public void onRunError(Exception e) {
            LogUtil.d(TAG + "  Runner stopped.");
            close();
        }

        @Override
        public void onNewData(final byte[] data) {
            LogUtil.d(TAG + "  recData = " + ByteUtil.byte2HexStr(data));
            if (recData != null) {
                recData = recData + ByteUtil.byte2HexStr(data);
            } else {
                recData = ByteUtil.byte2HexStr(data);
            }
            if (recData.length() < 6) {
                return;
            }
            int len = Integer.parseInt(recData.substring(4, 6), 16);
            if (recData.length() < len * 2) {
                return;
            }
            reciveData = ByteUtil.hexStr(recData);
            recData = "";
        }
    };


    public USbSerial(Context context) {
        UsbManager mUsbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> drivers = UsbSerialProber.getDefaultProber().findAllDrivers(mUsbManager);
        if (drivers != null && drivers.size() > 0) {
            for (UsbSerialDriver driver : drivers) {
//                if ("/dev/bus/usb/001/004".equals(driver.getDevice().getSerialNumber())) {
                if ("FTBD2V3C".equals(driver.getDevice().getSerialNumber())) {
                    LogUtil.e(TAG + "  driver size:" + drivers.size());
                    List<UsbSerialPort> ports = driver.getPorts();
                    if (ports != null && ports.size() > 0) {
                        for (UsbSerialPort port : ports) {
                            LogUtil.e(TAG + "  ports size:" + ports.size());
                            sPort = port;
                            isOpen = open(mUsbManager);
                            LogUtil.e(TAG + " : " + isOpen);
                        }
                    }
                }
            }
        }
    }

    public boolean close() {
        try {
            sPort.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean open(UsbManager mUsbManager) {
        UsbDeviceConnection connection = mUsbManager.openDevice(sPort.getDriver().getDevice());
        if (connection != null) {
            try {
                sPort.open(connection);
                sPort.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
            } catch (IOException e) {
                LogUtil.e(TAG + "  Error setting up device: " + e.getMessage() + e);
                try {
                    sPort.close();
                } catch (IOException e2) {
                }
            }
            onDeviceStateChange();
            return true;
        }
        return false;
    }

    private void onDeviceStateChange() {
        stopIoManager();
        startIoManager();
    }

    private void stopIoManager() {
        if (mSerialIoManager != null) {
            LogUtil.e(TAG + "  Stopping io manager ..");
            mSerialIoManager.stop();
            mSerialIoManager = null;
        }
    }

    private void startIoManager() {
        if (sPort != null) {
            LogUtil.e(TAG + "  Starting io manager ..");
            mSerialIoManager = new SerialInputOutputManager(sPort, lister);
            mExecutor.submit(mSerialIoManager);
        }
    }



    public int[] SendData(int[] ints) {
        reciveData = null;
        sendApdu(ints);
        while (reciveData == null) {
            sleep();
            LogUtil.e("USBS", "reciveData is null");
        }
        int[] reData = new int[reciveData.length];
        for (int i = 0; i < reciveData.length; i++) {
            reData[i] = reciveData[i];
            if (reData[i] < 0) {
                reData[i] = 256 + reData[i];
            }
        }
        return reData;
//        return new int[0];
    }


    public void SendData_NoAnswer(int[] ints) {
        sendApdu(ints);
    }

    private void sendApdu(int[] inData) {
        byte[] data = new byte[inData.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) inData[i];
        }
        LogUtil.e(TAG + "  SEND:" + ByteUtil.byte2HexStr(data));
        mSerialIoManager.writeAsync(data);
    }



    public int[] SendData(int[] ints, int i) {
        return SendData(ints);
    }

    protected static void sleep() {
        try {
            Thread.sleep(200L);
        } catch (InterruptedException var1) {
            var1.printStackTrace();
        }
    }
}
