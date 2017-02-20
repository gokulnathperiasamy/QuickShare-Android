package com.quickshare.activity;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.quickshare.R;

public class ReadQRCodeActivity extends CaptureActivity {
    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.activity_small_capture);
        return (DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
    }
}
