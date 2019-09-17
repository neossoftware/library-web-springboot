package com.neosuniversity.demo.repository;


import com.neosuniversity.demo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAORepository {

    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    long count();
    void delete(Customer customer);
    void update(Customer customer);
    boolean existById(Long id);

}
