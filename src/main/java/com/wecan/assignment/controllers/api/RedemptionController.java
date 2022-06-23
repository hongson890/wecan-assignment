package com.wecan.assignment.controllers.api;

import com.wecan.assignment.controllers.dto.VoucherDTO;
import com.wecan.assignment.controllers.dto.VoucherRequestDTO;
import com.wecan.assignment.services.RedemptionService;
import com.wecan.assignment.services.VoucherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/redemption")
public class RedemptionController {

    private RedemptionService redemptionService;

    @Autowired
    public RedemptionController(RedemptionService redemptionService) {
        this.redemptionService = redemptionService;
    }

    @PostMapping("/{voucherId}")
    @ApiOperation("Redeem a single voucher")
    public ResponseEntity redeemASingleVoucher(@PathVariable("voucherId") Integer voucherId) {
        return redemptionService.redeemASingleVoucher(voucherId);
    }

}
