/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Connectivity.ConnectionMySQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

/**
 *
 * @author chris
 */
public class ProfileLoad extends ModelAbstractCrud {
   @Override
    public ProfileDataRepository execute(ProfileDataRepository data)
    {
        return this.loadRecordById(data);
    }
    
    public ProfileDataRepository loadRecordById(ProfileDataRepository data)
    {
        ProfileDataRepository recordLoaded = new ProfileDataRepository(null,"","","");
        try {
            Connection con = ConnectionMySQL.getCon();
            String query = "select * from profile where id="+data.getId();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query); 
            
            while(rs.next()){
                recordLoaded = new ProfileDataRepository(rs.getInt(1),
                        rs.getString(2),rs.getString(3), rs.getString(4));
            }
            
            this.setHasExecutedSucessfully(true);
        } catch(Exception e) {
            this.setHasExecutedSucessfully(false);
            System.out.println(e);
        }

        return recordLoaded;
    }
    
    public boolean doesRecordExist(Integer id) {
        ProfileDataRepository data = new ProfileDataRepository(id,"","","");
        ProfileDataRepository loadedRecord = this.loadRecordById(data);
        return (loadedRecord.getId() != null) ? true : false;
    }
    
    public HashMap<Integer, ProfileDataRepository> loadAllRecords()
    {
        HashMap<Integer, ProfileDataRepository> profilesList = new HashMap<>();
        try {
            Connection con = ConnectionMySQL.getCon();
            String query = "select * from profile";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            
            while(rs.next()){
                ProfileDataRepository newProfileDataRepository = new ProfileDataRepository(rs.getInt(1),
                        rs.getString(2),rs.getString(3), rs.getString(4));
                profilesList.put(rs.getInt(1), newProfileDataRepository);
            }
            this.setHasExecutedSucessfully(true);
        } catch(Exception e) {
            this.setHasExecutedSucessfully(false);
            System.out.println(e);
        }
        
        return profilesList;
    }
}
