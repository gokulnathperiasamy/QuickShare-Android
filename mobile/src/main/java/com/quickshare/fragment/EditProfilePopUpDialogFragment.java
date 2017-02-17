package com.quickshare.fragment;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quickshare.R;

public class EditProfilePopUpDialogFragment extends DialogFragment {

    public static EditProfilePopUpDialogFragment newInstance() {
        EditProfilePopUpDialogFragment editProfilePopUpDialogFragment = new EditProfilePopUpDialogFragment();
        editProfilePopUpDialogFragment.setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_DeviceDefault_Dialog);
        return editProfilePopUpDialogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Remove the default background
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Inflate the new view with margins and background
        View v = inflater.inflate(R.layout.view_edit_profile, container, false);

        // Set up a click listener to dismiss the popup if they click outside
        // of the background view
        v.findViewById(R.id.edit_profile_popup_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dismiss();
            }
        });

        return v;
    }
}
