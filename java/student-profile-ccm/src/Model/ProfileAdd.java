/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Connectivity.ConnectionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Model adds a new profile record to the database
 * @author chris
 */
public class ProfileAdd extends ModelAbstractCrud {
    public ProfileDataRepository execute(ProfileDataRepository data)
    {
        try {
            Connection con = ConnectionMySQL.getCon();
            String query = "insert into profile(name,domain,address) value(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, data.getName());
            ps.setString(2, data.getDomain());
            ps.setString(3, data.getAddress());
            ps.executeUpdate();
            this.setHasExecutedSucessfully(true);
        } catch(Exception e) {
            this.setHasExecutedSucessfully(false);
            System.out.println(e);
        }
        
        return data;
    }
}
