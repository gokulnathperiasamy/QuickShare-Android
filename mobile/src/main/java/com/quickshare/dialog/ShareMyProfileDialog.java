package com.quickshare.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.quickshare.R;
import com.quickshare.entity.ProfileData;
import com.quickshare.utility.ProfileDataHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShareMyProfileDialog extends Dialog {

    @Bind(R.id.my_profile_qr_code)
    ImageView myProfileQRCode;

    @Bind(R.id.d_name)
    TextView name;

    @Bind(R.id.d_company_name)
    TextView companyName;

    @Bind(R.id.d_company_address)
    TextView companyAddress;

    @Bind(R.id.d_email_id)
    TextView emailID;

    @Bind(R.id.d_mobile_number)
    TextView mobileNumber;

    @Bind(R.id.d_office_number)
    TextView officeNumber;

    @Bind(R.id.d_fax)
    TextView fax;

    public ShareMyProfileDialog(Context context, Bitmap bitmap, ProfileData myProfileData) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_share_my_profile);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ButterKnife.bind(this);
        myProfileQRCode.setImageBitmap(bitmap);
        setData(myProfileData);
    }

    private void setData(ProfileData profileData) {
        name.setText(ProfileDataHelper.getFullName(profileData.firstName, profileData.lastName));
        if (profileData.companyName.length() > 0) {
            companyName.setText(profileData.companyName);
        } else {
            companyName.setVisibility(View.GONE);
        }

        if (profileData.companyAddress.length() > 0) {
            companyAddress.setText(profileData.companyAddress);
        } else {
            companyAddress.setVisibility(View.GONE);
        }

        if (profileData.email.length() > 0) {
            emailID.setText(ProfileDataHelper.getFormattedEmail(profileData.email));
        } else {
            emailID.setVisibility(View.GONE);
        }

        if (profileData.mobileNumber.length() > 0) {
            mobileNumber.setText(ProfileDataHelper.getFormattedMobileNumber(profileData.mobileNumber));
        } else {
            mobileNumber.setVisibility(View.GONE);
        }

        if (profileData.officeNumber.length() > 0) {
            officeNumber.setText(ProfileDataHelper.getFormattedOfficeNumber(profileData.officeNumber));
        } else {
            officeNumber.setVisibility(View.GONE);
        }

        if (profileData.fax.length() > 0) {
            fax.setText(ProfileDataHelper.getFormattedFax(profileData.fax));
        } else {
            fax.setVisibility(View.GONE);
        }
    }
}
