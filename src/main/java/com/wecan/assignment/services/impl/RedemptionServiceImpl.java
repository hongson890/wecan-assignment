package com.wecan.assignment.services.impl;

import com.wecan.assignment.mapper.VoucherMapper;
import com.wecan.assignment.model.Redemption;
import com.wecan.assignment.model.Voucher;
import com.wecan.assignment.repositories.RedemptionRepository;
import com.wecan.assignment.repositories.VoucherRepository;
import com.wecan.assignment.services.RedemptionService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static com.wecan.assignment.utils.VoucherUtil.isVoucherAvailability;

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
        if (voucher.isActive()
                && isVoucherAvailability(voucher.getRedemptionType(), voucher.getRedemptionValue(), voucher.getRedemptionTimes())) {
            logger.info("Voucher {} is valid", voucher.getCode());

            // add a new redemption record
            Redemption redemption = new Redemption();
            redemption.setRedemptionType(voucher.getRedemptionType());
            redemption.setVoucher(voucher);
            redemption.setCreatedOn(LocalDateTime.now());
            redemptionRepository.save(redemption);

            // update redemptionTimes
            voucher.setRedemptionTimes(voucher.getRedemptionTimes() + 1);
            voucherRepository.save(voucher);

            return ResponseEntity.ok(VoucherMapper.toDTO(voucher));
        } else {
            logger.info("Voucher {} is invalid", voucher.getCode());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Redeem voucher fail!! Voucher is invalid");
        }
    }

}
