/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Issue.controller;

import Core.Controller.AbstractFormController;
import Issue.Form.EditHydrator;
import Issue.Form.EditHydratorRequest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chris
 */
public class Edit extends AbstractFormController {

    
        /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request request
     * @param response response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void processFormAction(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException
    {
        Issue.Form.Edit editForm = new Issue.Form.Edit();
        Issue.Model.Issue modelIssue = new Issue.Model.Issue();
        EditHydratorRequest editHydratorRequest = new EditHydratorRequest(request, this);
        EditHydrator editHydrator = new EditHydrator(editForm,modelIssue,this, request, editHydratorRequest);
        editForm.loadDropDownOptions(editHydrator);
        editForm.process(editHydrator, request);
        editHydrator.loadCommentsForCurrentIssue();
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void displayFormAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        boolean didIssueFailLoad = this._loadIssueDataIntoFormFields(request); 
        if(!didIssueFailLoad) {
           this.setTemplate("/edit.jsp"); 
        } else {
            this.setTemplate("/edit_invalid_failed_load.jsp");
        }   
    }
 
    /**
     * Loads data from the database (according to issue_id get parameter), then sets the servlet attributes
     * the jsp grabs the values of these server attributes.  Therefore, this loads the data onto the form.
     * @param request 
     */
    private boolean _loadIssueDataIntoFormFields(HttpServletRequest request)
    {
        boolean didIssueFailLoad = false;
        String issueIdRaw = request.getParameter("issue_id");
        
        Issue.Form.Edit editForm = new Issue.Form.Edit();
        Issue.Model.Issue modelIssue = new Issue.Model.Issue();
        EditHydratorRequest editHydratorRequest = new EditHydratorRequest(request, this);
        EditHydrator editHydrator = new EditHydrator(editForm,modelIssue,this, request, editHydratorRequest);
        editForm.loadDropDownOptions(editHydrator);
            
        if(issueIdRaw != null && !issueIdRaw.isEmpty()) {
            if(editHydrator.isValueInteger(issueIdRaw)) {
                Integer issueId = editHydrator.convertToInteger(issueIdRaw);
                if(issueId != 0) {
                    modelIssue.setIssueId(issueId);
                    if(modelIssue.doesRecordAlreadyExist()) {
                        modelIssue.setIssueId(issueId);
                        modelIssue.loadById(issueId);
                        editHydratorRequest.hydrate(modelIssue);
                        editHydrator.loadCommentsForCurrentIssue();
                    } else {
                        didIssueFailLoad = true;
                    }              
                }
            }
        } else { //this clause prevents form data from being reloaded when back button is pressed!
            editHydratorRequest.flushAllRequestVars();
            this.getServletContext().setAttribute("errorsList", "");
            this.getServletContext().setAttribute("hasErrors", "");
        }
        
        return didIssueFailLoad;
    }
    
        /**
     * Displays the issue was successfully edited page.
     * @throws ServletException
     * @throws IOException 
     */
    public void displayEditIssuePage() throws ServletException, IOException
    {
        this.setTemplate("/edit.jsp");
    }
    
    /**
     * Displays the issue was successfully edited page.
     * @throws ServletException
     * @throws IOException 
     */
    public void displayIssueEditedPage() throws ServletException, IOException
    {
        this.setTemplate("/edit_success_existing.jsp");
    }
    
    /**
     * Displays a page stating an issue has been successfully created.
     * @throws ServletException
     * @throws IOException 
     */
    public void displayIssueCreatedPage() throws ServletException, IOException
    {
        this.setTemplate("/edit_success_new.jsp");
    }
    
    /**
     * Displays the error page. Occurs if user data is invalid when submitting a post.
     * @throws ServletException
     * @throws IOException 
     */
    public void displayIssueErrorPage() throws ServletException, IOException
    {
        this.setTemplate("/edit_invalid.jsp");
    }
}
