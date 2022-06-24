package com.wecan.assignment.controllers.dto;

import com.wecan.assignment.common.RedemptionType;
import com.wecan.assignment.common.VoucherType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoucherRequestDTO {
    private String code;
    private String name;
    private RedemptionType redemptionType;
    private VoucherType voucherType;
    private boolean active;
    private String voucherContent;
    private String redemptionValue;
    private Integer redemptionTimes;
}
