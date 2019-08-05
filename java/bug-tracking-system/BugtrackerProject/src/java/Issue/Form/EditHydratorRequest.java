/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Issue.Form;

import Core.Form.HydratorRequestAbstract;
import Core.Model.AbstractModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chris
 */
public class EditHydratorRequest extends HydratorRequestAbstract {

    /**
     * Constructor
     * @param request 
     * @param controller 
     */
    public EditHydratorRequest(HttpServletRequest request, HttpServlet controller) 
    {
        super(request, controller);
        this._mapDbFieldNameToRequestAttributeName("issue_id", "issueId");
        this._mapDbFieldNameToRequestAttributeName("project_id", "project");
        this._mapDbFieldNameToRequestAttributeName("owner_id", "owner");
        this._mapDbFieldNameToRequestAttributeName("assignee_id", "assignee");
        this._mapDbFieldNameToRequestAttributeName("identified_by_id", "identifiedby");
        this._mapDbFieldNameToRequestAttributeName("identified_date", "dateidentified");
        this._mapDbFieldNameToRequestAttributeName("status_id", "status");
        this._mapDbFieldNameToRequestAttributeName("priority_id", "priority");
        this._mapDbFieldNameToRequestAttributeName("title", "title");
        this._mapDbFieldNameToRequestAttributeName("description_full", "description");
        this._mapDbFieldNameToRequestAttributeName("description_summary", "summary");
        this._mapDbFieldNameToRequestAttributeName("resolution_date_target", "targetResolutionDate");
        this._mapDbFieldNameToRequestAttributeName("resolution_date_actual", "actualResolutionDate");
        
        this._mapFormFieldNameToFormFieldLabel("issueId", "Issue Id");
        this._mapFormFieldNameToFormFieldLabel("project", "Project");
        this._mapFormFieldNameToFormFieldLabel("owner", "Owner");
        this._mapFormFieldNameToFormFieldLabel("assignee", "Assignee");
        this._mapFormFieldNameToFormFieldLabel("identifiedby", "Identified By");
        this._mapFormFieldNameToFormFieldLabel("dateidentified", "Date Identified");
        this._mapFormFieldNameToFormFieldLabel("status", "Status");
        this._mapFormFieldNameToFormFieldLabel("priority", "Priority");
        this._mapFormFieldNameToFormFieldLabel("title", "Title");
        this._mapFormFieldNameToFormFieldLabel("description", "Description");
        this._mapFormFieldNameToFormFieldLabel("summary", "Summary");
        this._mapFormFieldNameToFormFieldLabel("targetResolutionDate", "Target Resolution Date");
        this._mapFormFieldNameToFormFieldLabel("actualResolutionDate", "Actual Resolution Date");
    }

    @Override
    public void hydrate(AbstractModel model) 
    {
        Issue.Model.Issue modelIssue = (Issue.Model.Issue) model;
        
        Integer issueId = modelIssue.getIssueId();
        String issueIdRequestAttributeName = this._getRequestAttributeNameByDbFieldName("issue_id");
        if(modelIssue.isIntegerNotNull(issueId) && modelIssue.isStringNotNull(issueIdRequestAttributeName)) {
            this._getController().getServletContext().setAttribute(issueIdRequestAttributeName, issueId);           
        }

        Integer projectId = modelIssue.getProjectId();
        String projectIdRequestAttributeName = this._getRequestAttributeNameByDbFieldName("project_id");
        if(modelIssue.isIntegerNotNull(projectId) && modelIssue.isStringNotNull(projectIdRequestAttributeName)) {
            this._getController().getServletContext().setAttribute(projectIdRequestAttributeName, projectId);
        }

        Integer ownerId = modelIssue.getOwnerId();
        String ownerIdRequestAttributeName = this._getRequestAttributeNameByDbFieldName("owner_id");
        if(modelIssue.isIntegerNotNull(ownerId) && modelIssue.isStringNotNull(ownerIdRequestAttributeName)) {
            this._getController().getServletContext().setAttribute(ownerIdRequestAttributeName, ownerId);
        }

        String createdOn = modelIssue.getCreatedOn();
        String createdOnRequestAttributeName = this._getRequestAttributeNameByDbFieldName("created_on");
        if(modelIssue.isStringNotNull(createdOn) && modelIssue.isStringNotNull(createdOnRequestAttributeName)) {
            String createdOnFormatted = createdOn;
            if(createdOnFormatted.length() == 10) {
                createdOnFormatted = createdOnFormatted + " 00:00:00";
            }
            this._getController().getServletContext().setAttribute(createdOnRequestAttributeName, createdOnFormatted);
        }

        Integer assigneeId = modelIssue.getAssigneeId();
        String assigneeIdRequestAttributeName = this._getRequestAttributeNameByDbFieldName("assignee_id");
        if(modelIssue.isIntegerNotNull(assigneeId) && modelIssue.isStringNotNull(assigneeIdRequestAttributeName)) {
            this._getController().getServletContext().setAttribute(assigneeIdRequestAttributeName, assigneeId);
        }

        Integer identifiedBydId = modelIssue.getIdentifiedById();
        String identifiedByIdRequestAttributeName = this._getRequestAttributeNameByDbFieldName("identified_by_id");
        if(modelIssue.isIntegerNotNull(identifiedBydId) && modelIssue.isStringNotNull(identifiedByIdRequestAttributeName)) {
            this._getController().getServletContext().setAttribute(identifiedByIdRequestAttributeName, identifiedBydId);
        }

        String identifiedDate = modelIssue.getIdentifiedDate();
        String identifiedDateRequestAttributeName = this._getRequestAttributeNameByDbFieldName("identified_date");
        if(modelIssue.isStringNotNull(identifiedDate) && modelIssue.isStringNotNull(identifiedDateRequestAttributeName)) {
            String identifiedDateFormatted = identifiedDate;
            if(identifiedDateFormatted.length() == 10) {
                identifiedDateFormatted = identifiedDateFormatted + " 00:00:00";
            }
            this._getController().getServletContext().setAttribute(identifiedDateRequestAttributeName, identifiedDateFormatted);
        }

        Integer statusId = modelIssue.getStatusId();
        String statusIdRequestAttributeName = this._getRequestAttributeNameByDbFieldName("status_id");
        if(modelIssue.isIntegerNotNull(statusId) && modelIssue.isStringNotNull(statusIdRequestAttributeName)) {
            this._getController().getServletContext().setAttribute(statusIdRequestAttributeName, statusId);
        }

        Integer priorityId = modelIssue.getPriorityId();
        String priorityIdRequestAttributeName = this._getRequestAttributeNameByDbFieldName("priority_id");
        if(modelIssue.isIntegerNotNull(priorityId) && modelIssue.isStringNotNull(priorityIdRequestAttributeName)) {
            this._getController().getServletContext().setAttribute(priorityIdRequestAttributeName, priorityId);
        }

        String issueTitle = modelIssue.getTitle();
        String titleRequestAttributeName = this._getRequestAttributeNameByDbFieldName("title");
        if(modelIssue.isStringNotNull(issueTitle) && modelIssue.isStringNotNull(titleRequestAttributeName)) {
            this._getController().getServletContext().setAttribute(titleRequestAttributeName, issueTitle);
        }

        String descriptionFull = modelIssue.getDescriptionFull();
        String descriptionFullRequestAttributeName = this._getRequestAttributeNameByDbFieldName("description_full");
        if(modelIssue.isStringNotNull(descriptionFull) && modelIssue.isStringNotNull(descriptionFullRequestAttributeName)) {
            this._getController().getServletContext().setAttribute(descriptionFullRequestAttributeName, descriptionFull);
        }

        String descriptionSummary = modelIssue.getDescriptionSummary();
        String descriptionSummaryRequestAttributeName = this._getRequestAttributeNameByDbFieldName("description_summary");
        if(modelIssue.isStringNotNull(descriptionSummary) && modelIssue.isStringNotNull(descriptionSummaryRequestAttributeName)) {
            this._getController().getServletContext().setAttribute(descriptionSummaryRequestAttributeName, descriptionSummary);
        }

        String resolutionDateTarget = modelIssue.getResolutionDateTarget();
        String resolutionDateTargetRequestAttributeName = this._getRequestAttributeNameByDbFieldName("resolution_date_target");
        if(modelIssue.isStringNotNull(resolutionDateTarget) && modelIssue.isStringNotNull(resolutionDateTargetRequestAttributeName)) {
            String resolutionDateTargetFormatted = resolutionDateTarget;
            if(resolutionDateTargetFormatted.length() == 10) {
                resolutionDateTargetFormatted =  resolutionDateTargetFormatted + " 00:00:00";
            }
            
            this._getController().getServletContext().setAttribute(resolutionDateTargetRequestAttributeName, resolutionDateTargetFormatted);
        }

        String resolutionDateActual = modelIssue.getResolutionDateActual();
        String resolutionDateActualRequestAttributeName = this._getRequestAttributeNameByDbFieldName("resolution_date_actual");
        if(modelIssue.isStringNotNull(resolutionDateActual) && modelIssue.isStringNotNull(resolutionDateActualRequestAttributeName)) {
            String resolutionDateActualFormatted = resolutionDateActual;
            if(resolutionDateActualFormatted.length() == 10) {
                resolutionDateActualFormatted =  resolutionDateActualFormatted + " 00:00:00";
            }
            
            this._getController().getServletContext().setAttribute(resolutionDateActualRequestAttributeName, resolutionDateActualFormatted);
        }
    }  
}
