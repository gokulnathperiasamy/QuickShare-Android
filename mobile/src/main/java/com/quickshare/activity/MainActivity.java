package com.quickshare.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.quickshare.R;
import com.quickshare.dialog.ShareMyProfileDialog;
import com.quickshare.entity.ProfileData;
import com.quickshare.entity.ProfileDataType;
import com.quickshare.fragment.CardFragment;
import com.quickshare.fragment.EditProfileFragment;
import com.quickshare.fragment.HomeFragment;
import com.quickshare.utility.DialogHelper;
import com.quickshare.utility.PreferenceHelper;
import com.quickshare.utility.ProfileDataHelper;

import net.glxn.qrgen.android.QRCode;

import org.litepal.crud.DataSupport;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.cta_share)
    FloatingActionButton ctaShare;

    @Bind(R.id.layout_cta_main)
    View layoutCTAMain;

    @Bind(R.id.layout_cta_user)
    View layoutCTAUser;

    private static boolean isSaveCalled = false;
    private static ProfileData myProfileData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toggleShareOption(false);
        loadCardData();
    }

    public void loadCardData() {
        loadCardData(false);
    }

    public void loadCardData(boolean isNewlyAdded) {
        hideSoftKeyboard();
        List<ProfileData> listProfileData = DataSupport.order("id").find(ProfileData.class);
        if (listProfileData != null && listProfileData.size() > 0) {
            Collections.reverse(listProfileData);
            loadCards(listProfileData, isNewlyAdded);
        } else {
            startFragment(HomeFragment.newInstance(this), false);
        }
    }

    private void loadCards(List<ProfileData> listProfileData, boolean isNewlyAdded) {
        for (ProfileData profileData : listProfileData) {
            if (profileData.isMyProfile.equalsIgnoreCase(ProfileDataType.MY_PROFILE.getValue())) {
                myProfileData = profileData;
                PreferenceHelper.setIsProfileSet(true);
                toggleShareOption(true);
                listProfileData.remove(profileData);
                break;
            }
        }
        // Remove personal card. And populate other cards.
        if (listProfileData.size() > 0) {
            startFragment(CardFragment.newInstance(this, listProfileData, isNewlyAdded), false);
        } else {
            startFragment(HomeFragment.newInstance(this), false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_my_profile:
                editMyProfile();
                break;
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
        if (flag) {
            ctaShare.setImageResource(R.mipmap.ic_share);
        } else {
            ctaShare.setImageResource(R.mipmap.ic_share_disabled);
        }

    }

    public void showCTAMainLayout(boolean flag) {
        if (flag) {
            layoutCTAMain.setVisibility(View.VISIBLE);
        } else {
            layoutCTAMain.setVisibility(View.GONE);
        }
    }

    public void showCTAUserLayout(boolean flag) {
        if (flag) {
            layoutCTAUser.setVisibility(View.VISIBLE);
        } else {
            layoutCTAUser.setVisibility(View.GONE);
        }
    }

    private void editMyProfile() {
        startFragment(EditProfileFragment.newInstance(this, myProfileData), false);
        showCTAMainLayout(false);
        showCTAUserLayout(true);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.cta_share)
    public void shareMyCard(View view) {
        if (myProfileData != null) {
            Bitmap myBitmap = QRCode.from(ProfileDataHelper.getQRCodeFromProfileData(myProfileData)).bitmap();
            if (myBitmap != null) {
                ShareMyProfileDialog dialog = new ShareMyProfileDialog(this, myBitmap);
                dialog.show();
            }
        }
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.cta_add)
    public void addCard(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.setCaptureActivity(ReadQRCodeActivity.class);
        integrator.setTimeout(10000);
        integrator.initiateScan();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.cta_cancel)
    public void cancelEditProfile(View view) {
        isSaveCalled = false;
        showHomeFragment();
        loadCardData();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.cta_save)
    public void saveProfile(View view) {
        isSaveCalled = true;
        loadCardData();
    }

    private void showHomeFragment() {
        startFragment(HomeFragment.newInstance(this), false);
        hideSoftKeyboard();
        showCTAMainLayout(true);
        showCTAUserLayout(false);
    }

    private void hideSoftKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void saveProfile(ProfileData profileData) {
        if (isSaveCalled) {
            // Delete current profile data.
            if (myProfileData != null) {
                myProfileData.delete();
            }

            // Save new profile data.
            if (ProfileDataHelper.isDataValid(profileData) && profileData.save()) {
                Toast.makeText(this, getString(R.string.profile_saved_message), Toast.LENGTH_SHORT).show();
                myProfileData = profileData;
                toggleShareOption(true);
                PreferenceHelper.setIsProfileSet(true);
            } else {
                Toast.makeText(this, getString(R.string.profile_invalid_message), Toast.LENGTH_SHORT).show();
            }
        }
        showCTAMainLayout(true);
        showCTAUserLayout(false);
        hideSoftKeyboard();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, getString(R.string.new_card_unable_to_read_message), Toast.LENGTH_LONG).show();
            } else {
                String input = result.getContents();
                saveData(input);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void saveData(String input) {
        ProfileData profileData = ProfileDataHelper.getProfileDataFromQRCode(input);
        if (profileData != null) {
            profileData.save();
            loadCardData(true);
        } else {
            Toast.makeText(this, getString(R.string.new_card_unable_to_save_message), Toast.LENGTH_LONG).show();
        }
    }
}
