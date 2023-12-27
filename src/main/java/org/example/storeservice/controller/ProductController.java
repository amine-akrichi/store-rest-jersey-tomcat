package org.example.storeservice.controller;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
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
}
