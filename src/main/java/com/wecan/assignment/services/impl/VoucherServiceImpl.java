package com.wecan.assignment.services.impl;

import com.wecan.assignment.controllers.dto.VoucherDTO;
import com.wecan.assignment.controllers.dto.VoucherRequestDTO;
import com.wecan.assignment.exception.VoucherBadParamsException;
import com.wecan.assignment.exception.VoucherNotfoundException;
import com.wecan.assignment.mapper.VoucherMapper;
import com.wecan.assignment.model.Voucher;
import com.wecan.assignment.repositories.RedemptionRepository;
import com.wecan.assignment.repositories.VoucherRepository;
import com.wecan.assignment.services.VoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImpl implements VoucherService {
    private static final Logger logger = LoggerFactory.getLogger(VoucherServiceImpl.class);

    private VoucherRepository voucherRepository;

    private RedemptionRepository redemptionRepository;

    @Autowired
    public VoucherServiceImpl(VoucherRepository voucherRepository, RedemptionRepository redemptionRepository) {
        this.voucherRepository = voucherRepository;
        this.redemptionRepository = redemptionRepository;
    }

    @Override
    public List<VoucherDTO> getAllVouchers() {
        logger.info("getAllVouchers");
        return voucherRepository.findAll()
                .stream()
                .map(voucher -> VoucherMapper.toDTO(voucher))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponseEntity createVoucher(final VoucherRequestDTO voucherRequestDTO) {
        logger.info("createVoucher: {}", voucherRequestDTO);
        try {
            Voucher voucher = VoucherMapper.toEntity(voucherRequestDTO);
            voucher.setRedemptionTimes(0);
            voucher.setCreatedOn(LocalDateTime.now());
            voucher.setUpdatedOn(LocalDateTime.now());
            Voucher v = voucherRepository.save(voucher);
            return ResponseEntity.ok(VoucherMapper.toDTO(v));
        } catch (DataIntegrityViolationException e) {
            logger.error("", e);
            throw new VoucherBadParamsException(voucherRequestDTO);
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException(voucherRequestDTO.getCode());
        }
    }

    @Override
    @Transactional
    public ResponseEntity updateVoucher(Integer id, VoucherRequestDTO voucherRequestDTO) {
        logger.info("updateVoucher: id {}, voucherRequestDTO{}", id, voucherRequestDTO);
        try {
            Voucher voucherDb = voucherRepository.getById(id);
            voucherDb.setUpdatedOn(LocalDateTime.now());
            voucherDb = VoucherMapper.toEntity(voucherDb, voucherRequestDTO);
            voucherRepository.save(voucherDb);
            return ResponseEntity.ok(VoucherMapper.toDTO(voucherDb));
        } catch (DataIntegrityViolationException e) {
            logger.error("", e);
            throw new VoucherBadParamsException(voucherRequestDTO);
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException(voucherRequestDTO.getCode());
        }
    }

    @Override
    @Transactional
    public ResponseEntity deleteVoucher(Integer id) {
        logger.info("deleteVoucher: id {}", id);
        try {
            redemptionRepository.deleteAllRedemptionFromAVoucher(id);
            voucherRepository.deleteById(id);
            return ResponseEntity.ok("Deleted the voucher successfully");
        } catch (EmptyResultDataAccessException e) {
            logger.error("", e);
            throw new VoucherNotfoundException(id.toString());
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException(id.toString());
        }
    }

    @Override
    public VoucherDTO getVoucherById(Integer id) {
        logger.info("getVoucherById: {}", id);
        try {
            Voucher voucherDb = voucherRepository.getById(id);
            if (voucherDb == null) {
                throw new VoucherNotfoundException(id.toString());
            } else {
                VoucherDTO v = VoucherMapper.toDTO(voucherDb);
                return v;
            }
        } catch (EntityNotFoundException e) {
            logger.error("", e);
            throw new VoucherNotfoundException(id.toString());
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException(id.toString());
        }
    }
}
