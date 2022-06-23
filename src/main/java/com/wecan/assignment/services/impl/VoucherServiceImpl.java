package com.wecan.assignment.services.impl;

import com.wecan.assignment.controllers.dto.VoucherDTO;
import com.wecan.assignment.controllers.dto.VoucherRequestDTO;
import com.wecan.assignment.exception.VoucherBadParamsException;
import com.wecan.assignment.exception.VoucherExistedException;
import com.wecan.assignment.mapper.VoucherMapper;
import com.wecan.assignment.model.Voucher;
import com.wecan.assignment.repositories.VoucherRepository;
import com.wecan.assignment.services.VoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImpl implements VoucherService {
    private static final Logger logger = LoggerFactory.getLogger(VoucherServiceImpl.class);

    private VoucherRepository voucherRepository;

    private VoucherMapper voucherMapper;

    @Autowired
    public VoucherServiceImpl(VoucherRepository voucherRepository, VoucherMapper voucherMapper) {
        this.voucherRepository = voucherRepository;
        this.voucherMapper = voucherMapper;
    }

    @Override
    public List<VoucherDTO> getAllVouchers() {
        logger.info("getAllVouchers");
        return voucherRepository.findAll()
                .stream()
                .map(voucher -> voucherMapper.toDTO(voucher))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity createOrUpdateVoucher(VoucherRequestDTO voucherRequestDTO, boolean isUpdated) {
        logger.info("createOrUpdateVoucher: {}, isUpdate: {}", voucherRequestDTO, isUpdated);
        try {
            Voucher voucher = voucherMapper.toEntity(voucherRequestDTO);
            voucher.setCreatedOn(LocalDateTime.now());
            voucher.setUpdatedOn(LocalDateTime.now());
            Voucher v = voucherRepository.save(voucher);
            return ResponseEntity.ok(v);
        } catch (DataIntegrityViolationException e) {
            logger.error("", e);
            throw new VoucherBadParamsException(voucherRequestDTO);
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException(voucherRequestDTO.getCode());
        }
    }
}
