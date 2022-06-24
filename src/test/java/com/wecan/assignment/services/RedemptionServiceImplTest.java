package com.wecan.assignment.services;

import com.wecan.assignment.common.RedemptionType;
import com.wecan.assignment.common.VoucherType;
import com.wecan.assignment.controllers.dto.VoucherDTO;
import com.wecan.assignment.model.Redemption;
import com.wecan.assignment.model.Voucher;
import com.wecan.assignment.repositories.RedemptionRepository;
import com.wecan.assignment.repositories.VoucherRepository;
import com.wecan.assignment.services.impl.RedemptionServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class RedemptionServiceImplTest {

    @Mock
    VoucherRepository voucherRepository;

    @Mock
    RedemptionRepository redemptionRepository;

    @InjectMocks
    RedemptionServiceImpl redemptionServiceImpl;

    private Voucher mockAVoucher(Integer id) {
        Voucher voucher = new Voucher();
        voucher.setId(id);
        voucher.setCode("VoucherCode");
        voucher.setName("VoucherName");
        voucher.setActive(true);
        voucher.setVoucherType(VoucherType.FIXED_AMOUNT);
        voucher.setVoucherContent("10%");
        voucher.setCreatedOn(LocalDateTime.now());
        voucher.setUpdatedOn(LocalDateTime.now());
        return voucher;
    }

    private Redemption mockARedemption(Integer id, Voucher voucher) {
        Redemption redemption = new Redemption();
        redemption.setId(id);
        redemption.setVoucher(voucher);
        redemption.setCreatedOn(LocalDateTime.now());
        return redemption;
    }

    @Test
    public void shouldAbleToRedeemSINGLEVoucher() {

        // mock a valid SINGLE Voucher with redemptionTimes = 0
        Voucher voucher = mockAVoucher(1000);
        voucher.setRedemptionType(RedemptionType.SINGLE);
        voucher.setRedemptionTimes(0);
        Redemption redemption = mockARedemption(1, voucher);

        when(voucherRepository.getById(1000)).thenReturn(voucher);
        when(redemptionRepository.save(redemption)).thenReturn(redemption);

        ResponseEntity result = redemptionServiceImpl.redeemASingleVoucher(1000);
        VoucherDTO responseBody = (VoucherDTO) result.getBody();

        Assert.assertEquals(result.getStatusCode().toString(), "200 OK");
        Assert.assertEquals(responseBody.getRedemptionType(), RedemptionType.SINGLE);
        Assert.assertEquals(responseBody.getRedemptionTimes(), Integer.valueOf(1)); // redemptionTimes increase from 0 to 1
        Assert.assertEquals(responseBody.isAvailability(), false);
    }

    @Test
    public void shouldReturn409ExceptionIfSingleVoucherAlreadyUsed() {

        // mock an valid SINGLE Voucher with redemptionTimes = 1
        Voucher voucher = mockAVoucher(1000);
        voucher.setRedemptionType(RedemptionType.SINGLE);
        voucher.setRedemptionTimes(1);
        Redemption redemption = mockARedemption(1, voucher);

        when(voucherRepository.getById(1000)).thenReturn(voucher);
        when(redemptionRepository.save(redemption)).thenReturn(redemption);

        ResponseEntity result = redemptionServiceImpl.redeemASingleVoucher(1000);
        Assert.assertEquals(result.getStatusCode().toString(), "409 CONFLICT");
        Assert.assertEquals(result.getBody(), "Redeem voucher fail!! Voucher is invalid");
    }

    @Test
    public void shouldAbleToRedeemMultipleVoucher() {

        // mock a valid MULTIPLE Voucher with redemptionTimes = 1000
        Voucher voucher = mockAVoucher(1000);
        voucher.setRedemptionType(RedemptionType.MULTIPLE);
        voucher.setRedemptionTimes(1000);
        Redemption redemption = mockARedemption(1, voucher);

        when(voucherRepository.getById(1000)).thenReturn(voucher);
        when(redemptionRepository.save(redemption)).thenReturn(redemption);

        ResponseEntity result = redemptionServiceImpl.redeemASingleVoucher(1000);
        VoucherDTO responseBody = (VoucherDTO) result.getBody();

        Assert.assertEquals(result.getStatusCode().toString(), "200 OK");
        Assert.assertEquals(responseBody.getRedemptionType(), RedemptionType.MULTIPLE);
        Assert.assertEquals(responseBody.getRedemptionTimes(), Integer.valueOf(1001)); // redemptionTimes increase from 1000 to 1001
        Assert.assertEquals(responseBody.isAvailability(), true);
    }

    @Test
    public void shouldAbleToRedeemXTimesVoucher() {

        // mock a valid X-Times Voucher with redemptionTimes = 5
        Voucher voucher = mockAVoucher(1000);
        voucher.setRedemptionType(RedemptionType.X_TIMES);
        voucher.setRedemptionValue("10");
        voucher.setRedemptionTimes(5);
        Redemption redemption = mockARedemption(1, voucher);

        when(voucherRepository.getById(1000)).thenReturn(voucher);
        when(redemptionRepository.save(redemption)).thenReturn(redemption);

        ResponseEntity result = redemptionServiceImpl.redeemASingleVoucher(1000);
        VoucherDTO responseBody = (VoucherDTO) result.getBody();

        Assert.assertEquals(result.getStatusCode().toString(), "200 OK");
        Assert.assertEquals(responseBody.getRedemptionType(), RedemptionType.X_TIMES);
        Assert.assertEquals(responseBody.getRedemptionTimes(), Integer.valueOf(6)); // redemptionTimes increase from 5 to 6
        Assert.assertEquals(responseBody.isAvailability(), true);
    }

    @Test
    public void ShouldAbleToUpdateXTimeVoucherBecomeInvalidAfterItExceededMaximumValue() {

        // mock a valid X-Times Voucher with redemptionTimes = 9 and become unavailable after redeemed successfully
        Voucher voucher = mockAVoucher(1000);
        voucher.setRedemptionType(RedemptionType.X_TIMES);
        voucher.setRedemptionValue("10");
        voucher.setRedemptionTimes(9);
        Redemption redemption = mockARedemption(1, voucher);

        when(voucherRepository.getById(1000)).thenReturn(voucher);
        when(redemptionRepository.save(redemption)).thenReturn(redemption);

        ResponseEntity result = redemptionServiceImpl.redeemASingleVoucher(1000);
        VoucherDTO responseBody = (VoucherDTO) result.getBody();

        Assert.assertEquals(result.getStatusCode().toString(), "200 OK");
        Assert.assertEquals(responseBody.getRedemptionType(), RedemptionType.X_TIMES);
        Assert.assertEquals(responseBody.getRedemptionTimes(), Integer.valueOf(10)); // redemptionTimes increase from 5 to 6
        Assert.assertEquals(responseBody.isAvailability(), false);
    }

    @Test
    public void shouldReturn409ExceptionIfXTimesVoucherExceededTheNumber() {

        // mock an valid X-Times Voucher with redemptionTimes = 10
        Voucher voucher = mockAVoucher(1000);
        voucher.setRedemptionType(RedemptionType.X_TIMES);
        voucher.setRedemptionValue("10");
        voucher.setRedemptionTimes(10);
        Redemption redemption = mockARedemption(1, voucher);

        when(voucherRepository.getById(1000)).thenReturn(voucher);
        when(redemptionRepository.save(redemption)).thenReturn(redemption);

        ResponseEntity result = redemptionServiceImpl.redeemASingleVoucher(1000);
        Assert.assertEquals(result.getStatusCode().toString(), "409 CONFLICT");
        Assert.assertEquals(result.getBody(), "Redeem voucher fail!! Voucher is invalid");
    }

    @Test
    public void shouldAbleToRedeemBeforeCertainPointOfTimeVoucher() {

        // mock a valid BeforeCertainPointOfTime Voucher with time setting is 2030-06-24T07:55:02Z
        Voucher voucher = mockAVoucher(1000);
        voucher.setRedemptionType(RedemptionType.BEFORE_CERTAIN_POINT_OF_TIME);
        voucher.setRedemptionValue("2030-06-24T07:55:02Z");
        voucher.setRedemptionTimes(0);
        Redemption redemption = mockARedemption(1, voucher);

        when(voucherRepository.getById(1000)).thenReturn(voucher);
        when(redemptionRepository.save(redemption)).thenReturn(redemption);

        ResponseEntity result = redemptionServiceImpl.redeemASingleVoucher(1000);
        VoucherDTO responseBody = (VoucherDTO) result.getBody();

        Assert.assertEquals(result.getStatusCode().toString(), "200 OK");
        Assert.assertEquals(responseBody.getRedemptionType(), RedemptionType.BEFORE_CERTAIN_POINT_OF_TIME);
        Assert.assertEquals(responseBody.getRedemptionTimes(), Integer.valueOf(1)); // redemptionTimes increase from 5 to 6
        Assert.assertEquals(responseBody.isAvailability(), true);
    }

    @Test
    public void shouldReturn409ExceptionIfBeforeCertainPointOfTimeVoucherExpired() {

        // mock an expired BeforeCertainPointOfTime Voucher with time setting is 2019-06-24T07:55:02Z
        Voucher voucher = mockAVoucher(1000);
        voucher.setRedemptionType(RedemptionType.BEFORE_CERTAIN_POINT_OF_TIME);
        voucher.setRedemptionValue("2019-06-24T07:55:02Z");
        voucher.setRedemptionTimes(0);
        Redemption redemption = mockARedemption(1, voucher);

        when(voucherRepository.getById(1000)).thenReturn(voucher);
        when(redemptionRepository.save(redemption)).thenReturn(redemption);

        ResponseEntity result = redemptionServiceImpl.redeemASingleVoucher(1000);
        Assert.assertEquals(result.getStatusCode().toString(), "409 CONFLICT");
        Assert.assertEquals(result.getBody(), "Redeem voucher fail!! Voucher is invalid");
    }

}
