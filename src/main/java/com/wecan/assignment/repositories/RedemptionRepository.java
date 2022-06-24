package com.wecan.assignment.repositories;

import com.wecan.assignment.model.Redemption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RedemptionRepository extends CrudRepository<Redemption, Integer> {

}
