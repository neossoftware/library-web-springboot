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

    private final String ID="id";
    private final String NAME="name";
    private final String LAST_NAME="lastName";
    private final String ADDRESS="address";
    private final String TABLE_CUSTOMER="customer";

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertCustomer;

    private RowMapper<Customer> customerMapper=
            (rs,rowNum)-> new Customer(
                rs.getLong(ID),
                rs.getString(NAME),
                rs.getString(LAST_NAME),
                rs.getString(ADDRESS));

    public CustomerDAORepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate= jdbcTemplate;
        insertCustomer= new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_CUSTOMER)
                .usingGeneratedKeyColumns(ID);
    }

    @Override
    public Customer save(Customer customer) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put(NAME,customer.getName());
        parameters.put(LAST_NAME,customer.getLastName());
        parameters.put(ADDRESS,customer.getAddress());
        Long newId = (Long) insertCustomer.executeAndReturnKey(parameters);
        customer.setId(Long.valueOf(newId));
        return customer;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        final String QUERY_FIND_BY_ID="SELECT * FROM customer WHERE id=?";
        if(!existById(id))return Optional.empty();
        return Optional.ofNullable((jdbcTemplate.queryForObject(QUERY_FIND_BY_ID, customerMapper,id)));

    }

    @Override
    public List<Customer> findAll() {
        final String QUERY_FIND_ALL="SELECT * FROM customer";
        return jdbcTemplate.query(QUERY_FIND_ALL, customerMapper);
    }

    @Override
    public long count() {
        final String QUERY_COUNT="SELECT COUNT(*) FROM customer";
        return jdbcTemplate.queryForObject(QUERY_COUNT,Long.class);
    }

    @Override
    public void delete(Customer customer) {
        final String QUERY_DELETE="DELETE FROM customer WHERE id=?";
        jdbcTemplate.update(QUERY_DELETE,customer.getId());
    }

    @Override
    public void update(Customer customer) {
        final String QUERY_UPDATE="UPDATE customer SET name=?,lastname=?,address=? WHERE id=?";
        jdbcTemplate.update(QUERY_UPDATE,customer.getName(),customer.getLastName(),customer.getAddress(),customer.getId());
    }

    @Override
    public boolean existById(Long id) {
        final String QUERY_EXIST_BY_ID="SELECT EXISTS(SELECT 1 FROM customer WHERE id=?)";
        return jdbcTemplate.queryForObject(QUERY_EXIST_BY_ID,Boolean.class,id);
    }
}
