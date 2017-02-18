package com.quickshare.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.quickshare.R;
import com.quickshare.activity.MainActivity;
import com.quickshare.entity.ProfileData;

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

    public EditProfileFragment() {
    }

    public static EditProfileFragment newInstance(MainActivity mainActivity) {
        EditProfileFragment.mainActivity = mainActivity;
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
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ProfileData profileData = new ProfileData();
        profileData.firstName = firstName.getText().toString();
        profileData.lastName = lastName.getText().toString();
        profileData.companyName = companyName.getText().toString();
        profileData.companyAddress = companyAddress.getText().toString();
        profileData.email = email.getText().toString();
        profileData.mobileNumber = mobileNumber.getText().toString();
        profileData.officeNumber = officeNumber.getText().toString();
        profileData.fax = fax.getText().toString();
        mainActivity.saveProfile(profileData);
    }
}
