package test.util;

public interface Sql {
    String SQL_INSERT_PRODUCT = "INSERT INTO product (id, name, price, weight, created) VALUES (DEFAULT, ? ,? ,?, ?)";
    String SQL_SELECT_ALL_PRODUCT = "SELECT* FROM product";
    String SQL_DELETE_PRODUCT_BY_ID = "DELETE FROM product WHERE ID = ?";
    String SQL_UPDATE_PRODUCT_BY_ID = "UPDATE product SET name = ?, price = ?, weight = ? WHERE id = ?";
    String SQL_SELECT_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?";

}
