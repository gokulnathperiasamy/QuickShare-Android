package com.quickshare.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.quickshare.R;
import com.quickshare.activity.MainActivity;
import com.quickshare.utility.PreferenceHelper;

import butterknife.Bind;

public class HomeFragment extends BaseFragment {

    @Bind(R.id.layout_error)
    View layoutError;

    @Bind(R.id.layout_error_message)
    TextView layoutErrorMessage;

    private static MainActivity mainActivity;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(MainActivity mainActivity) {
        HomeFragment.mainActivity = mainActivity;
        return new HomeFragment();
    }

    @Override
    protected int getTitleId() {
        return R.string.app_name;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!PreferenceHelper.isProfileSet()) {
            layoutError.setVisibility(View.VISIBLE);
            layoutErrorMessage.setVisibility(View.VISIBLE);
            layoutErrorMessage.setText(getString(R.string.set_profile_message));
            mainActivity.toggleShareOption(false);
        } else if (!PreferenceHelper.isDataAvailable()) {
            layoutError.setVisibility(View.VISIBLE);
            layoutErrorMessage.setVisibility(View.VISIBLE);
            layoutErrorMessage.setText(getString(R.string.data_not_available_message));
        } else {
            layoutError.setVisibility(View.GONE);
            layoutErrorMessage.setVisibility(View.GONE);
        }
    }
}
