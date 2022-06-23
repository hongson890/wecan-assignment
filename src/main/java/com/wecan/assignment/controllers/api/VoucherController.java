package com.wecan.assignment.controllers.api;

import com.wecan.assignment.controllers.dto.VoucherDTO;
import com.wecan.assignment.controllers.dto.VoucherRequestDTO;
import com.wecan.assignment.services.VoucherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

    @GetMapping("/{id}")
    @ApiOperation("Get voucher by id")
    public VoucherDTO getVoucherById(@PathVariable("id") Integer id) {
        return voucherService.getVoucherById(id);
    }

    @PostMapping
    @ApiOperation("Create a new voucher")
    public ResponseEntity createAVoucher(@RequestBody final VoucherRequestDTO voucherRequestDTO) {
        return voucherService.createVoucher(voucherRequestDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update an existed voucher")
    public ResponseEntity updateExistedVoucher(@PathVariable("id") Integer id,
                                               @RequestBody final VoucherRequestDTO voucherRequestDTO) {
        return voucherService.updateVoucher(id, voucherRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete an existed voucher")
    public ResponseEntity deleteVoucher(@PathVariable("id") Integer id) {
        return voucherService.deleteVoucher(id);
    }
}
