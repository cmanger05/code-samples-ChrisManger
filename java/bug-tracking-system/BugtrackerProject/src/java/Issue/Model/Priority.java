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
public class Priority extends AbstractModel {

    private Integer priority_id;
    
    private String priority_name;
    
    /**
     * Constructor
     */
    public Priority() {
        this._defaultFieldName = "priority_id";
        this._defaultTableName = "priority";
    }
    
    /**
     * Returns the id
     * @return 
     */
    @Override
    public Integer getId()
    {
        return this.getPriorityId();
    }
    
    /**
     * @return the priority_name
     */
    public String getPriorityName() {
        return priority_name;
    }

    /**
     * @param priority_name the priority_name to set
     */
    public void setPriorityName(String priority_name) {
        this.priority_name = priority_name;
    }

    /**
     * @return the priority_id
     */
    public Integer getPriorityId() {
        return priority_id;
    }

    /**
     * @param priority_id the priority_id to set
     */
    public void setPriorityId(Integer priority_id) {
        this.priority_id = priority_id;
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

                Integer priorityId;
                String priorityName;

                while(rs.next()) {
                    HashMap itemDetails = new HashMap();
                    priorityId = rs.getInt("priority_id");
                    priorityName = rs.getString("priority_name");
                    itemDetails.put("priority_id",priorityId);
                    itemDetails.put("priority_name",priorityName);
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
