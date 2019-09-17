package com.neosuniversity.demo.repository;

import com.neosuniversity.demo.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerDAORepositoryImpl implements CustomerDAORepository {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertCustomer;

    private RowMapper<Customer> customerMapper=
            (rs,rowNum)-> new Customer(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("lastName"),
                rs.getString("address"));

    public CustomerDAORepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate= jdbcTemplate;
        insertCustomer= new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("customer")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Customer save(Customer customer) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("name",customer.getName());
        parameters.put("lastName",customer.getLastName());
        parameters.put("address",customer.getAddress());
        Long newId = (Long) insertCustomer.executeAndReturnKey(parameters);
        customer.setId(Long.valueOf(newId));
        return customer;
    }

    @Override
    public Optional<Customer> findById(Long id) {

        if(!existById(id))return Optional.empty();
        return Optional.ofNullable((jdbcTemplate.queryForObject("SELECT * FROM customer WHERE id=?", customerMapper,id)));

    }

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query("SELECT * FROM customer", customerMapper);
    }

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM customer",Long.class);
    }

    @Override
    public void delete(Customer customer) {
        jdbcTemplate.update("DELETE FROM customer WHERE id=?",customer.getId());
    }

    @Override
    public void update(Customer customer) {
        jdbcTemplate.update("UPDATE customer SET name=?,lastname=?,address=?",customer.getName(),customer.getLastName(),customer.getAddress());
    }

    @Override
    public boolean existById(Long id) {
        return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT 1 FROM customer WHERE id=?)",Boolean.class,id);
    }
}
