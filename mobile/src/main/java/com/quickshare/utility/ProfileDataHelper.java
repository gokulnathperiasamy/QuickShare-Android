package com.quickshare.utility;

import com.quickshare.entity.ProfileData;
import com.quickshare.entity.ProfileDataType;

public class ProfileDataHelper {

    public static String getProfileID() {
        return "_ID" + "|" + generateRandomNumber(10, 99) + "|" + System.nanoTime();
    }

    public static boolean isDataValid(ProfileData profileData) {
        return profileData != null && profileData.firstName.length() > 0;
    }

    public static String getFullName(String firstName, String lastName) {
        if (lastName.length() > 0) {
            return firstName + " " + lastName;
        }
        return firstName;
    }

    public static String getFormattedEmail(String email) {
        if (email.length() > 0) {
            return "E: " + email;
        }
        return "";
    }

    public static String getFormattedMobileNumber(String mobileNumber) {
        if (mobileNumber.length() > 0) {
            return "M: " + mobileNumber;
        }
        return "";
    }

    public static String getFormattedOfficeNumber(String officeNumber) {
        if (officeNumber.length() > 0) {
            return "O: " + officeNumber;
        }
        return "";
    }

    public static String getFormattedFax(String fax) {
        if (fax.length() > 0) {
            return "F: " + fax;
        }
        return "";
    }

    public static void generateDummyData() {
        ProfileData profileData = new ProfileData();
        profileData.profileID = ProfileDataHelper.getProfileID();
        profileData.firstName = "First" + generateRandomNumber(0, 9);
        profileData.lastName = "Last" + generateRandomNumber(0, 9);
        profileData.companyName = "Company " + generateRandomNumber(10, 19);
        profileData.companyAddress = "Company Address " + generateRandomNumber(20, 29) + " Location " + generateRandomNumber(30, 39);
        profileData.email = "user" + generateRandomNumber(40, 41) + "@mail.com";
        profileData.mobileNumber = "" + generateRandomNumber(100000000, 999999999);
        profileData.officeNumber = "" + generateRandomNumber(800000000, 999999999);
        profileData.fax = "" + generateRandomNumber(100000, 999999);
        profileData.isMyProfile = ProfileDataType.OTHERS.getValue();
        profileData.save();
    }

    private static int generateRandomNumber(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

}
