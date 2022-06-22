package com.wecan.assignment.repositories;
import com.wecan.assignment.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

//    Page<Product> findAllByNameContains(String name, Pageable pageable);
//    Page<Product> findAll(Pageable pageable);
//    List<Product> findProductByCodeEquals(String code);
//    void deleteAllByCodeEquals(String code);
}
