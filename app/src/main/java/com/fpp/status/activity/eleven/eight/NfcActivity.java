package com.fpp.status.activity.eleven.eight;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fpp.status.R;
import com.fpp.status.http.http.CallBackUtil;
import com.fpp.status.http.http.UrlHttpUtil;
import com.fpp.status.nfc.NFC;
import com.fpp.status.utils.Constant;
import com.fpp.status.utils.DateTimeUtil;
import com.fpp.status.utils.EncryptUtil;
import com.fpp.status.utils.LogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import retrofit2.http.Url;

public class NfcActivity extends AppCompatActivity {


    @BindView(R.id.et_aty_nfc)
    EditText etAtyNfc;
    @BindView(R.id.btn_aty_nfc)
    Button btnAtyNfc;
    @BindView(R.id.tv_aty_nfc_log)
    TextView tvAtyNfcLog;
    @BindView(R.id.btn_aty_nfc_5202)
    Button btnAtyNfc5202;
    @BindView(R.id.btn_aty_nfc_5302)
    Button btnAtyNfc5302;
    @BindView(R.id.btn_aty_nfc_5402)
    Button btnAtyNfc5402;
    @BindView(R.id.btn_aty_nfc_5502)
    Button btnAtyNfc5502;
    @BindView(R.id.et_aty_nfc_2)
    EditText etAtyNfc2;
    private NfcAdapter nfcAdapter;
    private String[][] mTechLists;
    private PendingIntent pendingIntent;
    private IntentFilter[] mWriteTagFilters;

    public static final int VIEW_HOME = 3001;
    public static final int VIEW_HINT = 300201;
    public static final int VIEW_HINT_REMOVE_CARD = 300202;
    public static final int VIEW_FAIL = 300301;
    public static final int VIEW_FAIL_REMOVE_CARD = 300302;
    public static final int VIEW_SUCCESS = 300401;
    public static final int VIEW_SUCCESS_REMOVE_CARD = 300402;
    public static final int VIEW_LOADING = 300501;
    public static final int VIEW_LOADING_REMOVE_CARD = 300502;
    public static final int VIEW_CARD_INFO = 3006;
    public static final int QUERY_ORDER = 3007;
    public static final int YEAR_CHECK = 3008;

    public static String HTTP_2012 = "2012";            // 查询订单
    public static String HTTP_2061 = "2061";            // 获取圈存mac2
    public static String HTTP_2062 = "2062";            // 写卡确认or冲正
    private CardInfo mCardInfo;
    private String mBalance;
    private NFC nfc;
    private StringBuffer sb = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        ButterKnife.bind(this);
        initNFC();
    }


    private void initNFC() {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            // TODO: 2019/12/11 0011  "当前设备不支持NFC功能"
        } else if (!nfcAdapter.isEnabled()) {
            // TODO: 2019/12/11 0011  "NFC功能未打开，请先开启后重试！"
            Intent setNfc = new Intent(Settings.ACTION_NFC_SETTINGS);
            startActivity(setNfc);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 允许扫描的标签类型
        mTechLists = new String[][]{
                new String[]{MifareClassic.class.getName()},                    // M1
                new String[]{IsoDep.class.getName(), NfcA.class.getName()}      // CPU
//                ,new String[]{MifareUltralight.class.getName(), Ndef.class.getName(), NdefFormatable.class.getName(), NfcB.class.getName(), NfcBarcode.class.getName(), NfcF.class.getName(), NfcV.class.getName()}
        };
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        ndef.addCategory("*/*");

        // 允许扫描的标签类型
        mWriteTagFilters = new IntentFilter[]{ndef};

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, mWriteTagFilters, mTechLists);
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
    }


    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        showView(VIEW_LOADING, 0, "读卡中，请勿移卡...", "", "");
        //1.获取Tag对象
        Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        // 获取标签id数组
        byte[] bytesId = detectedTag.getId();
        LogUtil.e("readcard 001 bytesId = " + Arrays.toString(bytesId));

        for (String tag : detectedTag.getTechList()) {
            LogUtil.e("NFC READ 002 tag =  " + tag);
        }

        MifareClassic mfc = MifareClassic.get(detectedTag);
//        if (mfc != null) {      // M1卡
//            String rs = readTag(detectedTag, mfc, 1);
//            LogUtil.e("readcard 004 M1 mfc = " + rs);
//            showView(VIEW_HINT, 0, "不支持的卡类型", "", "");
//            mHandler.sendEmptyMessageDelayed(VIEW_HOME, 2000);
//        } else {                // CPU卡
        LogUtil.e("readcard 003 CPU detectedTag = " + detectedTag.toString());
        try {
            int type = 0;
            switch (type) {
                case 0:
                    NFCChongQing(detectedTag);
                    break;
                case 1:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            showView(VIEW_FAIL, 0, e.getMessage(), "", "");
            mHandler.sendEmptyMessageDelayed(VIEW_HOME, 2000);
        }
//        }

        // TODO: 2020/2/19 test
        String action = intent.getAction();
        if (action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
            NdefMessage[] ndefMessages = null;
            ndefMessages = (NdefMessage[]) intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            Tag detectedTag2 = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String[] techList = detectedTag.getTechList();
            for (String tech : techList) {
                if (tech.equals(NfcF.class.getName())) {
                    try {
                        // tag支持NfcF
                        // 创建NfcF对象与该Tag交互
                        NfcF nfcF = NfcF.get(detectedTag2);
                        nfcF.connect();  // 向目标Tag发起I/O操作前需连接
                        // TODO: 2020/2/19 调用NfcF类的其它函数  发送命令等
                        nfcF.close();
                    } catch (IOException e) {

                    }
                }
            }
        }
    }

    // TODO: 2020/2/18 重庆
    private void NFCChongQing(Tag tag) {
        try {
            nfc = new NFC(IsoDep.get(tag));
        } catch (Exception e) {
            e.printStackTrace();
            showView(VIEW_HINT, 0, "读卡失败，请重新贴卡", "", "");
            mHandler.sendEmptyMessageDelayed(VIEW_HOME, 2000);
        }
    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case VIEW_HOME:
                    showView(VIEW_HOME, 0, "", "", "");
                    break;
                case 0:
                    ValDateApplyResp valDateApplyResp = (ValDateApplyResp) msg.obj;
                    String resp = sendApdu("0082008308" + valDateApplyResp.getEnrndnumber1(), true);
                    if (!TextUtils.isEmpty(resp) && resp.endsWith("9000")) {
                        Toast.makeText(NfcActivity.this, "验证成功", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(NfcActivity.this, "验证失败", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:

                    break;
            }
        }
    };


    private void showView(int viewType1, int viewType2, String msg1, String msg2, String msg3) {
        switch (viewType1) {
            case VIEW_HOME:

                break;
            default:

                break;
        }
    }

    @OnClick({R.id.btn_aty_nfc_zjb, R.id.btn_aty_nfc_jtb, R.id.btn_aty_nfc_0x16, R.id.btn_aty_nfc_0x17,
            R.id.btn_aty_nfc_fci, R.id.btn_aty_nfc, R.id.btn_aty_nfc_3F00, R.id.btn_aty_nfc_0x05,
            R.id.btn_aty_nfc_random, R.id.btn_aty_nfc_0x15, R.id.btn_aty_nfc_balance, R.id.btn_aty_nfc_5202,
            R.id.btn_aty_nfc_5302, R.id.btn_aty_nfc_5402, R.id.btn_aty_nfc_5502, R.id.btn_aty_nfc_clear,
            R.id.btn_aty_nfc_2, R.id.btn_aty_nfc_001, R.id.btn_aty_nfc_002, R.id.btn_aty_nfc_003,
            R.id.btn_aty_nfc_004, R.id.btn_aty_nfc_005
    })
    public void onViewClicked(View view) {
        String resp = "";
        switch (view.getId()) {
            case R.id.btn_aty_nfc_001:
                sendApdu("00A404000B535558494E2E4444463031", true);
                break;
            case R.id.btn_aty_nfc_002:
                resp = sendApdu("0084000008", true);
                if (!TextUtils.isEmpty(resp) && resp.length() > 16 && resp.endsWith("9000")) {
                    LogUtil.e("valDateApply ----------随机数获取成功-------------" + resp);
                    ValDateApplyReq valDateApplyReq = new ValDateApplyReq();
                    valDateApplyReq.setCardId("2150999999990091");
                    valDateApplyReq.setMessageDateTime("20201209094500");
                    valDateApplyReq.setAsn("86000021500099990091");
                    valDateApplyReq.setUid("0CE2AD8B");
                    valDateApplyReq.setRandom(resp.substring(0, 16));
                    valDateApply(valDateApplyReq, mHandler);
                }
                break;
            case R.id.btn_aty_nfc_003:
                resp = sendApdu("00A40000020039", true);
                break;
            case R.id.btn_aty_nfc_004:
                resp = sendApdu("00B0000000", true);
                break;
            case R.id.btn_aty_nfc_005:
                resp = sendApdu("00B0000020", true);
                break;
            case R.id.btn_aty_nfc:
                sendApdu(etAtyNfc.getText().toString(), true);
                break;
            case R.id.btn_aty_nfc_2:
                sendApdu(etAtyNfc2.getText().toString(), true);
                break;
            case R.id.btn_aty_nfc_3F00:
                resp = sendApdu("00A40000023F00", false);
                sb.append("\n选择3F00响应:" + resp.substring(resp.length() - 4));
                tvAtyNfcLog.setText(sb.toString());
                break;
            case R.id.btn_aty_nfc_0x05:
                resp = sendApdu("00B0850000", true);
                sb.append("\n卡号:" + resp.substring(24, 40));
                tvAtyNfcLog.setText(sb.toString());
                sb.append("\n发行日期:" + resp.substring(40, 48));
                tvAtyNfcLog.setText(sb.toString());
                sb.append("\n押金:" + resp.substring(60, 62));
                tvAtyNfcLog.setText(sb.toString());
                sb.append("\n售卡日期:" + resp.substring(62, 70));
                tvAtyNfcLog.setText(sb.toString());
                String cardType = resp.substring(48, 52);
                switch (Integer.parseInt(cardType)) {
                    case 1452:
                        btnAtyNfc5202.setVisibility(View.VISIBLE);
                        btnAtyNfc5302.setVisibility(View.INVISIBLE);
                        btnAtyNfc5402.setVisibility(View.INVISIBLE);
                        btnAtyNfc5502.setVisibility(View.INVISIBLE);
                        break;
                    case 1453:
                        btnAtyNfc5202.setVisibility(View.INVISIBLE);
                        btnAtyNfc5302.setVisibility(View.VISIBLE);
                        btnAtyNfc5402.setVisibility(View.INVISIBLE);
                        btnAtyNfc5502.setVisibility(View.INVISIBLE);
                        break;
                    case 1454:
                        btnAtyNfc5202.setVisibility(View.INVISIBLE);
                        btnAtyNfc5302.setVisibility(View.INVISIBLE);
                        btnAtyNfc5402.setVisibility(View.VISIBLE);
                        btnAtyNfc5502.setVisibility(View.INVISIBLE);
                        break;
                    case 1455:
                        btnAtyNfc5202.setVisibility(View.INVISIBLE);
                        btnAtyNfc5302.setVisibility(View.INVISIBLE);
                        btnAtyNfc5402.setVisibility(View.INVISIBLE);
                        btnAtyNfc5502.setVisibility(View.VISIBLE);
                        break;
                }
                break;
            case R.id.btn_aty_nfc_random:
                resp = sendApdu("0084000004", false);
                sb.append("\n随机数:" + resp.substring(0, 8));
                tvAtyNfcLog.setText(sb.toString());
                break;
            case R.id.btn_aty_nfc_0x15:
                resp = sendApdu("00B0950000", true);
                sb.append("\n启用日期:" + resp.substring(4, 12));
                tvAtyNfcLog.setText(sb.toString());
                sb.append("\n有效期:" + resp.substring(12, 20));
                tvAtyNfcLog.setText(sb.toString());
                sb.append("\n计次基数:" + Integer.parseInt(resp.substring(20, 24), 16));
                tvAtyNfcLog.setText(sb.toString());
                sb.append("\n年检日期:" + resp.substring(24, 32));
                tvAtyNfcLog.setText(sb.toString());
                break;
            case R.id.btn_aty_nfc_balance:
                resp = sendApdu("805C000204", false);
                sb.append("\n余额:" + Integer.parseInt(resp.substring(0, 8), 16) / 100);
                tvAtyNfcLog.setText(sb.toString());
                break;
            case R.id.btn_aty_nfc_5202:
                resp = sendApdu("00A40000025202", false);
                sb.append("\n选择应用响应:" + resp.substring(resp.length() - 4));
                tvAtyNfcLog.setText(sb.toString());
                break;
            case R.id.btn_aty_nfc_5302:
                resp = sendApdu("00A40000025302", false);
                sb.append("\n选择应用响应:" + resp.substring(resp.length() - 4));
                tvAtyNfcLog.setText(sb.toString());
                break;
            case R.id.btn_aty_nfc_5402:
                resp = sendApdu("00A40000025402", false);
                sb.append("\n选择应用响应:" + resp.substring(resp.length() - 4));
                tvAtyNfcLog.setText(sb.toString());
                break;
            case R.id.btn_aty_nfc_5502:
                resp = sendApdu("00A40000025502", false);
                sb.append("\n选择应用响应:" + resp.substring(resp.length() - 4));
                tvAtyNfcLog.setText(sb.toString());
                break;
            case R.id.btn_aty_nfc_clear:
                sb.delete(0, sb.length());
                tvAtyNfcLog.setText(sb.toString());
                break;
            case R.id.btn_aty_nfc_zjb:
                sendApdu("00A4040009A00000000386980701", true);
                break;
            case R.id.btn_aty_nfc_jtb:
                sendApdu("00A4040008A000000632010105", true);
                break;
            case R.id.btn_aty_nfc_0x16:
                sendApdu("00B0960000", true);
                break;
            case R.id.btn_aty_nfc_0x17:
                sendApdu("00B0970000", true);
                break;
            case R.id.btn_aty_nfc_fci:
                resp = sendApdu("00A40000023F02", false);
                sb.append("\n选择应用响应:" + resp.substring(resp.length() - 4));
                tvAtyNfcLog.setText(sb.toString());
                break;
        }
    }

    public String sendApdu(String apdu, boolean isPrint) {
        String resp = "";
        try {
            if (isPrint) {
                sb.append("\nSEND:" + apdu.toUpperCase());
                tvAtyNfcLog.setText(sb.toString().toUpperCase());
            }
            resp = nfc.sendApdu(apdu);
            if (isPrint) {
                sb.append("\nRESP:" + resp);
                tvAtyNfcLog.setText(sb.toString());
            }
        } catch (IOException e) {
            LogUtil.e("卡片交互失败");
            sb.append("\nRESP:" + "卡片交互失败");
        }
        return resp;
    }

    public void sendApdu(boolean isPrint) {
        if (isPrint) {
            sb.append("\nSSSS:" + getFormatDate("20200205"));
            tvAtyNfcLog.setText(sb.toString());
        }
    }

    public static String getFormatDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return dateFormat.format(date);
    }

    private static String ORDER_KEY = "096473137898B5B133B86F33AF8AD498";

    public static void valDateApply(ValDateApplyReq req, Handler handler) {
        LinkedHashMap<String, String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("cardId", req.getCardId());
        paramsMap.put("messageDateTime", DateTimeUtil.getDateTime("yyyyMMddhhmmss"));
        paramsMap.put("deviceId", "2222EF0AF07A");
        paramsMap.put("asn", req.getAsn());
        paramsMap.put("uid", req.getUid());
        paramsMap.put("random", req.getRandom());
        String macCode = EncryptUtil.calculateMac(paramsMap, ORDER_KEY);
        paramsMap.put("macCode", macCode);
        String url = "http://192.168.10.163:9011/recharge_service_sz/api/val-educard/apply-valDate";
        LogUtil.e("valDateApply ---------获取mac-----------------");
        UrlHttpUtil.post(url, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(int code, String errorMessage) {
                LogUtil.e("valDateApply fail " + errorMessage);
            }

            @Override
            public void onResponse(String response) {
                Message msg = handler.obtainMessage();
                LogUtil.e("valDateApply success " + response);
                BaseObject<ValDateApplyResp> baseObject = new Gson().fromJson(response, new TypeToken<BaseObject<ValDateApplyResp>>() {
                }.getType());
                if (baseObject.getCode() != null && baseObject.getCode().equals("0")) {
                    ValDateApplyResp valDateApplyResp = baseObject.getContent();
                    if ("0".equals(valDateApplyResp.getErrorcode())) {
                        msg.what = 0;
                        msg.obj = baseObject.getContent();
                    } else {
                        msg.what = 1;
                        msg.obj = baseObject.getContent().getErrormsg();
                    }
                } else {
                    msg.what = 2;
                    msg.obj = baseObject.getMessage();
                }
                handler.sendMessage(msg);
            }
        });
    }
}
