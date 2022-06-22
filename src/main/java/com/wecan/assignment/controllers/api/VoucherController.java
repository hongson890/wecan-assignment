package com.wecan.assignment.controllers.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vouchers")
public class VoucherController {

    @GetMapping("/")
    @ApiOperation("Test")
    public String redeemVoucher() {
        return "Hello World";
    }
}
