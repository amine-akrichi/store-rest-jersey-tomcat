package org.example.storeservice.DAO;

import jakarta.ws.rs.core.Response;
import org.example.storeservice.model.Category;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private static Connection instance = null;

    public CategoryDAO() {
        try {
            instance = Connect.getInstance();
        } catch (Exception e) {
            throw new RuntimeException("Connection to database failed");
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Category";
        Statement statement = null;
        try {
            statement = instance.createStatement();
            statement.executeQuery(query);
            while (statement.getResultSet().next()) {
                Category category = new Category(statement.getResultSet().getInt("id"),
                        statement.getResultSet().getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categories;
    }

    public Response getCategory(int id) {
        String query = "SELECT * FROM Category WHERE id = " + id;
        Statement statement = null;
        try {
            statement = instance.createStatement();
            statement.executeQuery(query);
            statement.getResultSet().next();
            Category category = new Category(statement.getResultSet().getInt("id"),
                    statement.getResultSet().getString("name"));
            return Response.status(201).entity(category).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Response addCategory(Category category) {
        String query = "INSERT INTO Category VALUES (" + category.getId() + ", '" + category.getName() + "')";
        Statement statement = null;
        try {
            statement = instance.createStatement();
            statement.executeUpdate(query);
            return Response.status(201).entity(category).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Response updateCategory(int id, Category category) {
        String query = "UPDATE Category SET name = '" + category.getName() + "' WHERE id = " + id;
        Statement statement = null;
        try {
            statement = instance.createStatement();
            statement.executeUpdate(query);
            return Response.status(201).entity(category).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Response deleteCategory(int id) {
        String query = "DELETE FROM Category WHERE id = " + id;
        Statement statement = null;
        try {
            statement = instance.createStatement();
            statement.executeUpdate(query);
            return Response.status(201).entity("Category with id " + id + " deleted").build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
