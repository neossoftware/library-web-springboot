package com.neosuniversity.demo.bootstrap;

import com.neosuniversity.demo.model.Customer;
import com.neosuniversity.demo.repository.CustomerRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadInfo implements ApplicationRunner {

    private CustomerRepository customerRepository;

    public LoadInfo(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Customer c = new Customer("Mario", "Hidalgo");
        Customer c2 = new Customer("Jorge", "Hernandez");

        this.customerRepository.save(c);
        this.customerRepository.save(c2);

    }
}
