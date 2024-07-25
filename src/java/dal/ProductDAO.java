/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductDAO {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Trading2022";
    private static final String USER = "your_database_user";
    private static final String PASSWORD = "your_database_password";

    static {
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception or throw a runtime exception
        }
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                BigDecimal price = rs.getBigDecimal("price");
                java.sql.Date releaseDate = rs.getDate("releaseDate");
                String description = rs.getString("describe");
                String image = rs.getString("image");
                int cid = rs.getInt("cid");
                
                Product product = new Product(id, name, quantity, price, releaseDate, description, image, cid);
                products.add(product);
            }
        }
        return products;
    }

    public Product getProductById(String id) throws SQLException {
        Product product = null;
        String sql = "SELECT * FROM Products WHERE ID = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    BigDecimal price = rs.getBigDecimal("price");
                    java.sql.Date releaseDate = rs.getDate("releaseDate");
                    String description = rs.getString("describe");
                    String image = rs.getString("image");
                    int cid = rs.getInt("cid");
                    
                    product = new Product(id, name, quantity, price, releaseDate, description, image, cid);
                }
            }
        }
        return product;
    }

    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Products (ID, name, quantity, price, releaseDate, describe, image, cid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, product.getId());
            pstmt.setString(2, product.getName());
            pstmt.setInt(3, product.getQuantity());
            pstmt.setBigDecimal(4, product.getPrice());
            pstmt.setDate(5, new java.sql.Date(product.getReleaseDate().getTime()));
            pstmt.setString(6, product.getDescription());
            pstmt.setString(7, product.getImage());
            pstmt.setInt(8, product.getCategoryId());
            pstmt.executeUpdate();
        }
    }

    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE Products SET name = ?, quantity = ?, price = ?, releaseDate = ?, describe = ?, image = ?, cid = ? WHERE ID = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, product.getName());
            pstmt.setInt(2, product.getQuantity());
            pstmt.setBigDecimal(3, product.getPrice());
            pstmt.setDate(4, new java.sql.Date(product.getReleaseDate().getTime()));
            pstmt.setString(5, product.getDescription());
            pstmt.setString(6, product.getImage());
            pstmt.setInt(7, product.getCategoryId());
            pstmt.setString(8, product.getId());
            pstmt.executeUpdate();
        }
    }

    public void deleteProduct(String id) throws SQLException {
        String sql = "DELETE FROM Products WHERE ID = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
