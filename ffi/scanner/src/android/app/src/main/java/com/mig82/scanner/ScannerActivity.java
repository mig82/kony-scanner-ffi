package com.mig82.scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.content.pm.PackageManager;

import com.konylabs.vm.Function;
import android.content.Context;
//import org.apache.cordova.CordovaPlugin;
//import org.apache.cordova.CallbackContext;
//import org.apache.cordova.PluginResult;
//import org.apache.cordova.PermissionHelper;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.encode.EncodeActivity;
import com.google.zxing.client.android.Intents;

import java.util.HashMap;

/**
 * This calls out to the ZXing barcode reader and returns the result.
 *
 * @sa https://github.com/apache/cordova-android/blob/master/framework/src/org/apache/cordova/CordovaPlugin.java
 */
public class ScannerActivity extends Activity{
    public static final int SCAN_REQUEST_CODE = 1;

    private static final String SCAN = "scan";
    private static final String ENCODE = "encode";
    private static final String CANCELLED = "cancelled";
    private static final String FORMAT = "format";
    private static final String TEXT = "text";
    private static final String DATA = "data";
    private static final String TYPE = "type";
    private static final String PREFER_FRONTCAMERA = "preferFrontCamera";
    private static final String ORIENTATION = "orientation";
    private static final String SHOW_FLIP_CAMERA_BUTTON = "showFlipCameraButton";
    private static final String RESULTDISPLAY_DURATION = "resultDisplayDuration";
    private static final String SHOW_TORCH_BUTTON = "showTorchButton";
    private static final String TORCH_ON = "torchOn";
    private static final String SAVE_HISTORY = "saveHistory";
    private static final String DISABLE_BEEP = "disableSuccessBeep";
    private static final String FORMATS = "formats";
    private static final String PROMPT = "prompt";
    private static final String TEXT_TYPE = "TEXT_TYPE";
    private static final String EMAIL_TYPE = "EMAIL_TYPE";
    private static final String PHONE_TYPE = "PHONE_TYPE";
    private static final String SMS_TYPE = "SMS_TYPE";

    private static final String LOG_TAG = "ScannerFFI";

    private String [] permissions = { Manifest.permission.CAMERA };

    private JSONArray requestArgs;

    //private CallbackContext callbackContext;
    private Function onSuccess, onCancelled, onFailure;
    private HashMap<String, String> config;

    /**
     * Constructor.
     */
    public ScannerActivity() {
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        Log.i(LOG_TAG, "3. Initializing context and intent for CaptureActivity");

        onSuccess = (Function) getIntent().getExtras().get("onSuccess");
        Log.i(LOG_TAG, "3.1 onSuccess:" + onSuccess);

        onFailure = (Function) getIntent().getExtras().get("onFailure");
        Log.i(LOG_TAG, "3.2 onFailure:" + onFailure);

        onCancelled = (Function) getIntent().getExtras().get("onCancelled");
        Log.i(LOG_TAG, "3.3 onCancelled:" + onCancelled);

        config = (HashMap<String, String>) getIntent().getExtras().get("config");
        Log.i(LOG_TAG, "3.4 config:" + config);

        Intent captureIntent = new Intent(ScannerActivity.this, CaptureActivity.class);
        captureIntent.setAction(Intents.Scan.ACTION);
        captureIntent.addCategory(Intent.CATEGORY_DEFAULT);

        // add config as intent extras
        /*if (args.length() > 0) {

            JSONObject obj;
            JSONArray names;
            String key;
            Object value;

            for (int i = 0; i < args.length(); i++) {

                try {
                    obj = args.getJSONObject(i);
                } catch (JSONException e) {
                    Log.i("ScannerLog", e.getLocalizedMessage());
                    continue;
                }

                names = obj.names();
                for (int j = 0; j < names.length(); j++) {
                    try {
                        key = names.getString(j);
                        value = obj.get(key);

                        if (value instanceof Integer) {
                            intent.putExtra(key, (Integer) value);
                        } else if (value instanceof String) {
                            intent.putExtra(key, (String) value);
                        }

                    } catch (JSONException e) {
                        Log.i("CordovaLog", e.getLocalizedMessage());
                    }
                }

                intent.putExtra(Intents.Scan.CAMERA_ID, obj.optBoolean(PREFER_FRONTCAMERA, false) ? 1 : 0);
                intent.putExtra(Intents.Scan.SHOW_FLIP_CAMERA_BUTTON, obj.optBoolean(SHOW_FLIP_CAMERA_BUTTON, false));
                intent.putExtra(Intents.Scan.SHOW_TORCH_BUTTON, obj.optBoolean(SHOW_TORCH_BUTTON, false));
                intent.putExtra(Intents.Scan.TORCH_ON, obj.optBoolean(TORCH_ON, false));
                intent.putExtra(Intents.Scan.SAVE_HISTORY, obj.optBoolean(SAVE_HISTORY, false));
                boolean beep = obj.optBoolean(DISABLE_BEEP, false);
                intent.putExtra(Intents.Scan.BEEP_ON_SCAN, !beep);
                if (obj.has(RESULTDISPLAY_DURATION)) {
                    intent.putExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS, "" + obj.optLong(RESULTDISPLAY_DURATION));
                }
                if (obj.has(FORMATS)) {
                    intent.putExtra(Intents.Scan.FORMATS, obj.optString(FORMATS));
                }
                if (obj.has(PROMPT)) {
                    intent.putExtra(Intents.Scan.PROMPT_MESSAGE, obj.optString(PROMPT));
                }
                if (obj.has(ORIENTATION)) {
                    intent.putExtra(Intents.Scan.ORIENTATION_LOCK, obj.optString(ORIENTATION));
                }
            }

        }*/

        Log.i(LOG_TAG, "4. Starting CaptureActivity");
        startActivityForResult(captureIntent, SCAN_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        Log.i(LOG_TAG, "5. onActivityResult req:" + requestCode + " res:" + resultCode);

        if (requestCode == SCAN_REQUEST_CODE) {

            if (resultCode == Activity.RESULT_OK) {

                Log.i(LOG_TAG, "5.1 onActivityResult OK");
                JSONObject obj = new JSONObject();
                try {
                    obj.put(TEXT, intent.getStringExtra("SCAN_RESULT"));
                    obj.put(FORMAT, intent.getStringExtra("SCAN_RESULT_FORMAT"));
                    obj.put(CANCELLED, false);
                }
                catch (JSONException e) {
                    Log.i(LOG_TAG, "This should never happen");
                }

                Log.i(LOG_TAG, "5.2 Invoking onSuccess:" + onSuccess);
                invokeJsCallback(onSuccess, obj);
                //finish();
            }
            else if (resultCode == Activity.RESULT_CANCELED) {

                Log.i(LOG_TAG, "5.B CANCELLED");

                JSONObject obj = new JSONObject();
                try {
                    obj.put(TEXT, "");
                    obj.put(FORMAT, "");
                    obj.put(CANCELLED, true);
                } catch (JSONException e) {
                    Log.i(LOG_TAG, "This should never happen");
                }
                invokeJsCallback(onCancelled, obj);
                //finish();
            }
            else {
                Log.e(LOG_TAG, "5.C ERROR");
                invokeJsCallback(onFailure);
                //finish();
            }
        }
    }

    private void invokeJsCallback(Function jsCallback){
        invokeJsCallback(jsCallback, new Object[]{});
    }

    private void invokeJsCallback(Function jsCallback, Object data){

        Log.i(LOG_TAG, "6. Invoking callback with data:" + data);
        try {
            jsCallback.execute(new Object[]{data});
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
