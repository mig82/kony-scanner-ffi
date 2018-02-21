package com.mig82.scanner;

import android.content.Intent;

import com.konylabs.ffi.ActivityResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;

import com.konylabs.vm.Function;

/**
 * Created by miguelangel on 21/02/2018.
 */

public class ScanResultListerner implements ActivityResultListener {

    private static final String LOG_TAG = "ScannerFFI";

    private static final String CANCELLED = "cancelled";
    private static final String FORMAT = "format";
    private static final String TEXT = "text";

    private Function onSuccess, onCancelled, onFailure;

    public ScanResultListerner(Function onSuccess, Function onCancelled, Function onFailure){
        this.onSuccess = onSuccess;
        this.onCancelled = onCancelled;
        this.onFailure = onFailure;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.i(LOG_TAG, "5. onActivityResult req:" + requestCode + " res:" + resultCode);

        if (requestCode == ScannerWrapper.SCAN_REQUEST_CODE) {

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
            //jsCallback.execute(new Object[]{data});
            jsCallback.executeAsync(new Object[]{data});
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
