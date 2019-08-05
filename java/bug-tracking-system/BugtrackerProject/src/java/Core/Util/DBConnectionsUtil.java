/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Util;

import java.sql.*;
import java.util.*;
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
             
            String url = "jdbc:derby://localhost:1527/BugTracker4"; 
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        
            try
            {            	
               connection = DriverManager.getConnection(url,"root","temp");                           
            }            
            catch (SQLException ex)
            {
               ex.printStackTrace();
            }
         }
         catch(Exception e)
         {
            System.out.println(e);
         }

      return connection;
   }    
}
