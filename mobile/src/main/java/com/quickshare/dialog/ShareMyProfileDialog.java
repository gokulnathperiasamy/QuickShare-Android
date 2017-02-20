package com.quickshare.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.quickshare.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShareMyProfileDialog extends Dialog {

    @Bind(R.id.my_profile_qr_code)
    ImageView myProfileQRCode;

    private Bitmap bitmap;
    private Context context;

    public ShareMyProfileDialog(Context context, Bitmap bitmap) {
        super(context);
        this.context = context;
        this.bitmap = bitmap;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_share_my_profile);
        ButterKnife.bind(this);
        myProfileQRCode.setImageBitmap(bitmap);
    }

    private View.OnClickListener getDialogListener(final View.OnClickListener listener) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        };
    }
}
