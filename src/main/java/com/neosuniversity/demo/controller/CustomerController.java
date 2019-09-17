package com.neosuniversity.demo.controller;

import com.neosuniversity.demo.model.Customer;
import com.neosuniversity.demo.repository.CustomerDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerDAORepository customerDAORepository;

    @GetMapping("/customer")
    public String getAllCustomer(Model model){
        List<Customer> c =  this.customerDAORepository.findAll();
        model.addAttribute("customers", c);
        model.addAttribute("customer", new Customer());
        return "listCustomer";
    }

    @PostMapping("/newCustomer")
    public String saveCustomer( @Valid Customer customer, final BindingResult bindingResult,
                                final ModelMap model) {

        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            List<Customer> c =  this.customerDAORepository.findAll();
            model.addAttribute("customers", c);
            model.addAttribute("customer", new Customer());
            model.addAttribute("customer", customer);
            return "listCustomer";
        }else{

            customer = this.customerDAORepository.save(customer);
            System.out.println(customer.getName() + "-" +customer.getId()+"-"+customer.getLastName());
            return "redirect:/customer";
        }

    }
    @PostMapping("/deleteCustomer")
    public String deleteCustomer( @Valid Customer customer, final BindingResult bindingResult,
                                final ModelMap model) {

        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            List<Customer> c =  this.customerDAORepository.findAll();
            model.addAttribute("customers", c);
            model.addAttribute("customer", new Customer());
            model.addAttribute("customer", customer);
            return "listCustomer";
        }else{
            System.out.println("ID: " +customer.getId());
            Optional<Customer> response= this.customerDAORepository.findById(customer.getId());
            this.customerDAORepository.delete(response.get());
            return "redirect:/customer";
        }

    }
    @PostMapping("/loadDataCustomer")
    public String loadDataCustomer( @Valid Customer customer, final BindingResult bindingResult,
                                    final ModelMap model) {

        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            List<Customer> c =  this.customerDAORepository.findAll();
            model.addAttribute("customers", c);
            model.addAttribute("customer", new Customer());
            model.addAttribute("customer", customer);
            return "listCustomer";
        }else{
            System.out.println("ID: " +customer.getId());
            Optional<Customer> response= this.customerDAORepository.findById(customer.getId());
            System.out.println("UPDATE: " +response.get().getName());
            model.addAttribute("customer", response);
            return "loadDataCustomer";
        }

    }
    @PostMapping("/uploadCustomer")
    public String uploadCustomer( @Valid Customer customer, final BindingResult bindingResult,
                                    final ModelMap model) {

        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            List<Customer> c =  this.customerDAORepository.findAll();
            model.addAttribute("customers", c);
            model.addAttribute("customer", new Customer());
            model.addAttribute("customer", customer);
            return "listCustomer";
        }else{
            System.out.println("ID UPDATE: " +customer.getId());
            this.customerDAORepository.update(customer);
            return "redirect:/customer";
        }

    }
    @GetMapping("/demo")
    public String demo(){
        return "demo";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
