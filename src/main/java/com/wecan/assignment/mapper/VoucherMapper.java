package com.wecan.assignment.mapper;

import com.wecan.assignment.controllers.dto.VoucherDTO;
import com.wecan.assignment.controllers.dto.VoucherRequestDTO;
import com.wecan.assignment.model.Voucher;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VoucherMapper {
    ModelMapper modelMapper = new ModelMapper();
    public VoucherDTO toDTO(Voucher voucher) {
        return modelMapper.map(voucher, VoucherDTO.class);
    }

    public Voucher toEntity(VoucherRequestDTO voucherRequestDTO) {
        return modelMapper.map(voucherRequestDTO, Voucher.class);
    }

    public Voucher toEntity(Voucher voucherDb, VoucherRequestDTO voucherRequestDTO) {
        voucherDb.setActive(voucherRequestDTO.isActive());
        voucherDb.setVoucherType(voucherRequestDTO.getVoucherType());
        voucherDb.setRedemptionType(voucherRequestDTO.getRedemptionType());
        voucherDb.setCode(voucherRequestDTO.getCode());
        voucherDb.setName(voucherRequestDTO.getName());
        voucherDb.setVoucherContent(voucherRequestDTO.getVoucherContent());
        voucherDb.setRedemptionValue(voucherRequestDTO.getRedemptionValue());
        voucherDb.setRedemptionTimes(voucherRequestDTO.getRedemptionTimes());
        return voucherDb;
    }

}
