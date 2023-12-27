package org.example.storeservice.DAO;



import org.example.storeservice.model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static Connection instance = null;
    public ProductDAO() {
        try {
            instance = Connect.getInstance();
        } catch (Exception e) {
            throw new RuntimeException("Connection to database failed");
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product";
        Statement statement = null;
        try {
            statement = instance.createStatement();
            statement.executeQuery(query);
            while (statement.getResultSet().next()) {
                Product product = new Product(statement.getResultSet().getInt("id"),
                        statement.getResultSet().getString("name"),
                        statement.getResultSet().getDouble("price"),
                        statement.getResultSet().getInt("quantity"),
                        statement.getResultSet().getInt("category_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }


    public Product getProduct(int id) {
        String query = "SELECT * FROM Product WHERE id = " + id;
        Statement statement = null;
        try {
            statement = instance.createStatement();
            statement.executeQuery(query);
            statement.getResultSet().next();
            Product product = new Product(statement.getResultSet().getInt("id"),
                    statement.getResultSet().getString("name"),
                    statement.getResultSet().getDouble("price"),
                    statement.getResultSet().getInt("quantity"),
                    statement.getResultSet().getInt("category_id"));
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void createProduct(int id, String name, double price, int quantity, int category_id) {
        String query = "INSERT INTO Product VALUES ('" + id + "','"  + name + "', " + price + ", " + quantity + ", " + category_id + ")";
        Statement statement = null;
        try {
            statement = instance.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProduct( int id, String name, double price, int quantity, int category_id) {
        String query = "UPDATE Product SET name = '" + name + "', price = " + price + ", quantity = " + quantity + ", category_id = " + category_id + " WHERE id = " + id;
        Statement statement = null;
        try {
            statement = instance.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(int id) {
        String query = "DELETE FROM Product WHERE id = " + id;
        Statement statement = null;
        try {
            statement = instance.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
