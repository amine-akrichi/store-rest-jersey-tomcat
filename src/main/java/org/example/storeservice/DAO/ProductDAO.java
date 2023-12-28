package org.example.storeservice.DAO;



import jakarta.ws.rs.core.Response;
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


    public Response getProduct(int id) {
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
            return Response.status(201).entity(product).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public Response addProduct(Product product) {
        String query = "INSERT INTO Product VALUES ('" + product.getId() + "','"  + product.getName() + "', " + product.getPrice() + ", " + product.getQuantity() + ", " + product.getCategory_id() + ")";
        Statement statement = null;
        try {
            statement = instance.createStatement();
            statement.executeUpdate(query);
            return Response.status(201).entity(product).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Response updateProduct(int id,Product product) {
        String query = "UPDATE Product SET name = '" + product.getName() + "', price = " + product.getPrice() + ", quantity = " + product.getQuantity() + ", category_id = " + product.getCategory_id() + " WHERE id = " + id;
        Statement statement = null;
        try {
            statement = instance.createStatement();
            statement.executeUpdate(query);
            return Response.status(201).entity(product).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Response deleteProduct(int id) {
        String query = "DELETE FROM Product WHERE id = " + id;
        Statement statement = null;
        try {
            statement = instance.createStatement();
            statement.executeUpdate(query);
            return Response.status(201).entity("Product with id " + id + " deleted").build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
