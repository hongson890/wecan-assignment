package com.wecan.assignment.exception;

public class VoucherExistedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public VoucherExistedException(String code){
        super("Voucher with code " + code + " already existed.");
    }
}
