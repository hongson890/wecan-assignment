package com.wecan.assignment.common;

public enum VoucherType {

    PERCENTAGE ("Percentage Discount Voucher"),
    FIXED_AMOUNT ("Fixed Amount Discount Voucher");

    private String voucherType;
    VoucherType(String redemptionType) {
        this.voucherType = voucherType;
    }
    public String getVoucherType() {
        return voucherType;
    }

    @Override
    public String toString() {
        return getVoucherType();
    }
}
