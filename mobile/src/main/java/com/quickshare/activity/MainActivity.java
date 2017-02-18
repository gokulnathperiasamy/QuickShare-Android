package com.quickshare.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.quickshare.R;
import com.quickshare.fragment.EditProfileFragment;
import com.quickshare.fragment.HomeFragment;
import com.quickshare.utility.DialogHelper;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.cta_edit)
    TextView ctaEdit;

    @Bind(R.id.cta_share)
    TextView ctaShare;

    @Bind(R.id.cta_add)
    TextView ctaAdd;

    @Bind(R.id.layout_cta_main)
    View layoutCTAMain;

    @Bind(R.id.layout_cta_user)
    View layoutCTAUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        startFragment(HomeFragment.newInstance(this), false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                DialogHelper.showAboutMessage(this);
                break;
            case R.id.action_rate:
                DialogHelper.launchPlayStore();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void startFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getFragmentManager();
        if (!addToBackStack) {
            int count = fragmentManager.getBackStackEntryCount();
            for (int i = 0; i < count; ++i) {
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.coordinator_content, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public void toggleShareOption(boolean flag) {
        ctaShare.setEnabled(flag);
    }

    public void showCTAMain(boolean flag) {
        if (flag) {
            layoutCTAMain.setVisibility(View.VISIBLE);
        } else {
            layoutCTAMain.setVisibility(View.GONE);
        }
    }

    public void showCTAUser(boolean flag) {
        if (flag) {
            layoutCTAUser.setVisibility(View.VISIBLE);
        } else {
            layoutCTAUser.setVisibility(View.GONE);
        }
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.cta_edit)
    public void editProfile(View view) {
        startFragment(EditProfileFragment.newInstance(this), false);
        showCTAMain(false);
        showCTAUser(true);
    }
}
