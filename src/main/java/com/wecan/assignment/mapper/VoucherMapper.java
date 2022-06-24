package com.wecan.assignment.mapper;

import com.wecan.assignment.controllers.dto.VoucherDTO;
import com.wecan.assignment.controllers.dto.VoucherRequestDTO;
import com.wecan.assignment.model.Voucher;
import com.wecan.assignment.utils.VoucherUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VoucherMapper {
    public static VoucherDTO toDTO(Voucher voucher) {
        ModelMapper modelMapper = new ModelMapper();
        VoucherDTO voucherDTO = modelMapper.map(voucher, VoucherDTO.class);
        voucherDTO.setAvailability(VoucherUtil.isVoucherAvailability(voucher.getRedemptionType(), voucher.getRedemptionValue(), voucher.getRedemptionTimes()));
        return voucherDTO;
    }

    public static Voucher toEntity(VoucherRequestDTO voucherRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(voucherRequestDTO, Voucher.class);
    }

    public static Voucher toEntity(Voucher voucherDb, VoucherRequestDTO voucherRequestDTO) {
        voucherDb.setActive(voucherRequestDTO.isActive());
        voucherDb.setVoucherType(voucherRequestDTO.getVoucherType());
        voucherDb.setRedemptionType(voucherRequestDTO.getRedemptionType());
        voucherDb.setCode(voucherRequestDTO.getCode());
        voucherDb.setName(voucherRequestDTO.getName());
        voucherDb.setVoucherContent(voucherRequestDTO.getVoucherContent());
        voucherDb.setRedemptionValue(voucherRequestDTO.getRedemptionValue());
        if (voucherRequestDTO.getRedemptionTimes() != null) {
            voucherDb.setRedemptionTimes(voucherRequestDTO.getRedemptionTimes());
        }
        return voucherDb;
    }

}
