package com.highradius.connection; 
  
 import java.sql.Connection; 
 import java.sql.DriverManager; 
 import java.sql.ResultSet; 
 import java.sql.SQLException; 
 import java.sql.Statement; 
  
 public class DatabaseConnection { 
     public static void main(String[] args) { 
         Connection connection = null; 
         Statement statement = null; 
         ResultSet resultSet = null; 
  
         String url = "jdbc:mysql://localhost:3306/h2h_milestone_2"; 
         String username = "root"; 
         String password = "Riya@2652"; 
  
         try { 
             connection = DriverManager.getConnection(url, username, password); 
             statement = connection.createStatement(); 
  
             String sqlQuery = "SELECT * FROM h2h_oap"; 
             resultSet = statement.executeQuery(sqlQuery); 
  
             while (resultSet.next()) { 
                 double ORDER_AMOUNT = resultSet.getInt("ORDER_AMOUNT"); 
                 System.out.println(ORDER_AMOUNT); 
             } 
         } catch (SQLException e) { 
             e.printStackTrace(); 
         } finally { 
             try { 
                 if (resultSet != null) { 
                     resultSet.close(); 
                 } 
                 if (statement != null) { 
                     statement.close(); 
                 } 
                 if (connection != null) { 
                     connection.close(); 
                 } 
             } catch (SQLException e) { 
                 e.printStackTrace(); 
             } 
         } 
     } 
 }