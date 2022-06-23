package com.wecan.assignment.common;

public enum RedemptionType {

    SINGLE ("Single Redemption"),
    MULTIPLE ("Multiple Redemption"),
    X_TIMES ("X times Redemption"),
    BEFORE_CERTAIN_POINT_OF_TIME("Before certain point of time");

    private String redemptionType;
    RedemptionType(String redemptionType) {
        this.redemptionType = redemptionType;
    }
    public String getRedemptionType() {
        return redemptionType;
    }

    @Override
    public String toString() {
        return getRedemptionType();
    }
}
