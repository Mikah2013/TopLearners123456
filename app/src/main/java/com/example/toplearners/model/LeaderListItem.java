package com.example.toplearners.model;

public class LeaderListItem {

    private String mName;
    private String mHours;
    private String mCountry;
    private String mBadgeUrl;

    @Override
    public String toString() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setHours(String hours) {
        this.mHours = hours;
    }

    public void setCountry(String country) {
        this.mCountry = country;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.mBadgeUrl = badgeUrl;
    }

    public String getName() {
        return mName;
    }

    public String getHours() {
        return mHours;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getBadgeUrl() {
        return mBadgeUrl;
    }
}
