/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Issue.Form;

import Core.Form.FormAbstract;
import Core.Form.HydratorAbstract;
import Login.Model.User;
import Project.Model.Project;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chris
 */
public class List extends FormAbstract {

    /**
     * Processes the form
     * @param hydrator
     * @param request 
     */
    @Override
    public void process(HydratorAbstract hydrator, HttpServletRequest request) {
        ListHydrator listHydrator = (ListHydrator)hydrator;
        Issue.Model.Issue issueModel = (Issue.Model.Issue)hydrator.hydrate();
        Issue.Form.List listForm = (Issue.Form.List)hydrator.getForm();
        if(listForm.isValid()) {
            String submittedButton = hydrator.getPostedStringValue("search");
            if(submittedButton.equals("Search Only Open Tickets")) {
                issueModel.addFilter("s.status_id != 5");
            }
                        
            Integer assigneeId = issueModel.getAssigneeId();
            Integer projectId = issueModel.getProjectId();
            if(issueModel.isIntegerNotNull(assigneeId)) {
                issueModel.addFilter("i.assignee_id = " + assigneeId.toString());
            }
            if(issueModel.isIntegerNotNull(projectId)) {
                issueModel.addFilter("p.project_id = " + projectId.toString());
            }
            
            if(submittedButton.equals("View My Open Tickets") && issueModel.isIntegerNotNull(assigneeId)) {
                issueModel.addFilter("s.status_id != 5");
            }
        }
        
        ArrayList<HashMap> issuesList = issueModel.getCollection(); 
        hydrator.getController().getServletContext().setAttribute("issuesList", issuesList);
    }
    
     /**
     * Loads the drop down options
     * @param hydrator 
     */
    public void loadDropDownOptions(ListHydrator hydrator)
    {        
        User modelUsers = new User();
        ArrayList<HashMap> userCollection = modelUsers.getCollection();
        hydrator.getController().getServletContext().setAttribute("userDropDownList", userCollection);   
        
        Project modelProject = new Project();
        ArrayList<HashMap> projectCollection = modelProject.getCollection();
        hydrator.getController().getServletContext().setAttribute("projectDropDownList", projectCollection); 
    }
    
}
