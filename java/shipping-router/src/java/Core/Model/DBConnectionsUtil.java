/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Model;

import java.sql.*;
/**
 *
 * @author sai
 */
public class DBConnectionsUtil 
{
    static Connection connection;
    static String url;
            
    public static Connection getConnection()
    {
        try
        {
             
            String urlDbCon = "jdbc:derby://localhost:1527/promise_shipping3"; 
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        
            try
            {            	
               connection = DriverManager.getConnection(urlDbCon,"temp","password123");                           
            }            
            catch (SQLException ex)
            {
            }
         }
         catch(ClassNotFoundException | InstantiationException | IllegalAccessException e)
         {
            System.out.println(e);
         }

      return connection;
   }    
}
