package com.quickshare.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.quickshare.R;
import com.quickshare.fragment.HomeFragment;
import com.quickshare.utility.DialogHelper;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.cta_edit)
    TextView ctaEdit;

    @Bind(R.id.cta_share)
    TextView ctaShare;

    @Bind(R.id.cta_add)
    TextView ctaAdd;

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
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (!addToBackStack) {
            int count = supportFragmentManager.getBackStackEntryCount();
            for (int i = 0; i < count; ++i) {
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.coordinator_content, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public void toggleShareOption(boolean flag) {
        ctaShare.setEnabled(flag);
    }
}
