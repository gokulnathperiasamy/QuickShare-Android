package com.quickshare.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public final class PreferenceHelper {
    private static final String IS_PROFILE_SET = "IsProfileSet";
    private static final String CARD_IMAGE_DATA = "CardImageData";

    private Context context;

    public PreferenceHelper(Context context) {
        this.context = context;
    }

    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void clearSharedPreference() {
        getSharedPreferences().edit().clear().apply();
    }

    public void setIsProfileSet(boolean isProfileSet) {
        getSharedPreferences().edit().putBoolean(IS_PROFILE_SET, isProfileSet).apply();
    }

    public boolean isProfileSet() {
        return getSharedPreferences().getBoolean(IS_PROFILE_SET, false);
    }

    public void setCardImageData(Bitmap bitmap) {
        getSharedPreferences().edit().putString(CARD_IMAGE_DATA, ImageHelper.encodeToBase64(bitmap)).apply();
    }

    public Bitmap getCardImageData() {
        return ImageHelper.decodeFromBase64(getSharedPreferences().getString(CARD_IMAGE_DATA, null));
    }

}
