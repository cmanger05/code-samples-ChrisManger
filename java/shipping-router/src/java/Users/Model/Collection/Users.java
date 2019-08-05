/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users.Model.Collection;

import Core.Model.AbstractCollection;
import Core.Model.CollectionItemValidator;
import Core.Model.DBConnectionsUtil;

import Users.Model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public class Users extends AbstractCollection<User> {
        /**
     * Loads one stop only 
     * @param stopId 
     */
    public void loadSingleInstance(Integer stopId)
    {
        this.addFilter("u.user_id = " + stopId);
        this.load();
    }
    
    /**
     * Loads Rental Videos
     */
    @Override
    public void load() {

        String whereClause = this._renderWhereClause();
        String queryLoadAllUsers = "SELECT u.user_id, u.username, "
                + "r.role_name, u.email, u.first_name, u.last_name, u.date_created " +
            "FROM users u INNER JOIN role r ON u.user_role_id = r.role_id " + whereClause;
        
        try ( //connect to DB
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryLoadAllUsers)) {

            User userItem;
            CollectionItemValidator itemValidator;
            Integer userId;
            String userName;
            String roleName;
            String email;
            String firstName;
            String lastName;
            Date dateCreated;

            
            while(rs.next()) {
                userItem = (User)this.createNewItem();
                itemValidator = this.createNewValidator(userItem);

                userId   = rs.getInt("user_id");
                if(itemValidator.isIntegerNotNull(userId, true)) {
                    userItem.setUserId(userId);
                }
                
                userName   = rs.getString("username");
                if(itemValidator.isStringNotNull(userName, true)) {
                    userItem.setUsername(userName);
                }
                    
                roleName = rs.getString("role_name"); 
                if(itemValidator.isStringNotNull(roleName, true)) {
                    userItem.setRoleName(roleName);
                }
                
                email = rs.getString("email");
                if(itemValidator.isStringNotNull(email, true)) {
                    userItem.setEmail(email);
                }
                
                firstName = rs.getString("first_name"); 
                if(itemValidator.isStringNotNull(firstName, true)) {
                    userItem.setFirstName(firstName);
                }
                
                lastName = rs.getString("last_name"); 
                if(itemValidator.isStringNotNull(lastName, true)) {
                    userItem.setLastName(lastName);
                }
                
                dateCreated = rs.getDate("date_created"); 
                if(itemValidator.isDateNotEmpty(dateCreated, false)) {
                    userItem.setDateCreated(dateCreated);
                }
                             
                if(itemValidator.isValid()) {
                    this.addItem(userId, userItem);
                }            
            }
            
            rs.close();
            stmt.close();
            currentCon.close();
        }
        catch (Exception ex) 
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
    }
    
    /**
     * Returns an instance of Stop
     * @return 
     */
    @Override
    public User createNewItem() 
    {    
        return User.createInstance();
    } 
}
