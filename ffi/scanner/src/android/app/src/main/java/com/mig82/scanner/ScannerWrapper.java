package com.mig82.scanner;


import android.content.Intent;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Intents;
import com.konylabs.android.KonyMain;
import com.konylabs.vm.Function;
import android.util.Log;

/**
 * Created by miguelangel on 20/02/2018.
 */

public class ScannerWrapper {

    private static final String LOG_TAG = "ScannerFFI";

    /**
     *
     * @param	formats	Comma separated list of DATA_MATRIX,QR_CODE",
     * @param	preferFrontCamera	iOS and Android
     * @param	showFlipCameraButton	default false
     * @param	showTorchButton	iOS and Android
     * @param	torchOn	Android, launch with the torch switched on (if available)
     * @param	promptMessage	Android only, default is 'Place a barcode inside the viewfinder rectangle to scan it.'
     * @param	resultDisplayDuration	Android, display scanned text for X ms. 0 suppresses it entirely, default 1500ms
     * @param	orientation	Android only, orientation|portrait|landscape
     * @param	beepOnScan	Play or suppress beep on scan.
     * @param   saveHistory ...
     */

    public static final int SCAN_REQUEST_CODE = 1;

    public void scan(Function onSuccess, Function onCancelled, Function onFailure,
                     String formats,
                     Boolean preferFrontCamera,
                     Boolean showFlipCameraButton,
                     Boolean showTorchButton,
                     Boolean torchOn,
                     String promptMessage,
                     Integer resultDisplayDuration,
                     String orientation,
                     Boolean beepOnScan,
                     Boolean saveHistory
    ) {

        Log.i(LOG_TAG, "1. Initializing context and intent for CaptureActivity");
        KonyMain konyActivityContext = KonyMain.getActivityContext();


        Intent intent = new Intent(konyActivityContext, CaptureActivity.class);
        intent.setAction(Intents.Scan.ACTION);
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        intent.putExtra(Intents.Scan.CAMERA_ID, preferFrontCamera?1:0);
        intent.putExtra(Intents.Scan.SHOW_FLIP_CAMERA_BUTTON, showFlipCameraButton);
        intent.putExtra(Intents.Scan.SHOW_TORCH_BUTTON, showTorchButton);
        intent.putExtra(Intents.Scan.TORCH_ON, torchOn);
        intent.putExtra(Intents.Scan.SAVE_HISTORY, saveHistory);
        intent.putExtra(Intents.Scan.BEEP_ON_SCAN, beepOnScan);

        intent.putExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS, new Long(resultDisplayDuration));
        intent.putExtra(Intents.Scan.FORMATS, formats);

        intent.putExtra(Intents.Scan.PROMPT_MESSAGE, promptMessage);
        intent.putExtra(Intents.Scan.ORIENTATION_LOCK, orientation);

        konyActivityContext.registerActivityResultListener(SCAN_REQUEST_CODE,
            new ScanResultListerner(
                onSuccess,
                onCancelled,
                onFailure
            )
        );

        Log.i(LOG_TAG, "2. Starting CaptureActivity");
        konyActivityContext.startActivityForResult(intent, SCAN_REQUEST_CODE);
    }
}