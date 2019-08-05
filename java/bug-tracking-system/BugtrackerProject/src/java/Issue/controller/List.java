/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Issue.controller;

import Core.Controller.AbstractFormController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chris
 */
public class List extends AbstractFormController {

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
        Issue.Form.List listForm = new Issue.Form.List();
        Issue.Model.Issue modelIssue = new Issue.Model.Issue();
        Issue.Form.ListHydratorRequest listHydratorRequest = new Issue.Form.ListHydratorRequest(request, this);
        Issue.Form.ListHydrator listHydrator = new Issue.Form.ListHydrator(listForm,modelIssue,this, request, listHydratorRequest);
        listForm.loadDropDownOptions(listHydrator);
        ArrayList<HashMap> issuesList = modelIssue.getCollection(); 
        this.getServletContext().setAttribute("issuesList", issuesList);
        this.setTemplate("/list.jsp");
    }

    @Override
    public void processFormAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Issue.Form.List listForm = new Issue.Form.List();
        Issue.Model.Issue modelIssue = new Issue.Model.Issue();
        Issue.Form.ListHydratorRequest listHydratorRequest = new Issue.Form.ListHydratorRequest(request, this);
        Issue.Form.ListHydrator listHydrator = new Issue.Form.ListHydrator(listForm,modelIssue,this, request, listHydratorRequest);
        listForm.loadDropDownOptions(listHydrator);
        listForm.process(listHydrator, request);
        this.setTemplate("/list.jsp");      
    }
}
