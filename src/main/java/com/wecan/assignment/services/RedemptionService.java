package com.wecan.assignment.services;

import org.springframework.http.ResponseEntity;

public interface RedemptionService {
    ResponseEntity redeemASingleVoucher(Integer voucherId);
}
