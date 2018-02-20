package com.mig82.scanner;


import android.content.Intent;
import com.konylabs.android.KonyMain;
import com.konylabs.vm.Function;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by miguelangel on 20/02/2018.
 */

public class ScannerWrapper {

    private static final String LOG_TAG = "ScannerFFI";

    public void scan(Function onSuccess, Function onCancelled, Function onFailure, HashMap<String, String> config) {

        Log.i(LOG_TAG, "1. Initializing context and intent for ScannerActiviry");
        KonyMain localKonyMain = KonyMain.getActivityContext();
        Intent intent = new Intent(localKonyMain, ScannerActivity.class);
        intent.putExtra("onSuccess", onSuccess);
        intent.putExtra("onCancelled", onCancelled);
        intent.putExtra("onFailure", onFailure);
        intent.putExtra("config", config);

        Log.i(LOG_TAG, "2. Starting ScannerActiviry");
        localKonyMain.startActivity(intent);
    }
}