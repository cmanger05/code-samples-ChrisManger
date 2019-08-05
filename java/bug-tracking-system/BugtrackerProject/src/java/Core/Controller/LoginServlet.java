/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Controller;

import Login.Business.UserSession;
import Login.DataAccess.LoginDataAccessObject;
import Login.Business.UsersBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author sai
 */
public class LoginServlet extends AbstractFormController {
    
    /**
     * Anyone is allowed to access
     */
    public LoginServlet()
    {
        this.setRequiresAuthentication(false);
    }
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
        try
        {	    
            UsersBean user = new UsersBean();
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));

            UserSession userSession = new UserSession();
            user = LoginDataAccessObject.login(user);
            if(user.isValid()) {
                String fullName = user.getFirstName() + " " + user.getLastName();
                Integer userId = user.getUserId();
                userSession.setFullname(fullName);
                userSession.setAuthenticated(true);
                userSession.setUserId(userId);
            } else {
                userSession.setAuthenticated(false);
            }
	   
            this.getRequest().getSession().setAttribute("userSession",userSession);
            
             RequestDispatcher requestDispatcher;
            if (user.isValid())
            {
                getServletContext().setAttribute("currentUser", user);
                this.displayListPage();
            }    
            else 
            {
                this.displayInvalidLoginAttemptPage();
            }
        } 		
        catch (ServletException | IOException theException) 
        {
            System.out.println(theException);
        }
    }

    /**
     * Occurs on a successful login. Displays the page listing all the tickets.
     * @throws ServletException
     * @throws IOException 
     */
    public void displayListPage() throws ServletException, IOException
    {
        this.getResponse().sendRedirect("List");
    }
 
        /**
     * Occurs on a successful login. Displays the page listing all the tickets.
     * @throws ServletException
     * @throws IOException 
     */
    public void displayInvalidLoginAttemptPage() throws ServletException, IOException
    {
        this.setTemplate("/invalidLogin.jsp");
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
        this.setTemplate("/login.jsp");
    }
}
