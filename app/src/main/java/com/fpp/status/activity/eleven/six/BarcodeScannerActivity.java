package com.fpp.status.activity.eleven.six;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.utils.BarcodeScannerUtils;
import com.fpp.status.utils.LogUtil;

import java.util.Iterator;
import java.util.Map;

public class BarcodeScannerActivity extends AppCompatActivity {
    TextView tv;
    StringBuffer sb = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);
        tv = findViewById(R.id.tv_aty_barcode_scanner);
        findViewById(R.id.btn_aty_barcode_scanner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.delete(0, sb.length());
                tv.setText("");
            }
        });

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        UsbManager usbManager = (UsbManager) getSystemService(USB_SERVICE);
        Map<String, UsbDevice> usbDeviceMap = usbManager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = usbDeviceMap.values().iterator();
        while (deviceIterator.hasNext()) {
            UsbDevice usbDevice = deviceIterator.next();
            LogUtil.e("vid=" + usbDevice.getVendorId() + "---pid=" + usbDevice.getProductId() + "\n" + usbDevice.toString());
            LogUtil.e("vid=" + usbDevice.toString());
            LogUtil.e("vid=" + usbDevice.getInterface(0).getEndpoint(0).toString());
            //if (mVendorID == usb.getVendorId() && mProductID == usb.getProductId()) {//找到指定设备
        }

        // 判断输入设备类型是keyboard
        if (event.getDevice().getSources() == 0x101) {
            // 条码扫码枪内容获取
            BarcodeScannerUtils.getInstance().analysisKeyEvent(event, new BarcodeScannerUtils.OnScanSuccessListener() {
                @Override
                public void onSuccess(String barcode) {
                    if (TextUtils.isEmpty(barcode)) {
                        LogUtil.e("barcode = " + "读取失败");
                        return;
                    }
                    LogUtil.e("扫码成功 barcode = " + barcode);
                    sb.append(barcode + "\n");
                    tv.setText(sb.toString());
                }
            });
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

}
