/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Connectivity.ConnectionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author chris
 */
public class ProfileUpdate extends ModelAbstractCrud {
   @Override
   public ProfileDataRepository execute(ProfileDataRepository data) {
        try {
            Connection con = ConnectionMySQL.getCon();
            String query = "update profile set name=?,domain=?,address=? where id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, data.getName());
            ps.setString(2, data.getDomain());
            ps.setString(3, data.getAddress());
            ps.setString(4, Integer.toString(data.getId()));
            ps.executeUpdate();
            this.setHasExecutedSucessfully(true);
        } catch (Exception e) {
            this.setHasExecutedSucessfully(false);
            System.out.println(e);
        }

        return data;
   }

    public ProfileDataRepository updateDomainOnly(ProfileDataRepository data){
        try {
            Connection con = ConnectionMySQL.getCon();
            String query = "update profile set domain=? where id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, data.getDomain());
            ps.setString(2, Integer.toString(data.getId()));
            ps.executeUpdate();
            this.setHasExecutedSucessfully(true);
        } catch (Exception e) {
            this.setHasExecutedSucessfully(false);
            System.out.println(e);
        }

        return data;
    }
    
    public ProfileDataRepository updateAddressOnly(ProfileDataRepository data) {
        try {
            Connection con = ConnectionMySQL.getCon();
            String query = "update profile set address=? where id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, data.getAddress());
            ps.setString(2, Integer.toString(data.getId()));
            ps.executeUpdate();
            this.setHasExecutedSucessfully(true);
        } catch(Exception e) {
            this.setHasExecutedSucessfully(false);
            System.out.println(e);
        }
        
        return data;
    }
    
    public boolean doesRecordExist(Integer id) {
        ProfileLoad modelLoadProfile = new ProfileLoad();
        return modelLoadProfile.doesRecordExist(id);
    }
}
