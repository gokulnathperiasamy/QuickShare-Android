package com.quickshare.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.quickshare.application.QuickShareApplication;

public final class PreferenceHelper {
    private static final String QUICK_SHARE_SHARED_PREFERENCE_NAME = "QuickShareSharedPreferenceName";
    private static final String IS_PROFILE_SET = "IsProfileSet";
    private static final String IS_DATA_AVAILABLE = "IsDataAvailable";

    private SharedPreferences sharedPreferences;

    private PreferenceHelper() {

    }

    private static SharedPreferences getSharedPreferences() {
        return QuickShareApplication.get(Context.class).getSharedPreferences(QUICK_SHARE_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static void clearSharedPreference() {
        getSharedPreferences().edit().clear().apply();
    }

    public static void setIsProfileSet(boolean isProfileSet) {
        getSharedPreferences().edit().putBoolean(IS_PROFILE_SET, isProfileSet).apply();
    }

    public static boolean isProfileSet() {
        return getSharedPreferences().getBoolean(IS_PROFILE_SET, false);
    }

    public static void setIsDataAvailable(boolean isDataAvailable) {
        getSharedPreferences().edit().putBoolean(IS_DATA_AVAILABLE, isDataAvailable).apply();
    }

    public static boolean isDataAvailable() {
        return getSharedPreferences().getBoolean(IS_DATA_AVAILABLE, false);
    }
}
