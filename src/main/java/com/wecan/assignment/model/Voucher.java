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
    private RedemptionType redemptionType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VoucherType voucherType;

    private boolean active;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

}
