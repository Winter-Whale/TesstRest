package test.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import test.model.DTO.ProdDTO;
import test.model.Product;
import test.util.Sql;

import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ProdRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProdRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product saveProduct(Product productInput) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(Sql.SQL_INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, productInput.getName());
            ps.setInt(2, productInput.getPrice());
            ps.setInt(3, productInput.getWeight());
            ps.setTimestamp(4, Timestamp.valueOf(productInput.getCreated()));
            return ps;
        }, keyHolder);

        productInput.setId((int) keyHolder.getKeys().get("id"));
        return productInput;
    }

    public List<ProdDTO> findAll() {
        return jdbcTemplate.query(Sql.SQL_SELECT_ALL_PRODUCT, new BeanPropertyRowMapper<>(ProdDTO.class));
    }

    public boolean updateProduct(ProdDTO productInput) {
        return jdbcTemplate.update(Sql.SQL_UPDATE_PRODUCT_BY_ID,
                productInput.getName(),
                productInput.getPrice(),
                productInput.getWeight(),
                productInput.getId()) > 0;
    }

    public boolean deleteProductById(Integer id) {
        return jdbcTemplate.update(Sql.SQL_DELETE_PRODUCT_BY_ID, id) > 0;
    }

    public ProdDTO getProductById(Integer id) {
        return jdbcTemplate.queryForObject(Sql.SQL_SELECT_PRODUCT_BY_ID, new BeanPropertyRowMapper<>(ProdDTO.class), id);
    }
}
