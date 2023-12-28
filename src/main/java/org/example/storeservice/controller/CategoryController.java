package org.example.storeservice.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.storeservice.DAO.CategoryDAO;
import org.example.storeservice.model.Category;

import java.util.List;

@Path("/categories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryController {
    private CategoryDAO categoryDAO = new CategoryDAO();

    @GET
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
    @GET
    @Path("{id}")
    public Response getCategory(@PathParam("id") int id) {
        return categoryDAO.getCategory(id);
    }

    @POST
    public Response addCategory(Category category) {
        return categoryDAO.addCategory(category);
    }

    @PUT
    @Path("{id}")
    public Response updateCategory(@PathParam("id") int id, Category category) {
        return categoryDAO.updateCategory(id, category);
    }

    @DELETE
    @Path("{id}")
    public Response deleteCategory(@PathParam("id") int id) {
        return categoryDAO.deleteCategory(id);
    }


}
