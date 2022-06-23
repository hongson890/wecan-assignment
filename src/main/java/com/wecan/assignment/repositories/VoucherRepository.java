package com.wecan.assignment.repositories;
import com.wecan.assignment.model.Voucher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VoucherRepository extends CrudRepository<Voucher, Integer> {

//    Page<Product> findAllByNameContains(String name, Pageable pageable);
//    Page<Product> findAll(Pageable pageable);
//    List<Product> findProductByCodeEquals(String code);
//    void deleteAllByCodeEquals(String code);

    List<Voucher> findAll();
}