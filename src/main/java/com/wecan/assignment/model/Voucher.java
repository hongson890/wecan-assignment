package com.wecan.assignment.model;

import com.wecan.assignment.common.RedemptionType;
import com.wecan.assignment.common.VoucherType;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "voucher")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String code;

    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VoucherType voucherType;

    // Used to set discount value, which is based on voucher type
    // i.e:
    // If voucherType == PERCENTAGE then voucherContent can be 5%, 10%, etc..
    // If voucherType == FIXED_AMOUNT then voucherContent can be 10, 20, etc..
    private String voucherContent;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RedemptionType redemptionType;

    // Used to set redemption value, which is based on redemption type
    // i.e:
    // If redemptionType == X_TIMES then redemptionValue can be a number i.e: 5, 10, etc..
    // If redemptionType == BEFORE_CERTAIN_POINT_OF_TIME then redemptionValue can be an UTC time string i.e: 2022-06-23T14:33:59Z
    // If redemptionType == SINGLE OR MULTIPLE then redemptionValue is null
    private String redemptionValue;

    // The total number of times the voucher has been redeemed
    private Integer redemptionTimes;

    // Used to enable or disable a voucher regardless of the voucher status being available or not
    // Note: only active vouchers can be redeemed
    private boolean active;

    @Version
    private Integer version;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

}
