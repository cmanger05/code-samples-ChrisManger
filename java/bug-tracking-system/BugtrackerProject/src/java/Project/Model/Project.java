/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.Model;

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
public class Project extends AbstractModel {
     
    protected Integer project_id;
    
    protected String project_name;
    
    protected String date_start;
    
    protected String date_end;
    
    protected String date_end_actual;
    
    /**
     * Constructor
     */
    public Project() {
        this._defaultFieldName = "project_id";
        this._defaultTableName = "project";
    }

    /**
     * Returns the project id
     * @return 
     */
    @Override
    public Integer getId()
    {
        return this.getProjectId();
    }
    
    /**
     * @return the project_id
     */
    public Integer getProjectId() {
        return project_id;
    }

    /**
     * @return the project_name
     */
    public String getProjectName() {
        return project_name;
    }

    /**
     * @return the date_start
     */
    public String getDateStart() {
        return date_start;
    }

    /**
     * @return the date_end
     */
    public String getDateEnd() {
        return date_end;
    }

    /**
     * @return the date_end_actual
     */
    public String getDateEndActual() {
        return date_end_actual;
    }

    /**
     * @param project_id the project_id to set
     */
    public void setProjectId(Integer project_id) {
        this.project_id = project_id;
    }

    /**
     * @param project_name the project_name to set
     */
    public void setProjectName(String project_name) {
        this.project_name = project_name;
    }

    /**
     * @param date_start the date_start to set
     */
    public void setDateStart(String date_start) {
        this.date_start = date_start;
    }

    /**
     * @param date_end the date_end to set
     */
    public void setDateEnd(String date_end) {
        this.date_end = date_end;
    }

    /**
     * @param date_end_actual the date_end_actual to set
     */
    public void setDateEndActual(String date_end_actual) {
        this.date_end_actual = date_end_actual;
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

                Integer projectId;
                String projectName;

                while(rs.next()) {
                    HashMap itemDetails = new HashMap();
                    projectId = rs.getInt("project_id");
                    projectName = rs.getString("project_name");
                    itemDetails.put("project_id",projectId);
                    itemDetails.put("project_name",projectName);
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
