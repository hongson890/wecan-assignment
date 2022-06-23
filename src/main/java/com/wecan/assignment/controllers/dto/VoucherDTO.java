package com.wecan.assignment.controllers.dto;

import com.wecan.assignment.common.RedemptionType;
import com.wecan.assignment.common.VoucherType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {

    private Integer id;
    private String code;
    private String name;
    private RedemptionType redemptionType;
    private VoucherType voucherType;
    private boolean active;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
