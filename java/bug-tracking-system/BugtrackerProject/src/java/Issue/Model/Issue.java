/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Issue.Model;

import Core.Model.AbstractModel;
import Core.Util.DBConnectionsUtil;
import Issue.Model.Save.ExistingIssue;
import Issue.Model.Save.NewIssue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author chris
 */
public class Issue extends AbstractModel {
    
    private Integer issue_id;
    
    private Integer project_id;
    
    private Integer owner_id;
    
    private String created_on;
    
    private Integer assignee_id;
    
    private String identified_date;
    
    private Integer identified_by_id;
    
    private Integer status_id;
    
    private Integer priority_id;
    
    private String title;
    
    private String description_full;
    
    private String description_summary;
    
    private String resolution_date_target;
    
    private String resolution_date_actual;
    
    protected ExistingIssue _resourceSaveExistingIssue;
    
    protected NewIssue _resourceSaveNewIssue;
    
    private String _commentText;
    
    private final ArrayList<String> _filters = new ArrayList<>();
    
    /**
     * Constructor
     */
    public Issue() {
        this._defaultFieldName = "issue_id";
        this._defaultTableName = "issue";
        this._resourceSaveExistingIssue = new ExistingIssue();
        this._resourceSaveNewIssue = new NewIssue();
    }

    /**
     * Returns the issue id
     * @return 
     */
    @Override
    public Integer getId()
    {
        return this.getIssueId();
    }
    
    /**
     * @return the issue_id
     */
    public Integer getIssueId() {
        return issue_id;
    }

    /**
     * @return the project_id
     */
    public Integer getProjectId() {
        return project_id;
    }

            /**
     * @return the owner_id
     */
    public Integer getOwnerId() {
        return owner_id;
    }

    /**
     * @return the assignee_id
     */
    public Integer getAssigneeId() {
        return assignee_id;
    }

    /**
     * @return the identified_by_id
     */
    public Integer getIdentifiedById() {
        return identified_by_id;
    }

    /**
     * @return the status_id
     */
    public Integer getStatusId() {
        return status_id;
    }

    /**
     * @return the priority_id
     */
    public Integer getPriorityId() {
        return priority_id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @return the description_full
     */
    public String getDescriptionFull() {
        return description_full;
    }

    /**
     * @return the description_summary
     */
    public String getDescriptionSummary() {
        return description_summary;
    }

    /**
     * @return the resolution_date_target
     */
    public String getResolutionDateTarget() {
        return resolution_date_target;
    }

    /**
     * @return the resolution_date_actual
     */
    public String getResolutionDateActual() {
        return resolution_date_actual;
    }

    /**
     * @param issue_id the issue_id to set
     */
    public void setIssueId(Integer issue_id) {
        this.issue_id = issue_id;
    }

    /**
     * @param project_id the project_id to set
     */
    public void setProjectId(Integer project_id) {
        this.project_id = project_id;
    }

    /**
     * @param owner_id the owner_id to set
     */
    public void setOwnerId(Integer owner_id) {
        this.owner_id = owner_id;
    }
    
    /**
     * @param assignee_id the assignee_id to set
     */
    public void setAssigneeId(Integer assignee_id) {
        this.assignee_id = assignee_id;
    }

    /**
     * @param identified_by_id the identified_by_id to set
     */
    public void setIdentifiedById(Integer identified_by_id) {
        this.identified_by_id = identified_by_id;
    }

    /**
     * @param status_id the status_id to set
     */
    public void setStatusId(Integer status_id) {
        this.status_id = status_id;
    }

    /**
     * @param priority_id the priority_id to set
     */
    public void setPriorityId(Integer priority_id) {
        this.priority_id = priority_id;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param description_full the description_full to set
     */
    public void setDescriptionFull(String description_full) {
        this.description_full = description_full;
    }

    /**
     * @param description_summary the description_summary to set
     */
    public void setDescriptionSummary(String description_summary) {
        this.description_summary = description_summary;
    }

    /**
     * @param resolution_date_target the resolution_date_target to set
     */
    public void setResolutionDateTarget(String resolution_date_target) {
        this.resolution_date_target = resolution_date_target;
    }

    /**
     * @param resolution_date_actual the resolution_date_actual to set
     */
    public void setResolutionDateActual(String resolution_date_actual) {
        this.resolution_date_actual = resolution_date_actual;
    }

    /**
     * @return the created_on
     */
    public String getCreatedOn() {
        return created_on;
    }

    /**
     * @return the identified_date
     */
    public String getIdentifiedDate() {
        return identified_date;
    }

    /**
     * @param created_on the created_on to set
     */
    public void setCreatedOn(String created_on) {
        this.created_on = created_on;
    }

    /**
     * Adds a filter to the collection
     * @param fieldName
     * @param fieldValue 
     */
    public void addFilter(String whereCondition) {
        this._filters.add(whereCondition);
    }
    
    /**
     * Returns the filters
     * @return 
     */
    private ArrayList<String> _getFilters()
    {
        return this._filters;
    }
    
    /**
     * @param identified_date the identified_date to set
     */
    public void setIdentifiedDate(String identified_date) {
        this.identified_date = identified_date;
    }
  
        /**
     * Loads by the id field
     * The reason why I'm creating a simple function instead of an abstract function
     * is because there are some models where no data every needs to be loaded.
     * @param idFieldValue
     */
    @Override
    public void loadById(Integer idFieldValue)
    {   
        try 
        {
            String queryLoadIssue = "SELECT * FROM issue WHERE issue_id = " + idFieldValue;
            //connect to DB 
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryLoadIssue);	        
            boolean more = rs.next();
	    if(more) { 
                Integer issueId = rs.getInt("issue_id");
                if(this.isIntegerNotNull(issueId)) {
                    this.setIssueId(issueId);
                }
                
                Integer projectId = rs.getInt("project_id");
                if(this.isIntegerNotNull(projectId)) {
                    this.setProjectId(projectId);
                }
                
                Integer ownerId = rs.getInt("owner_id");
                if(this.isIntegerNotNull(ownerId)) {
                    this.setOwnerId(ownerId);
                }
                
                String createdOn = rs.getString("created_on");
                if(this.isStringNotNull(createdOn)) {
                    this.setCreatedOn(createdOn);
                }
                
                Integer assigneeId = rs.getInt("assignee_id");
                if(this.isIntegerNotNull(assigneeId)) {
                    this.setAssigneeId(assigneeId);
                }
                
                Integer identifiedBydId = rs.getInt("identified_by_id");
                if(this.isIntegerNotNull(identifiedBydId)) {
                    this.setIdentifiedById(identifiedBydId);
                }
                
                String identifiedDate = rs.getString("identified_date");
                if(this.isStringNotNull(identifiedDate)) {
                    this.setIdentifiedDate(identifiedDate);
                }
                
                Integer statusId = rs.getInt("status_id");
                if(this.isIntegerNotNull(statusId)) {
                    this.setStatusId(statusId);
                }
                
                Integer priorityId = rs.getInt("priority_id");
                if(this.isIntegerNotNull(priorityId)) {
                    this.setPriorityId(priorityId);
                }
                
                String issueTitle = rs.getString("title");
                if(this.isStringNotNull(issueTitle)) {
                    this.setTitle(issueTitle);
                }
                
                String descriptionFull = rs.getString("description_full");
                if(this.isStringNotNull(descriptionFull)) {
                    this.setDescriptionFull(descriptionFull);
                }
                
                String descriptionSummary = rs.getString("description_summary");
                if(this.isStringNotNull(descriptionSummary)) {
                    this.setDescriptionSummary(descriptionSummary);
                }
                
                String resolutionDateTarget = rs.getString("resolution_date_target");
                if(this.isStringNotNull(resolutionDateTarget)) {
                    this.setResolutionDateTarget(resolutionDateTarget);
                }
                
                String resolutionDateActual = rs.getString("resolution_date_actual");
                if(this.isStringNotNull(resolutionDateActual)) {
                    this.setResolutionDateActual(resolutionDateActual);
                }
                
                this.setIsLoaded(true);
                currentCon.close();
                stmt.close();
                rs.close();
            }
        } 
        catch (Exception ex) 
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
    }
    
    /**
     * Saves a record to issue table
     * @return 
     */
    @Override
    public boolean save()
    {
        boolean didRecordSaveCorrectly;
        
        if(this.doesRecordAlreadyExist()) {
            didRecordSaveCorrectly = this.getResourceSaveExistingIssue().save(this);
        } else {
            didRecordSaveCorrectly = this.getResourceSaveNewIssue().save(this);
        }
        
        return didRecordSaveCorrectly;
    }

    /**
     * @return the _resourceSaveExistingIssue
     */
    public ExistingIssue getResourceSaveExistingIssue() {
        return _resourceSaveExistingIssue;
    }

    /**
     * @return the _resourceSaveNewIssue
     */
    public NewIssue getResourceSaveNewIssue() {
        return _resourceSaveNewIssue;
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
        String whereClause = this._renderWhereClause();
        String queryLoadIssue = " select i.issue_id, p.project_name, i.title, o.first_name || ' ' || o.last_name AS \"owner_name\""+
                                ", a.first_name || ' ' || a.last_name AS \"assignee_name\", s.status_name, p.priority_name,  i.resolution_date_target"+
                                " from issue i INNER JOIN project p ON i.project_id = p.PROJECT_ID " +
                                " INNER JOIN status s ON i.status_id = s.status_id" +
                                " INNER JOIN priority p ON i.priority_id = p.priority_id" + 
                                " INNER JOIN users a ON i.assignee_id = a.user_id" + 
                                " INNER JOIN users o ON i.owner_id = o.user_id" + whereClause;
        
            try ( //connect to DB
                Connection currentCon = DBConnectionsUtil.getConnection();
                Statement stmt = currentCon.createStatement();
                ResultSet rs = stmt.executeQuery(queryLoadIssue)) {

                Integer issueId;
                String projectName;
                String ownerName;
                String assigneeName;
                String resolutionDate;          
                String statusName;      
                String priorityName;
                String titleIssue;         
                
                while(rs.next()) {
                    HashMap issueDetails = new HashMap();
                   
                    issueId   = rs.getInt("issue_id");
                    projectName = rs.getString("project_name");
                    titleIssue = rs.getString("title"); 
                    ownerName = rs.getString("owner_name");
                    assigneeName = rs.getString("assignee_name");
                    statusName = rs.getString("status_name");
                    priorityName = rs.getString("priority_name");
                    resolutionDate = rs.getString("resolution_date_target");                       
                    
                    issueDetails.put("issue_id",issueId);
                    issueDetails.put("project_name",projectName);
                    issueDetails.put("owner_name", ownerName);
                    issueDetails.put("assignee_name", assigneeName);
                    issueDetails.put("status_name", statusName);
                    issueDetails.put("priority_name", priorityName);
                    issueDetails.put("title", titleIssue); 
                    issueDetails.put("resolution_date", resolutionDate);
                    
                    records.add(issueDetails);
                }
                rs.close();
                stmt.close();
                currentCon.close();
            }
            catch (Exception ex) 
            {
                System.out.println("Log In failed: An Exception has occurred! " + ex);
            }
        
        
        return records;
    }
    
    /**
     * Returns the where statement
     * @return 
     */
    private String _renderWhereClause()
    {
        String whereStatement = "";
        ArrayList<String> filters;
        filters = this._getFilters();
        boolean hasFilters = (filters.size() > 0);
        Integer numberFilters = filters.size();
        if(numberFilters > 0) {
            Integer numberFieldsProcesed = 0;
            for(String whereCondition : filters) {
                if(numberFieldsProcesed != 0) {
                    whereStatement += " AND ";
                } else {
                    whereStatement += " WHERE ";
                }
                whereStatement += whereCondition;
                numberFieldsProcesed++;
            }
        }
        
        return whereStatement;
    }

    /**
     * @return the _commentText
     */
    public String getCommentText() {
        return _commentText;
    }

    /**
     * @param _commentText the _commentText to set
     */
    public void setCommentText(String _commentText) {
        this._commentText = _commentText;
    }
}
