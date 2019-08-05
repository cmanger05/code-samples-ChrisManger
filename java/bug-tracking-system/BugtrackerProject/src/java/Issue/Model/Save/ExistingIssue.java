/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Issue.Model.Save;

import Core.Util.DBConnectionsUtil;
import Issue.Model.Issue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author chris
 */
public class ExistingIssue {
    
    /**
     * Saves an existing issue
     * @param issueModel
     * @return 
     */
    public boolean save(Issue issueModel)
    {
        boolean didSaveSuccessfully = false;
        
        String defaultDate = "0000-00-00 00:00:00";
        try {
            Integer issueId = issueModel.getId();
            if(issueModel.isIntegerNotNull(issueId) && issueModel.doesRecordAlreadyExist()) {
                String queryString = "UPDATE issue SET project_id = ?, owner_id = ?, "
                        + "assignee_id = ?, identified_by_id = ?, identified_date = ?, status_id = ?, priority_id = ?, title = ?, "
                        + "description_full = ?, description_summary = ?, resolution_date_target = ?, resolution_date_actual = ? "
                        + "WHERE issue_id = ?";
                Connection connection = DBConnectionsUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(queryString);

                Integer projectId = issueModel.getProjectId();
                Integer ownerId = issueModel.getOwnerId();
                String createdOn = issueModel.getCreatedOn();
                Integer assigneeId = issueModel.getAssigneeId();
                Integer identifiedById = issueModel.getIdentifiedById();
                String identifiedDate = issueModel.getIdentifiedDate();
                Integer statusId = issueModel.getStatusId();
                Integer priorityId = issueModel.getPriorityId();
                String issueTitle = issueModel.getTitle();
                String descriptionFull = issueModel.getDescriptionFull();
                String descriptionSummary = issueModel.getDescriptionSummary();
                String resolutionDateTarget = issueModel.getResolutionDateTarget();
                if(!issueModel.isStringNotNull(resolutionDateTarget)) {
                    resolutionDateTarget = defaultDate;
                }
                String resolutionDateActual = issueModel.getResolutionDateActual();
                if(!issueModel.isStringNotNull(resolutionDateActual)) {
                    resolutionDateActual = defaultDate;
                }

                if(!issueModel.isStringNotNull(identifiedDate)) {
                    identifiedDate = defaultDate;
                }

                if(issueModel.isIntegerNotNull(projectId) && issueModel.isIntegerNotNull(ownerId) && issueModel.isIntegerNotNull(assigneeId)
                        && issueModel.isIntegerNotNull(identifiedById) && issueModel.isStringNotNull(identifiedDate) && issueModel.isIntegerNotNull(statusId) && issueModel.isIntegerNotNull(priorityId)
                        && issueModel.isStringNotNull(issueTitle) && issueModel.isStringNotNull(descriptionFull) && issueModel.isStringNotNull(descriptionSummary) && issueModel.isStringNotNull(resolutionDateTarget)
                        && issueModel.isStringNotNull(resolutionDateActual)) {
                    statement.setInt(1, projectId);
                    statement.setInt(2, ownerId);
                    statement.setInt(3, assigneeId);
                    statement.setInt(4, identifiedById);
                    statement.setString(5, identifiedDate);
                    statement.setInt(6, statusId);
                    statement.setInt(7, priorityId);
                    statement.setString(8, issueTitle);
                    statement.setString(9, descriptionFull);
                    statement.setString(10, descriptionSummary);
                    statement.setString(11, resolutionDateTarget);
                    statement.setString(12, resolutionDateActual);
                    statement.setInt(13, issueId);

                    int executedUpdate = statement.executeUpdate();
                    statement.close();
                    connection.close();
                    didSaveSuccessfully = true;
                }
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return didSaveSuccessfully;
    }
}
