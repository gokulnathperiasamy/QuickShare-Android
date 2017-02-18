package com.quickshare.entity;

public enum ProfileDataType {
    OTHERS("0"),
    MY_PROFILE("1");
    private String value;

    private ProfileDataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}