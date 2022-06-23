package com.wecan.assignment.services.impl;

import com.wecan.assignment.controllers.dto.VoucherDTO;
import com.wecan.assignment.services.RedemptionService;
import com.wecan.assignment.services.VoucherService;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RedemptionServiceImpl implements RedemptionService {

    private VoucherService voucherService;

    @Autowired
    public RedemptionServiceImpl(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @Override
    public ResponseEntity redeemASingleVoucher(Integer voucherId) {
        VoucherDTO voucherDTO = voucherService.getVoucherById(voucherId);

        if (voucherDTO.isActive() && voucherDTO.isStatus()) {
            switch (voucherDTO.getRedemptionType()) {
                case SINGLE:
                    // do something
                    break;
                case MULTIPLE:
                    // do something
                    break;
                case X_TIMES:
                    // do something
                    break;
                case BEFORE_CERTAIN_POINT_OF_TIME:
                    // do something
                    break;
                default:
                    // do something
                    break;
            }

        } else {
            // return false here
        }


        return null;
    }
}
