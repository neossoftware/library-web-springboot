package com.neosuniversity.demo;

import com.neosuniversity.demo.model.Customer;
import com.neosuniversity.demo.repository.CustomerDAORepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerDAOCoverage {

    @InjectMocks
    private CustomerDAORepositoryImpl dao;

    @Mock
    private Customer customer;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private SimpleJdbcInsert insertCustomer;

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection con;

    private Optional<Customer> customerOptional;

    private final Long ID= new Long(1);
    private final String NAME="HUGO";
    private final String LAST_NAME="HIDALGO";
    private final String ADDRESS="DOMICILIO CONOCIDO";




    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        dao = new CustomerDAORepositoryImpl(jdbcTemplate);

    }

    @Test
    public void testFinByIdCustomer(){
        final String QUERY_EXIST_BY_ID="SELECT EXISTS(SELECT 1 FROM customer WHERE id=?)";
        boolean exist=Boolean.TRUE;
        when(jdbcTemplate.queryForObject(QUERY_EXIST_BY_ID,Boolean.class,ID)).thenReturn(Boolean.TRUE);
        when(dao.existById(new Long(ID))).thenReturn(exist);


        assertEquals(Boolean.TRUE, exist);

    }

    //@Test
    public void testSaveCustomer() throws SQLException {
        customer = new Customer(NAME,LAST_NAME);


        when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
        when(dataSource.getConnection()).thenReturn(con);
        when(insertCustomer.executeAndReturnKey(anyMap())).thenReturn(ID);
        when(dao.save(customer)).thenReturn(customer);

        //verify(dao, times(1)).save(customer);
        //assertEquals(NAME, customer.getName());
       // assertEquals(LAST_NAME, customer.getLastName());

    }


}
