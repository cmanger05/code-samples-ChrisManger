/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Issue.Form;

import Core.Form.FormAbstract;
import Core.Form.HydratorAbstract;
import Issue.Model.Priority;
import Issue.Model.Status;
import Login.Model.User;
import Project.Model.Project;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chris
 */
public class Edit extends FormAbstract {
    
    /**
     * Performs processing and saves model if necessary
     * @param hydrator 
     * @param request 
     */
    @Override
    public void process(HydratorAbstract hydrator, HttpServletRequest request)
    {
        Issue.Model.Issue issueModel = (Issue.Model.Issue)hydrator.hydrate();
        FormAbstract form = hydrator.getForm();
        Issue.controller.Edit editController = (Issue.controller.Edit)hydrator.getController();
        
        try {
            if(form.isValid()) {
               boolean doesIssueAlreadyExist = issueModel.doesRecordAlreadyExist();
               boolean didIssueSaveSuccessfully = issueModel.save();

                if(didIssueSaveSuccessfully) {
                    this._savePostedComment(issueModel, hydrator);
                    if(doesIssueAlreadyExist) {
                        editController.displayIssueEditedPage();
                    } else {
                        editController.displayIssueCreatedPage();
                    }
                } else {
                    editController.displayIssueErrorPage();
                }
            } else {
                    ArrayList<String> errors = form.getErrors();
                    hydrator.getController().getServletContext().setAttribute("errorsList", errors);
                    if(errors.size() > 0) {
                        hydrator.getController().getServletContext().setAttribute("hasErrors", true);
                    }
                    editController.displayEditIssuePage();                 
            }
        } catch (ServletException | IOException ex) {
            Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
    
    /**
     * Saves a posted comment
     * @param issueModel
     * @param hydrator 
     */
    private void _savePostedComment(Issue.Model.Issue issueModel, HydratorAbstract hydrator)
    {
        Integer issueId = issueModel.getIssueId();
        String commentText = issueModel.getCommentText();
        
        Object userIdRaw = hydrator.getController().getServletContext().getAttribute("userId");
        String userIdString = userIdRaw.toString();
        boolean isUserIdInteger = hydrator.isValueInteger(userIdString);

        if(isUserIdInteger && issueId != null && commentText != null) {
            Integer userId = hydrator.convertToInteger(userIdString);
            Issue.Model.Changelog modelChangelog = new Issue.Model.Changelog();
            modelChangelog.setIssueId(issueId);
            modelChangelog.setUserId(userId);
            modelChangelog.setComment(commentText);
            modelChangelog.save();
        }
    }
    
    /**
     * Loads the drop down options
     * @param hydrator 
     */
    public void loadDropDownOptions(EditHydrator hydrator)
    {
        Priority modelPriority = new Priority();
        ArrayList<HashMap> priorityCollection = modelPriority.getCollection();
        hydrator.getController().getServletContext().setAttribute("priorityDropDownList", priorityCollection); 
        
        Status modelStatus = new Status();
        ArrayList<HashMap> statusCollection = modelStatus.getCollection();
        hydrator.getController().getServletContext().setAttribute("statusDropDownList", statusCollection);   
        
        User modelUsers = new User();
        ArrayList<HashMap> userCollection = modelUsers.getCollection();
        hydrator.getController().getServletContext().setAttribute("userDropDownList", userCollection);   
        
        Project modelProject = new Project();
        ArrayList<HashMap> projectCollection = modelProject.getCollection();
        hydrator.getController().getServletContext().setAttribute("projectDropDownList", projectCollection); 
    }
}
