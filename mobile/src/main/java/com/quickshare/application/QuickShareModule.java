package com.quickshare.application;

import android.content.Context;

import com.quickshare.activity.BaseActivity;
import com.quickshare.activity.MainActivity;
import com.quickshare.adapter.CardListAdapter;
import com.quickshare.adapter.CardListItemViewHolder;
import com.quickshare.dialog.ShareMyProfileDialog;
import com.quickshare.fragment.BaseFragment;
import com.quickshare.fragment.CardFragment;
import com.quickshare.fragment.EditProfileFragment;
import com.quickshare.fragment.HomeFragment;
import com.quickshare.utility.DialogHelper;
import com.quickshare.utility.PreferenceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                QuickShareApplication.class,
                QuickShareModule.class,
                Context.class,
                PreferenceHelper.class,
                DialogHelper.class,
                BaseActivity.class,
                MainActivity.class,
                BaseFragment.class,
                HomeFragment.class,
                EditProfileFragment.class,
                CardFragment.class,
                CardListAdapter.class,
                CardListItemViewHolder.class,
                ShareMyProfileDialog.class,
        })
public class QuickShareModule {

    private Context context;

    public QuickShareModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return context;
    }

}
