/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Controller;

import Login.Business.UserSession;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chris
 */
public abstract class AbstractFormController extends HttpServlet {
    
    private HttpServletRequest _request;
    
    private HttpServletResponse _response;
    
    private boolean _requiresAuthentication = true;
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this._setRequest(request);
        this._setResponse(response);
        
        if(this._isUserAuthenticated()) {
            dispatch(request, response);
        } else {
            this.getResponse().sendRedirect("LoginServlet");
        }
    }
    
    /**
     * Dispatches the request
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    protected void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.displayFormAction(request,response);
    }
    
    /**
     * This function displays the form when user first arrives at form page.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public abstract void displayFormAction(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException;
    
    protected void setTemplate(String templateFilePath) throws ServletException, IOException
    {
        RequestDispatcher requestDispatcher;
        requestDispatcher = getServletContext().getRequestDispatcher(templateFilePath);
        requestDispatcher.forward(this.getRequest(),this.getResponse()); 
    }

        /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this._setRequest(request);
        this._setResponse(response);
        
        if(this._isUserAuthenticated()) {
            this.processFormAction(request, response);
        } else {
            this.getResponse().sendRedirect("LoginServlet");
        }
    }
  
    /**
     * Determines if authentication is required
     * @return 
     */
    private boolean _isUserAuthenticated()
    {
       boolean isAuthenticated = false;

       if(!this.doesRequireAuthentication()) {
           isAuthenticated = true;
       } else {
         UserSession userSession = (UserSession) this.getRequest().getSession().getAttribute("userSession");
         if(userSession != null) {
             String fullName = userSession.getFullname();
             Integer userId = userSession.getUserId();
             this.getServletContext().setAttribute("userFullName", fullName);
             this.getServletContext().setAttribute("userId", userId);
            isAuthenticated = userSession.isAuthenticated();
         }
       }
       
       return isAuthenticated;
    }
    
    
    public abstract void processFormAction(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException;
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet Information";
    }// </editor-fold>
    /**
     * @return the _request
     */
    public HttpServletRequest getRequest() {
        return _request;
    }

    /**
     * @return the _response
     */
    public HttpServletResponse getResponse() {
        return _response;
    }

    /**
     * @param _request the _request to set
     */
    protected void _setRequest(HttpServletRequest _request) {
        this._request = _request;
    }

    /**
     * @param _response the _response to set
     */
    protected void _setResponse(HttpServletResponse _response) {
        this._response = _response;
    }

    /**
     * @return the _requiresAuthentication
     */
    public boolean doesRequireAuthentication() {
        return _requiresAuthentication;
    }

    /**
     * @param _requiresAuthentication the _requiresAuthentication to set
     */
    public void setRequiresAuthentication(boolean _requiresAuthentication) {
        this._requiresAuthentication = _requiresAuthentication;
    }
}
