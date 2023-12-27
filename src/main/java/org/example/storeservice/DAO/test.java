package org.example.storeservice.DAO;


public class test {
    private static ProductDAO productDAO = new ProductDAO();
    public static void main(String[] args) {
        System.out.println(productDAO.getAllProducts());
    }
 }
