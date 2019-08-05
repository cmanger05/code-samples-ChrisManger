/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Issue.Form;

import Core.Controller.AbstractFormController;
import Core.Form.FormAbstract;
import Core.Form.HydratorAbstract;
import Core.Form.HydratorRequestAbstract;
import Core.Model.AbstractModel;
import Issue.Model.Changelog;
import Issue.Model.Issue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chris
 */
public class EditHydrator extends HydratorAbstract {

    /**
     * Constructor
     * @param form
     * @param model
     * @param controller
     * @param request 
     * @param requestHydrator 
     */
    public EditHydrator(FormAbstract form, AbstractModel model, AbstractFormController controller,
            HttpServletRequest request, HydratorRequestAbstract requestHydrator) {
        super(form, model, controller, request, requestHydrator);
    }

    /**
     * Populates the issue model with data from the submitted form
     * @return 
     */
    @Override
    public AbstractModel hydrate() {
        FormAbstract editForm = this.getForm();
        Issue modelIssue = (Issue)this._getModel();
        String defaultDateValue = "0000-00-00 00:00:";
        Integer issueId;
        
        if(this.isAttributeInteger("issueId", false)) {
            String issueIdRaw = this.getAttributeValue("issueId","");
            issueId = this.convertToInteger(issueIdRaw);
            modelIssue.setIssueId(issueId);
            this.hydrateRequestAttribute("issueId", issueId);
        }
        
        if(this.isAttributeInteger("project", true)) {
            String projectIdRaw = this.getAttributeValue("project","");
            Integer projectId = this.convertToInteger(projectIdRaw);
            modelIssue.setProjectId(projectId);
            this.hydrateRequestAttribute("project", projectId);
        }
        
        if(this.isAttributeInteger("owner", true)) {
            String ownerIdRaw = this.getAttributeValue("owner","");
            Integer ownerId = this.convertToInteger(ownerIdRaw);
            modelIssue.setOwnerId(ownerId);
            this.hydrateRequestAttribute("owner", ownerId);
        }
       
        if(this.isAttributeInteger("assignee", true)) {
            String userIdRaw = this.getAttributeValue("assignee","");
            Integer userId = this.convertToInteger(userIdRaw);
            modelIssue.setAssigneeId(userId);
            this.hydrateRequestAttribute("assignee",userId);
        }
        
        if(this.isAttributeInteger("identifiedby", true)) {
            String identifiedByIdRaw = this.getAttributeValue("identifiedby","");
            Integer identifiedById = this.convertToInteger(identifiedByIdRaw);
            modelIssue.setIdentifiedById(identifiedById);
            this.hydrateRequestAttribute("identifiedby",identifiedById);
        }
        
        if(this.isAttributeInteger("priority", true)) {
            String priorityIdRaw = this.getAttributeValue("priority","");
            Integer priorityId = this.convertToInteger(priorityIdRaw);
            modelIssue.setPriorityId(priorityId);
            this.hydrateRequestAttribute("priority",priorityId);
        }
                
        if(this.isAttributeInteger("status", true)) {
            String statusIdRaw = this.getAttributeValue("status","");
            Integer statusId = this.convertToInteger(statusIdRaw);
            modelIssue.setStatusId(statusId);
            this.hydrateRequestAttribute("status", statusId);
        }
        
        if(!this.isAttributeEmptyString("title", true)) {
            String titleValue = this.getAttributeValue("title","");
            modelIssue.setTitle(titleValue);
            this.hydrateRequestAttribute("title", titleValue);
        }
        
        if(!this.isAttributeEmptyString("description", true)) {
            String descriptionFullValue = this.getAttributeValue("description","");
            modelIssue.setDescriptionFull(descriptionFullValue);
            this.hydrateRequestAttribute("description", descriptionFullValue);
        }
        
        if(!this.isAttributeEmptyString("summary", true)) {
            String descriptionSummaryValue = this.getAttributeValue("summary","");
            modelIssue.setDescriptionSummary(descriptionSummaryValue);
            this.hydrateRequestAttribute("summary",descriptionSummaryValue);
        }
        
        if(!this.isAttributeEmptyString("commentText", false)) {
            String commtentTextValue = this.getAttributeValue("commentText","");
            modelIssue.setCommentText(commtentTextValue);
            this.hydrateRequestAttribute("commentText",commtentTextValue);
        }
        
        boolean doesIssueAlreadyExist = modelIssue.doesRecordAlreadyExist();
        
        if(!doesIssueAlreadyExist) {
            Date dateCreated = new Date();
            String dateCreatedOn= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateCreated);
            modelIssue.setCreatedOn(dateCreatedOn);
        }
        
        if(this.isAttributeValidDate("dateidentified", true)) {
            String dateIdentified = this.getAttributeValue("dateidentified","0000-00-00 00:00:00");
            modelIssue.setIdentifiedDate(dateIdentified);
            this.hydrateRequestAttribute("dateidentified", dateIdentified);
        }
        
        if(this.isAttributeValidDate("targetResolutionDate", false)) {
            String dateIdentified = this.getAttributeValue("targetResolutionDate","0000-00-00 00:00:00");
            modelIssue.setResolutionDateTarget(dateIdentified);
            this.hydrateRequestAttribute("targetResolutionDate", dateIdentified);
        }
                
        if(this.isAttributeValidDate("actualResolutionDate", false)) {
            String dateIdentified = this.getAttributeValue("actualResolutionDate","0000-00-00 00:00:00");
            modelIssue.setResolutionDateActual(dateIdentified);
            this.hydrateRequestAttribute("actualResolutionDate", dateIdentified);
        }
        
        return modelIssue;
    }
    
    /**
     * Loads all comments for current issue into jsp template
     */
    public void loadCommentsForCurrentIssue()
    {
       Issue issueModel = (Issue)this._getModel();
       
       if(this.isAttributeInteger("issue_id", false)) {
           Integer issueId = issueModel.getIssueId();
           Changelog modelChangelog = new Changelog();
           ArrayList<HashMap> commentsCollection = modelChangelog.getCollectionByIssueId(issueId);
           if(commentsCollection.size() > 0) {
                this.hydrateRequestAttribute("commentsList", commentsCollection);
                this.hydrateRequestAttribute("hasComments", true);
           }
       } else {
           this.hydrateRequestAttribute("hasComments", false);
       }
    }   
}
