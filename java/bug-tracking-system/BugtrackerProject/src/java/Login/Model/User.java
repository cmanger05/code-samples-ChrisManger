package Login.Model;


import Core.Model.AbstractModel;
import Core.Util.DBConnectionsUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chris
 */
public class User extends AbstractModel {
    
    private Integer user_id;
    
    private Integer role_id;
    
    private String username;
    
    private String first_name;
    
    private String last_name;
    
    private String email;
    
    private String password;
    
    private String created_at;
    
    
    /**
     * User constructor
     */
    public User()
    {
        this._defaultFieldName = "user_id";
        this._defaultTableName = "users";
    }
    
    @Override
    public Integer getId() {
        return this.getUserId();
    }

    /**
     * @return the user_id
     */
    public Integer getUserId() {
        return user_id;
    }

    /**
     * @return the role_id
     */
    public Integer getRoleId() {
        return role_id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the first_name
     */
    public String getFirstName() {
        return first_name;
    }

    /**
     * @return the last_name
     */
    public String getLastName() {
        return last_name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the created_at
     */
    public String getCreatedAt() {
        return created_at;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUserId(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * @param role_id the role_id to set
     */
    public void setRoleId(Integer role_id) {
        this.role_id = role_id;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @param last_name the last_name to set
     */
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param created_at the created_at to set
     */
    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }
    
        /**
     * Returns the collection
     * @return 
     */
    @Override
    public ArrayList<HashMap> getCollection()
    {
        ArrayList<HashMap> records;
        records = new ArrayList<>();
        
        if(!this.getDefaultTableName().isEmpty()) {
            String queryLoadIssue = "SELECT * FROM " + this.getDefaultTableName();
            try ( //connect to DB
                Connection currentCon = DBConnectionsUtil.getConnection();
                Statement stmt = currentCon.createStatement();
                ResultSet rs = stmt.executeQuery(queryLoadIssue)) {

                Integer userId;
                Integer roleId;
                String userName;
                String firstName;
                String lastName;
                String userFullname;
                String emailUser;
                String passwordUser;
                String createdAt;

                while(rs.next()) {
                    HashMap itemDetails = new HashMap();
                    userId = rs.getInt("user_id");
                    roleId = rs.getInt("role_id");
                    userName = rs.getString("username");
                    firstName = rs.getString("first_name");
                    lastName = rs.getString("last_name");
                    userFullname = firstName + " " + lastName;
                    emailUser = rs.getString("email");
                    passwordUser = rs.getString("password");
                    createdAt = rs.getString("created_at");
                    itemDetails.put("user_id",userId);
                    itemDetails.put("role_id",roleId);
                    itemDetails.put("username",userName);
                    itemDetails.put("first_name",firstName);
                    itemDetails.put("last_name",lastName);
                    itemDetails.put("fullname", userFullname);
                    itemDetails.put("email",emailUser);
                    itemDetails.put("password",passwordUser);
                    itemDetails.put("created_at",createdAt);
                    records.add(itemDetails);
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
        
        return records;
    }
}
