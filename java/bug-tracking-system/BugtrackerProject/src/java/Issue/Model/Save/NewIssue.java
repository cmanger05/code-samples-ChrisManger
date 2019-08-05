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
public class NewIssue {

    /**
     * Saves a new issue
     * @param issueModel
     * @return 
     */
    public boolean save(Issue issueModel)
    {
        boolean didSaveSuccessfully = false;
        String defaultDate = "0000-00-00 00:00:00";
        try {
            if(!issueModel.doesRecordAlreadyExist()) {
                String queryString = "INSERT INTO issue(issue_id, project_id, owner_id, created_on, "
                        + "assignee_id, identified_by_id, identified_date, status_id, priority_id, title,"
                        + "description_full, description_summary, resolution_date_target, resolution_date_actual)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                Connection connection = DBConnectionsUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(queryString);

                Integer issueId = issueModel.getLastInsertedId() + 1;
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

                if(issueModel.isIntegerNotNull(issueId) && issueModel.isIntegerNotNull(projectId) && issueModel.isIntegerNotNull(ownerId) && issueModel.isStringNotNull(createdOn) && issueModel.isIntegerNotNull(assigneeId)
                        && issueModel.isIntegerNotNull(identifiedById) && issueModel.isStringNotNull(identifiedDate) && issueModel.isIntegerNotNull(statusId) && issueModel.isIntegerNotNull(priorityId)
                        && issueModel.isStringNotNull(issueTitle) && issueModel.isStringNotNull(descriptionFull) && issueModel.isStringNotNull(descriptionSummary) && issueModel.isStringNotNull(resolutionDateTarget)
                        && issueModel.isStringNotNull(resolutionDateActual)) {
                    statement.setInt(1, issueId);
                    statement.setInt(2, projectId);
                    statement.setInt(3, ownerId);
                    statement.setString(4, createdOn);
                    statement.setInt(5, assigneeId);
                    statement.setInt(6, identifiedById);
                    statement.setString(7, identifiedDate);
                    statement.setInt(8, statusId);
                    statement.setInt(9, priorityId);
                    statement.setString(10, issueTitle);
                    statement.setString(11, descriptionFull);
                    statement.setString(12, descriptionSummary);
                    statement.setString(13, resolutionDateTarget);
                    statement.setString(14, resolutionDateActual);


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
