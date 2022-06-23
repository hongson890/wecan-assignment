package com.wecan.assignment.exception;


import com.wecan.assignment.controllers.dto.VoucherRequestDTO;

public class VoucherBadParamsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public VoucherBadParamsException(VoucherRequestDTO voucherRequestDTO){
        super("Bad Request: " + voucherRequestDTO.toString());
    }

    public VoucherBadParamsException(String message){
        super(message);
    }
}
