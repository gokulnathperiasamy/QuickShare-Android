package com.quickshare.utility;

import com.quickshare.entity.ProfileData;

public class ProfileDataHelper {

    public static String getProfileID() {
        return "_ID" + "|" + Math.random() + "|" + System.nanoTime();
    }

    public static boolean isDataValid(ProfileData profileData) {
        return profileData != null && profileData.firstName.length() > 0;
    }

}
