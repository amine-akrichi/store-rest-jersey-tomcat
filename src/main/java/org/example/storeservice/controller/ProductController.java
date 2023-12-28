package org.example.storeservice.controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.storeservice.DAO.ProductDAO;
import org.example.storeservice.model.Product;

import java.awt.*;
import java.util.List;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {
    private ProductDAO productDAO = new ProductDAO();

    @GET
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @GET
    @Path("{id}")
    public Response GetProduct(@PathParam("id") int id) {
        return productDAO.getProduct(id);
    }

    @POST
    public Response addProduct(Product product) {
        return productDAO.addProduct(product);
    }

    @PUT
    @Path("{id}")
    public Response updateProduct(@PathParam("id") int id, Product product) {
        return productDAO.updateProduct(id, product);
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") int id) {
        return productDAO.deleteProduct(id);
    }
}
