package com.neosuniversity.demo;


import com.neosuniversity.demo.model.Customer;

import com.neosuniversity.demo.repository.CustomerDAORepositoryImpl;
import com.neosuniversity.demo.service.ServiceCustomer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDAOTest {

    @Mock
    private CustomerDAORepositoryImpl dao;

    @InjectMocks
    private ServiceCustomer service;

    @Mock
    private Customer customer;


    private Optional<Customer> customerOptional;

    private final Long ID= new Long(1);
    private final String NAME="HUGO";
    private final String LAST_NAME="HIDALGO";
    private final String ADDRESS="DOMICILIO CONOCIDO";



    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCustomer() {
        customer = new Customer(NAME,LAST_NAME);
        service.save(customer);
//        when(dao.save(customer)).thenReturn(customer);

        verify(dao, times(1)).save(customer);
        assertEquals(NAME, customer.getName());
        assertEquals(LAST_NAME, customer.getLastName());
    }

    @Test
    public void testFinByIdCustomer(){
        customer = new Customer(ID,NAME,LAST_NAME,ADDRESS);
        customerOptional= Optional.of(customer);
        service.findById(new Long(ID));
        //when(dao.findById(new Long(ID))).thenReturn(customerOptional);


        verify(dao, times(1)).findById(ID);
        assertEquals(ID, customerOptional.get().getId());
        assertEquals(NAME, customerOptional.get().getName());
        assertEquals(LAST_NAME, customerOptional.get().getLastName());
        assertEquals(ADDRESS, customerOptional.get().getAddress());
    }

    /*
    Optional<Customer> findById(Long id);

    List<Customer> findAll();
    long count();
    void delete(Customer customer);
    void update(Customer customer);
    boolean existById(Long id);
*/
}
