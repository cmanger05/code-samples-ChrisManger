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
import Issue.Model.Issue;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chris
 */
public class ListHydrator extends HydratorAbstract {
    
    /**
     * Constructor
     * @param form
     * @param model
     * @param controller
     * @param request
     * @param requestHydrator 
     */
    public ListHydrator(FormAbstract form, AbstractModel model, AbstractFormController controller, HttpServletRequest request, HydratorRequestAbstract requestHydrator) {
        super(form, model, controller, request, requestHydrator);
    }
    
    /**
     * Populates model with posted form data 
     * @return 
     */
    @Override
    public AbstractModel hydrate() {
        FormAbstract editForm = this.getForm();
        Issue modelIssue = (Issue)this._getModel();

        if(this.isAttributeInteger("assignee", false)) {
            String assigneeIdRaw = this.getAttributeValue("assignee","");
            Integer assigneeId = this.convertToInteger(assigneeIdRaw);
            modelIssue.setAssigneeId(assigneeId);
            this.hydrateRequestAttribute("assignee", assigneeId);
        } else {
            this.hydrateRequestAttribute("assignee", "");
        }
        
        if(this.isAttributeInteger("project", false)) {
            String projectIdRaw = this.getAttributeValue("project","");
            Integer projectId = this.convertToInteger(projectIdRaw);
            modelIssue.setProjectId(projectId);
            this.hydrateRequestAttribute("project", projectId);
        } else {
            this.hydrateRequestAttribute("project", "");
        }
        
        return modelIssue;
    }
    
}
