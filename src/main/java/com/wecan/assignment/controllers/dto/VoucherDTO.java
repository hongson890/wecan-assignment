package com.wecan.assignment.controllers.dto;

import com.wecan.assignment.common.RedemptionType;
import com.wecan.assignment.common.VoucherType;
import com.wecan.assignment.utils.VoucherUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String voucherContent;
    private String redemptionValue;
    private Integer redemptionTimes;
    private boolean isAvailability;


    public VoucherDTO(Integer id, String code, String name, RedemptionType redemptionType, VoucherType voucherType) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.redemptionType = redemptionType;
        this.voucherType = voucherType;
    }
}
