package com.quickshare.utility;

public class ProfileDataHelper {

    public static String getProfileID() {
        return "_ID" + "|" + Math.random() + "|" + System.nanoTime();
    }

}
