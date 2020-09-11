package com.example.toplearners.model;

public class SkillListItem {

    private String mName;
    private String mScore;
    private String mCountry;
    private String mBadgeUrl;

    @Override
    public String toString() {
        return mName;
    }

    public String getName() {
        return mName;
    }

    public String getScore() {
        return mScore;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getBadgeUrl() {
        return mBadgeUrl;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setScore(String mScore) {
        this.mScore = mScore;
    }

    public void setCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public void setBadgeUrl(String mBadgeUrl) {
        this.mBadgeUrl = mBadgeUrl;
    }
}
