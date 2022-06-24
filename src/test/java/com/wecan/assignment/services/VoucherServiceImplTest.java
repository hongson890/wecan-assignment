package com.wecan.assignment.services;

import com.wecan.assignment.common.RedemptionType;
import com.wecan.assignment.common.VoucherType;
import com.wecan.assignment.controllers.dto.VoucherDTO;
import com.wecan.assignment.controllers.dto.VoucherRequestDTO;
import com.wecan.assignment.exception.VoucherBadParamsException;
import com.wecan.assignment.mapper.VoucherMapper;
import com.wecan.assignment.model.Voucher;
import com.wecan.assignment.repositories.RedemptionRepository;
import com.wecan.assignment.repositories.VoucherRepository;
import com.wecan.assignment.services.impl.VoucherServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class VoucherServiceImplTest {

    @Mock
    VoucherRepository voucherRepository;

    @Mock
    RedemptionRepository redemptionRepository;

    @InjectMocks
    VoucherServiceImpl voucherServiceImpl;

    @Test
    public void shouldBeAbleToSearchAllVoucherOrById() {
        // 1. create mock data
        List<Voucher> mockVouchers = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            Voucher voucher = new Voucher();
            voucher.setId(i);
            voucher.setCode("VCode" + i);
            voucher.setName("VName" + i);
            voucher.setRedemptionType(RedemptionType.X_TIMES);
            voucher.setVoucherType(VoucherType.FIXED_AMOUNT);
            voucher.setRedemptionTimes(0);
            voucher.setRedemptionValue("100");
            mockVouchers.add(voucher);
        }

        // 2: check findAll
        when(voucherRepository.findAll()).thenReturn(mockVouchers);
        List<VoucherDTO> result = voucherServiceImpl.getAllVouchers();
        Assert.assertEquals(result.size(), 5);

        //3: Check getById
        when(voucherRepository.getById(2)).thenReturn(mockVouchers.get(2));
        VoucherDTO VoucherDTO = voucherServiceImpl.getVoucherById(2);
        Assert.assertEquals(VoucherDTO.getCode(), "VCode2");

    }

    @Test
    public void shouldBeAbleToCreateAVoucher() {
        VoucherRequestDTO voucherRequestDTO = new VoucherRequestDTO();
        voucherRequestDTO.setVoucherType(VoucherType.FIXED_AMOUNT);
        voucherRequestDTO.setCode("BETA");
        voucherRequestDTO.setName("Beta Voucher");
        voucherRequestDTO.setRedemptionType(RedemptionType.X_TIMES);
        voucherRequestDTO.setRedemptionValue("100");
        voucherRequestDTO.setActive(true);

        when(voucherRepository.save(any())).thenReturn(VoucherMapper.toEntity(voucherRequestDTO));

        ResponseEntity result = voucherServiceImpl.createVoucher(voucherRequestDTO);
        Assert.assertEquals(result.getStatusCode().toString(), "200 OK");
        VoucherDTO voucherDTO = (VoucherDTO) result.getBody();
        Assert.assertEquals(voucherDTO.getCode(), "BETA");
        Assert.assertEquals(voucherDTO.getRedemptionType(), RedemptionType.X_TIMES);
    }

    @Test
    public void shouldBeAbleToThrowsVoucherBadParamsExceptionIfTheVoucherCodeExisted() {
        VoucherRequestDTO voucherRequestDTO = new VoucherRequestDTO();
        voucherRequestDTO.setVoucherType(VoucherType.FIXED_AMOUNT);
        voucherRequestDTO.setCode("BETA");
        voucherRequestDTO.setName("Beta Voucher");
        voucherRequestDTO.setRedemptionType(RedemptionType.X_TIMES);
        voucherRequestDTO.setRedemptionValue("100");
        voucherRequestDTO.setActive(true);

        when(voucherRepository.save(any())).thenThrow(DataIntegrityViolationException.class);

        RuntimeException exception = assertThrows(VoucherBadParamsException.class, () -> {
            voucherServiceImpl.createVoucher(voucherRequestDTO);
        });
        assertTrue(exception.getMessage().equalsIgnoreCase("Bad Request: " + voucherRequestDTO.toString()));
    }

    @Test
    public void shouldBeAbleToUpdateExistedVoucher() {
        VoucherRequestDTO voucherRequestDTO = new VoucherRequestDTO();
        voucherRequestDTO.setVoucherType(VoucherType.FIXED_AMOUNT);
        voucherRequestDTO.setCode("BETA");
        voucherRequestDTO.setName("Beta Voucher");
        voucherRequestDTO.setRedemptionType(RedemptionType.X_TIMES);
        voucherRequestDTO.setRedemptionValue("100");
        voucherRequestDTO.setActive(true);


        when(voucherRepository.getById(1)).thenReturn(VoucherMapper.toEntity(voucherRequestDTO));
        when(voucherRepository.save(any())).thenReturn(VoucherMapper.toEntity(voucherRequestDTO));

        ResponseEntity result = voucherServiceImpl.updateVoucher(1, voucherRequestDTO);
        Assert.assertEquals(result.getStatusCode().toString(), "200 OK");
        VoucherDTO voucherDTO = (VoucherDTO) result.getBody();
        Assert.assertEquals(voucherDTO.getCode(), "BETA");
        Assert.assertEquals(voucherDTO.getRedemptionType(), RedemptionType.X_TIMES);
    }

    @Test
    public void shouldBeAbleToDeleteExistedVoucher() {
        ResponseEntity result = voucherServiceImpl.deleteVoucher(1);
        Assert.assertEquals(result.getStatusCode().toString(), "200 OK");
    }
}
