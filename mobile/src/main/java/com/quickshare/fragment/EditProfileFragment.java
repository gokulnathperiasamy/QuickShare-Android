package com.quickshare.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.quickshare.R;
import com.quickshare.activity.MainActivity;

public class EditProfileFragment extends BaseFragment {

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
}
