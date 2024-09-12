/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connectivity;
import java.sql.*;

/**
 *
 * @author chris
 */
public class ConnectionMySQL {
    public static Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student","root","");
            return con;
        } catch(Exception e) {
            System.out.print("Connection failed:" + e);
        }
        return null;
    }
}
