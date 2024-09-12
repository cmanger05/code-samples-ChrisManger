/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Connectivity.ConnectionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Model.ProfileLoad;
/**
 * Deletes records from the student table
 * @author chris
 */
public class ProfileDelete extends ModelAbstractCrud {
   @Override
   public ProfileDataRepository execute(ProfileDataRepository data) {
       try {
        Connection con = ConnectionMySQL.getCon();
        String query = "delete from profile where id="+data.getId();
        PreparedStatement ps = con.prepareStatement(query);
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
