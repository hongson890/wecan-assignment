package com.wecan.assignment.controllers.dto;

import com.wecan.assignment.common.RedemptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoucherRequestDTO {
    private Integer id;
    private String code;
    private String name;
    private RedemptionType redemptionType;
    private boolean active;
}
