package com.quickshare.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.quickshare.R;
import com.quickshare.activity.MainActivity;
import com.quickshare.entity.ProfileData;
import com.quickshare.entity.ProfileDataType;
import com.quickshare.utility.ProfileDataHelper;
import com.quickshare.utility.TextHelper;

import butterknife.Bind;

public class EditProfileFragment extends BaseFragment {

    @Bind(R.id.first_name)
    TextView firstName;

    @Bind(R.id.last_name)
    TextView lastName;

    @Bind(R.id.company_name)
    TextView companyName;

    @Bind(R.id.company_address)
    TextView companyAddress;

    @Bind(R.id.email_address)
    TextView email;

    @Bind(R.id.mobile_number)
    TextView mobileNumber;

    @Bind(R.id.office_number)
    TextView officeNumber;

    @Bind(R.id.fax)
    TextView fax;

    private static MainActivity mainActivity;
    private static ProfileData myProfileData;

    public EditProfileFragment() {
    }

    public static EditProfileFragment newInstance(MainActivity mainActivity, ProfileData myProfileData) {
        EditProfileFragment.mainActivity = mainActivity;
        EditProfileFragment.myProfileData = myProfileData;
        return new EditProfileFragment();
    }

    @Override
    protected int getTitleId() {
        return R.string.my_profile_title;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edit_profile;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (myProfileData != null) {
            if (TextHelper.isValidText(myProfileData.firstName)) {
                firstName.setText(myProfileData.firstName);
            }
            if (TextHelper.isValidText(myProfileData.lastName)) {
                lastName.setText(myProfileData.lastName);
            }
            if (TextHelper.isValidText(myProfileData.companyName)) {
                companyName.setText(myProfileData.companyName);
            }
            if (TextHelper.isValidText(myProfileData.companyAddress)) {
                companyAddress.setText(myProfileData.companyAddress);
            }
            if (TextHelper.isValidText(myProfileData.email)) {
                email.setText(myProfileData.email);
            }
            if (TextHelper.isValidText(myProfileData.mobileNumber)) {
                mobileNumber.setText(myProfileData.mobileNumber);
            }
            if (TextHelper.isValidText(myProfileData.officeNumber)) {
                officeNumber.setText(myProfileData.officeNumber);
            }
            if (TextHelper.isValidText(myProfileData.fax)) {
                fax.setText(myProfileData.fax);
            }
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_my_profile);
        item.setVisible(false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ProfileData profileData = new ProfileData();
        profileData.profileID = ProfileDataHelper.getProfileID();
        profileData.firstName = firstName.getText().toString();
        profileData.lastName = lastName.getText().toString();
        profileData.companyName = companyName.getText().toString();
        profileData.companyAddress = companyAddress.getText().toString();
        profileData.email = email.getText().toString();
        profileData.mobileNumber = mobileNumber.getText().toString();
        profileData.officeNumber = officeNumber.getText().toString();
        profileData.fax = fax.getText().toString();
        profileData.isMyProfile = ProfileDataType.MY_PROFILE.getValue();
        mainActivity.saveProfile(profileData);
    }
}
