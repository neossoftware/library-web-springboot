package com.neosuniversity.demo.controller;

import com.neosuniversity.demo.model.Customer;
import com.neosuniversity.demo.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
       this.customerRepository = customerRepository;
    }

    @GetMapping("/customer")
    public String getAllCustomer(Model model){

        List<Customer> c =  this.customerRepository.findAll();
        model.addAttribute("customers", c);

        model.addAttribute("customer", new Customer());

        return "listCustomer";
    }

    @PostMapping("/newCustomer")
    public String saveCustomer(final @Valid Customer customer, final BindingResult bindingResult,
                               final ModelMap model) {

        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");

            List<Customer> c =  this.customerRepository.findAll();
            model.addAttribute("customers", c);

            model.addAttribute("customer", customer);

            return "listCustomer";
        }

        System.out.println(customer.getName());

        return "redirect:/customer";
    }

    @GetMapping("/demo")
    public String demo(){
        return "demo";
    }
}
