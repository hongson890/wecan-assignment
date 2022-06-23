package com.wecan.assignment.exception;

public class VoucherNotfoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public VoucherNotfoundException(String code){
        super("Voucher with code " + code + " does not exist.");
    }
}
