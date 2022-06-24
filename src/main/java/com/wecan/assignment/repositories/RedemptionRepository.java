package com.wecan.assignment.repositories;

import com.wecan.assignment.model.Redemption;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RedemptionRepository extends CrudRepository<Redemption, Integer> {

    @Modifying
    @Query(value = "delete from redemption where voucher_id = :voucherId ", nativeQuery = true)
    void deleteAllRedemptionFromAVoucher(@Param("voucherId") Integer voucherId);
}
