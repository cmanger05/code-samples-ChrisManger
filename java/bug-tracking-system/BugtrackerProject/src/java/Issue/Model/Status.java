/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Issue.Model;

import Core.Model.AbstractModel;
import Core.Util.DBConnectionsUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author chris
 */
public class Status extends AbstractModel {

    private Integer status_id;
    
    private String status_name;
    
    /**
     * Constructor
     */
    public Status() {
        this._defaultFieldName = "status_id";
        this._defaultTableName = "status";
    }

    /**
     * Returns the value of the id field.
     * @return 
     */
    @Override
    public Integer getId()
    {
        return this.getStatusId();
    }
    
    /**
     * @return the status_id
     */
    public Integer getStatusId() {
        return status_id;
    }

    /**
     * @return the status_name
     */
    public String getStatusName() {
        return status_name;
    }

    /**
     * @param status_id the status_id to set
     */
    public void setStatusId(Integer status_id) {
        this.status_id = status_id;
    }

    /**
     * @param status_name the status_name to set
     */
    public void setStatusName(String status_name) {
        this.status_name = status_name;
    }
    
    /**
     * Returns the collection
     * @return 
     */
    @Override
    public ArrayList<HashMap> getCollection()
    {
        ArrayList<HashMap> records;
        records = new ArrayList<>();
        
        if(!this.getDefaultTableName().isEmpty()) {
            String queryLoadIssue = "SELECT * FROM " + this.getDefaultTableName();
            try ( //connect to DB
                Connection currentCon = DBConnectionsUtil.getConnection();
                Statement stmt = currentCon.createStatement();
                ResultSet rs = stmt.executeQuery(queryLoadIssue)) {

                Integer statusId;
                String statusName;

                while(rs.next()) {
                    HashMap itemDetails = new HashMap();
                    statusId = rs.getInt("status_id");
                    statusName = rs.getString("status_name");
                    itemDetails.put("status_id",statusId);
                    itemDetails.put("status_name",statusName);
                    records.add(itemDetails);
                }
                rs.close();
                stmt.close();
                currentCon.close();
            }
            catch (Exception ex) 
            {
                System.out.println("Log In failed: An Exception has occurred! " + ex);
            }
        }
        
        return records;
    }
}
