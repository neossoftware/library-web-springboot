package com.neosuniversity.demo.service;

import com.neosuniversity.demo.model.Customer;
import com.neosuniversity.demo.repository.CustomerDAORepository;
import com.neosuniversity.demo.repository.CustomerDAORepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCustomer {

    @Autowired
    private CustomerDAORepositoryImpl dao;

    public Customer save(Customer customer){
        return dao.save(customer);
    }

    public Optional<Customer> findById(Long id){
        return dao.findById(id);
    }

    public List<Customer> findAll(){
        return dao.findAll();
    }

    public long count(){
        return dao.count();
    }

    public void delete(Customer customer){
        dao.delete(customer);
    }

    public void update(Customer customer){
        dao.update(customer);
    }

    public boolean existById(Long id){
        return dao.existById(id);
    }
}


