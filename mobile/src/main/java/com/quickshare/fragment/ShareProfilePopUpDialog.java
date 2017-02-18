package com.quickshare.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quickshare.R;

public class ShareProfilePopUpDialog extends DialogFragment {

    private static Context context;

    public static ShareProfilePopUpDialog newInstance(Context context) {
        ShareProfilePopUpDialog.context = context;
        ShareProfilePopUpDialog shareProfilePopUpDialog = new ShareProfilePopUpDialog();
        shareProfilePopUpDialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AlertDialogCustom);
        return shareProfilePopUpDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setTitle(R.string.my_profile_title);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, R.style.AlertDialogCustom);

        // Inflate the new view with margins and background
        View v = inflater.from(wrapper).inflate(R.layout.view_edit_profile, container, false);

        // Set up a click listener to dismiss the popup if they click outside
        // of the background view
        /*v.findViewById(R.id.edit_profile_popup_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dismiss();
            }
        });*/

        return v;
    }
}
