package com.neosuniversity.demo.repository;

import com.neosuniversity.demo.model.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AuthorDAORepositoryImpl implements AuthorDAORepository{

    private final String ID="id";
    private final String NAME="name";
    private final String LAST_NAME="lastName";
    private final String TABLE_AUTHOR="author";

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertCustomer;

    private RowMapper<Author> authorMapper=
            (rs,rowNum)-> new Author(
                    rs.getLong(ID),
                    rs.getString(NAME),
                    rs.getString(LAST_NAME));

    public AuthorDAORepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate= jdbcTemplate;
        insertCustomer= new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_AUTHOR)
                .usingGeneratedKeyColumns(ID);
    }
    @Override
    public Author save(Author author) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put(NAME,author.getName());
        parameters.put(LAST_NAME,author.getLastName());
        Long newId = (Long) insertCustomer.executeAndReturnKey(parameters);
        author.setId(Long.valueOf(newId));
        return author;
    }

    @Override
    public Optional<Author> findById(Long id) {
        final String QUERY_FIND_BY_ID="SELECT * FROM author WHERE id=?";
        if(!existById(id))return Optional.empty();
        return Optional.ofNullable((jdbcTemplate.queryForObject(QUERY_FIND_BY_ID, authorMapper,id)));

    }

    @Override
    public List<Author> findAll() {
        final String QUERY_FIND_ALL="SELECT * FROM author";
        return jdbcTemplate.query(QUERY_FIND_ALL, authorMapper);
    }

    @Override
    public long count() {
        final String QUERY_COUNT="SELECT COUNT(*) FROM author";
        return jdbcTemplate.queryForObject(QUERY_COUNT,Long.class);
    }

    @Override
    public void delete(Author author) {
        final String QUERY_DELETE="DELETE FROM author WHERE id=?";
        jdbcTemplate.update(QUERY_DELETE,author.getId());
    }

    @Override
    public void update(Author author) {
        final String QUERY_UPDATE="UPDATE customer SET name=?,lastname=? WHERE id=?";
        jdbcTemplate.update(QUERY_UPDATE,author.getName(),author.getLastName(),author.getId());
    }

    @Override
    public boolean existById(Long id) {
        final String QUERY_EXIST_BY_ID="SELECT EXISTS(SELECT 1 FROM author WHERE id=?)";
        return jdbcTemplate.queryForObject(QUERY_EXIST_BY_ID,Boolean.class,id);
    }
}
