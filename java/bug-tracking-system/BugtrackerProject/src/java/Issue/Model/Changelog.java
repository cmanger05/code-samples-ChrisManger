/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Issue.Model;

import Core.Model.AbstractModel;
import Core.Util.DBConnectionsUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author chris
 */
public class Changelog extends AbstractModel {

    private Integer changelog_id;
    
    private Integer issue_id;
    
    private Integer user_id;
    
    private String comment;
    
    /**
     * Constructor
     */
    public Changelog() {
        this._defaultFieldName = "changelog_id";
        this._defaultTableName = "changelog";
    }
    
    /**
     * Returns the changelog id
     * @return 
     */
    @Override
    public Integer getId()
    {
        return this.getChangelogId();
    }

    /**
     * @return the changelog_id
     */
    protected Integer getChangelogId() {
        return changelog_id;
    }

    /**
     * @return the issue_id
     */
    protected Integer getIssueId() {
        return issue_id;
    }

    /**
     * @return the user_id
     */
    protected Integer getUserId() {
        return user_id;
    }

    /**
     * @return the comment
     */
    protected String getComment() {
        return comment;
    }

    /**
     * @param changelog_id the changelog_id to set
     */
    public void setChangelogId(Integer changelog_id) {
        this.changelog_id = changelog_id;
    }

    /**
     * @param issue_id the issue_id to set
     */
    public void setIssueId(Integer issue_id) {
        this.issue_id = issue_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUserId(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    
            /**
     * Returns the collection
     * @param issueIdToLoad
     * @return 
     */
    public ArrayList<HashMap> getCollectionByIssueId(Integer issueIdToLoad)
    {
        ArrayList<HashMap> records;
        records = new ArrayList<>();
        
        if(!this.getDefaultTableName().isEmpty()) {
            String queryLoadIssue = "SELECT * FROM " + this.getDefaultTableName() + " c "
                    + "INNER JOIN users u ON c.user_id = u.user_id WHERE c.issue_id = " + issueIdToLoad;
            try ( //connect to DB
                Connection currentCon = DBConnectionsUtil.getConnection();
                Statement stmt = currentCon.createStatement();
                ResultSet rs = stmt.executeQuery(queryLoadIssue)) {

                Integer issueId;
                String userFullName;
                String commentText;

                while(rs.next()) {
                    HashMap itemDetails = new HashMap();
                    issueId = rs.getInt("issue_id");
                    userFullName = rs.getString("first_name") + " " + rs.getString("last_name");
                    commentText = rs.getString("comment");
                    itemDetails.put("issue_id",issueId);
                    itemDetails.put("full_name",userFullName);
                    itemDetails.put("comment",commentText);
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
    
    @Override
    public boolean save()
    {
        boolean didSaveSuccessfully = false;
        String defaultDate = "0000-00-00 00:00:00";
        try {
            String queryString = "INSERT INTO changelog(changelog_id, issue_id, user_id, comment)"
                    + "VALUES(?,?,?,?)";
            Connection connection = DBConnectionsUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(queryString);

            Integer changelogId = this.getLastInsertedId() + 1;
            Integer issueId = this.getIssueId();
            Integer userId = this.getUserId();
            String commentText = this.getComment();


            if(this.isIntegerNotNull(changelogId) && this.isIntegerNotNull(issueId) 
                    && this.isIntegerNotNull(userId) && this.isStringNotNull(commentText)) {
                statement.setInt(1, changelogId);
                statement.setInt(2, issueId);
                statement.setInt(3, userId);
                statement.setString(4, commentText);

                int executedUpdate = statement.executeUpdate();
                statement.close();
                connection.close();
                didSaveSuccessfully = true;
            }     
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return didSaveSuccessfully;
    }
}
