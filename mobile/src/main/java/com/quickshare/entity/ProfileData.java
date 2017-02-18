package com.quickshare.entity;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

public class ProfileData extends DataSupport {

    public long id;

    @Column(unique = true)
    public String profileID;

    @Column(unique = false, defaultValue = "-")
    public String firstName;

    @Column(unique = false, defaultValue = "-")
    public String lastName;

    @Column(unique = false, defaultValue = "-")
    public String companyName;

    @Column(unique = false, defaultValue = "-")
    public String companyAddress;

    @Column(unique = false, defaultValue = "-")
    public String email;

    @Column(unique = false, defaultValue = "-")
    public String mobileNumber;

    @Column(unique = false, defaultValue = "-")
    public String officeNumber;

    @Column(unique = false, defaultValue = "-")
    public String fax;

    @Column(unique = false, defaultValue = "0")
    public String isMyProfile = ProfileDataType.OTHERS.getValue();
}
