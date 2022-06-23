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


}
