package com.mig82.scanner;


import android.content.Intent;
import com.konylabs.android.KonyMain;
import com.konylabs.vm.Function;
import android.util.Log;

/**
 * Created by miguelangel on 20/02/2018.
 */

public class ScannerWrapper {

    private static final String LOG_TAG = "ScannerWrapper";

    public void scan(Function paramFunction) {
        Log.i(LOG_TAG, "1. Requesting scan");

        KonyMain localKonyMain = KonyMain.getActivityContext();
        Intent intent = new Intent(localKonyMain, ScannerActivity.class);
        intent.putExtra("jsCallback", paramFunction);
        localKonyMain.startActivity(intent);
    }
}