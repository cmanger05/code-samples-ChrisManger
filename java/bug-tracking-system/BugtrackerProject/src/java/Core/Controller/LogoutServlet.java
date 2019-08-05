/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chris
 */
public class LogoutServlet extends AbstractFormController {

    /**
     * Is almost equivalent to do get (except a few things are called before this is executed)
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void displayFormAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this._processLogout();
    }
    
    /**
     * Runs every time form is posted
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void processFormAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this._processLogout();
    }
    
    /**
     * Performs all processing associated with logging out a user.
     * @throws ServletException
     * @throws IOException 
     */
    private void _processLogout() throws ServletException, IOException
    {
        this.getRequest().getSession().setAttribute("userSession",null);
        this.displayLogoutPage();
    }
    
    /**
     * Displays the logout page
     * @throws ServletException
     * @throws IOException 
     */
    public void displayLogoutPage() throws ServletException, IOException
    {
        this.setTemplate("/logout.jsp");
    }
}
