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
@Table(name = "redemption")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Redemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="voucher_id", nullable=false)
    private Voucher voucher;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RedemptionType redemptionType;

    private LocalDateTime createdOn;

}
