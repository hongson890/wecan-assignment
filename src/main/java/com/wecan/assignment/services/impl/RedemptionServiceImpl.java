package com.wecan.assignment.services.impl;

import com.wecan.assignment.controllers.dto.VoucherDTO;
import com.wecan.assignment.model.Redemption;
import com.wecan.assignment.model.Voucher;
import com.wecan.assignment.repositories.RedemptionRepository;
import com.wecan.assignment.repositories.VoucherRepository;
import com.wecan.assignment.services.RedemptionService;
import com.wecan.assignment.services.VoucherService;
import liquibase.pro.packaged.A;
import liquibase.pro.packaged.P;
import liquibase.pro.packaged.R;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class RedemptionServiceImpl implements RedemptionService {
    private static final Logger logger = LoggerFactory.getLogger(RedemptionServiceImpl.class);

    private VoucherRepository voucherRepository;

    private RedemptionRepository redemptionRepository;

    @Autowired
    public RedemptionServiceImpl(VoucherRepository voucherRepository, RedemptionRepository redemptionRepository) {
        this.voucherRepository = voucherRepository;
        this.redemptionRepository = redemptionRepository;
    }

    @Override
    @Transactional
    public ResponseEntity redeemASingleVoucher(Integer voucherId) {
        logger.info("[Start] redeemASingleVoucher - voucherId = {}", voucherId);
        Voucher voucher = voucherRepository.getById(voucherId);
        if (voucher.isActive() && voucher.isStatus() && isVoucherAvailability(voucher)) {
            logger.info("Voucher {} is valid", voucher.getCode());

            // add a new redemption record
            Redemption redemption = new Redemption();
            redemption.setRedemptionType(voucher.getRedemptionType());
            redemption.setVoucher(voucher);
            redemption.setCreatedOn(LocalDateTime.now());
            redemptionRepository.save(redemption);

            // update redemptionTimes of this voucher
            voucher.setRedemptionTimes(voucher.getRedemptionTimes() + 1);
            voucherRepository.save(voucher);

            return ResponseEntity.ok(redemption);
        } else {
            logger.info("Voucher {} is invalid", voucher.getCode());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Redeem voucher fail!! Voucher is invalid");
        }
    }

    private boolean isVoucherAvailability(Voucher voucher) {
        logger.info("Checking voucher {} availability", voucher.getCode());
        try {
            switch (voucher.getRedemptionType()) {
                case SINGLE:
                    // only valid if: redemptionTimes == 0
                    return voucher.getRedemptionTimes() == 0;
                case MULTIPLE:
                    // always valid
                    return true;
                case X_TIMES:
                    //  only valid if: redemptionTimes < Number(redemptionValue)
                    Integer redemptionValue = Integer.parseInt(voucher.getRedemptionValue());
                    return voucher.getRedemptionTimes() < redemptionValue;
                case BEFORE_CERTAIN_POINT_OF_TIME:
                    //  only valid if: Date.now() > Date(redemptionValue)
                    DateTime dt = DateTime.parse(voucher.getRedemptionValue());
                    return DateTime.now().isAfter(dt);
                default:
                    logger.error("Invalid Redemption Type: ", voucher.getRedemptionType());
                    return false;
            }
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }
}
