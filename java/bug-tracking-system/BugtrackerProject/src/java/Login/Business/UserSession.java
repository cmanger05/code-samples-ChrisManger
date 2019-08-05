/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login.Business;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author chris
 */
@Named(value = "userSession")
@SessionScoped
public class UserSession implements Serializable {
    
    private boolean _authenticated = false;

    private Integer userId;
    
    private String fullname;
    
    /**
     * Creates a new instance of UserSession
     */
    public UserSession() {
    }

    /**
     * @return the _authenticated
     */
    public boolean isAuthenticated() {
        return _authenticated;
    }

    /**
     * @param _authenticated the _authenticated to set
     */
    public void setAuthenticated(boolean _authenticated) {
        this._authenticated = _authenticated;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     * @return 
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
