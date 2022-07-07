package com.example.ql_skateboarding;

import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBConnect{
    private Connection connection;

    public DBConnect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/skateboarding_javafx", "root", ""
            );
            System.out.println("Connect successfully!!!");

        } catch (SQLException e) {
            connection = null;
            System.out.println(e);
        }
    }
    public List<Skateboarding> getSkateboarding() {
        ArrayList<Skateboarding> skateboardings = new ArrayList<>();
        try {
            ResultSet result = connection.prepareStatement("SELECT * FROM skateboarding_store").executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String brand = result.getString("brand");
                int quantity = result.getInt("quantity");
                String image = result.getString("image");
                float price =result.getFloat("price");

                System.out.println(id);
                System.out.println(name);
                System.out.println(brand);
                System.out.println(quantity);
                System.out.println(image);
                System.out.println(price);

                skateboardings.add(new Skateboarding(id,name,brand,quantity,image,price));
            }
            System.out.println("Success!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return skateboardings;
    }
    public void insertSkateboarding(Skateboarding skateboarding){

        String sql = "INSERT INTO skateboarding_store (name,brand,quantity,image,price) VALUES ('"+skateboarding.name+"','"+skateboarding.brand+"',"+skateboarding.quantity+",'"+skateboarding.image+"',"+skateboarding.price+")";
        System.out.println(sql);
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateSkateboarding(Skateboarding skateboarding){
        String sql = "UPDATE skateboarding_store SET name = '"+skateboarding.name+"','"+skateboarding.brand+"',"+skateboarding.quantity+",'"+skateboarding.image+"',"+skateboarding.price+
                " WHERE id = "+ skateboarding.id;
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteSkateboarding (int id){
        String sql= "DELETE from skateboarding_store WHERE id="+id;
        System.out.println(sql);
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}