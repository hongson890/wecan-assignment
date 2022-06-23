package com.wecan.assignment.controllers.api;

import com.wecan.assignment.controllers.dto.VoucherDTO;
import com.wecan.assignment.controllers.dto.VoucherRequestDTO;
import com.wecan.assignment.services.VoucherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vouchers")
public class VoucherController {

    private VoucherService voucherService;

    @Autowired
    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping
    @ApiOperation("Get all vouchers")
    public List<VoucherDTO> getAllVouchers() {
        return voucherService.getAllVouchers();
    }

    @PostMapping
    @ApiOperation("Create a new voucher")
    public ResponseEntity createAVoucher(@RequestBody final VoucherRequestDTO voucherRequestDTO) {
        return voucherService.createOrUpdateVoucher(voucherRequestDTO, false);
    }
}
