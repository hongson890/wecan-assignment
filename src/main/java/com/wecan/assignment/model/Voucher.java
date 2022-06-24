package com.wecan.assignment.model;

import com.wecan.assignment.common.RedemptionType;
import com.wecan.assignment.common.VoucherType;
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

    // The status of a voucher and status = false if there it is fell into 3 cases:
    // - redemptionType = SINGLE and it has been used
    // - redemptionType = X_TIMES and it has been redeemed more than number of its value
    // - redemptionType = BEFORE_CERTAIN_POINT_OF_TIME and it has been expired
    private boolean status;

    // Used to enable or disable a voucher regardless of the voucher status being available or not
    // Note: only active vouchers can be redeemed
    private boolean active;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

}
