package com.wecan.assignment.services;

import com.wecan.assignment.controllers.dto.VoucherDTO;
import com.wecan.assignment.controllers.dto.VoucherRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VoucherService {
    List<VoucherDTO> getAllVouchers();
    ResponseEntity createOrUpdateVoucher(VoucherRequestDTO voucherRequestDTO, boolean isUpdated);
}
