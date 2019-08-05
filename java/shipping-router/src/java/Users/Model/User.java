/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users.Model;

import Core.Model.AbstractModel;
import ObjectCreation.InterfacesCreational.CreationalInterfaces.BaseCreationalInterface;
import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.Model.Arguments.ArgumentStandard;
import ObjectCreation.Model.Arguments.ArgumentString;
import ObjectCreation.Model.ObjectFactory;
import ObjectCreation.Model.Prototypes.CollectionStandard;
import Users.Model.Collection.Users;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class User extends AbstractModel implements BaseCreationalInterface
{
    private Integer _user_id;
    
    private Integer _user_role_id;
    
    private String _role_name;
    
    private String _username;
    
    private String _email;
    
    private String _firstName;
    
    private String lastName;
    
    private Date date_created;
    
    /**
     * User
     */
    public User()
    {
        this._defaultFieldName = "user_id";
        this._defaultTableName = "users"; //db does not allow a table called user to exist, that is why its plural
    }
    
    /**
     * Creates an instance of this class
     * @return 
     */
    public static User createInstance()
    {
        ArrayList<ArgumentStandard> arrayListArguments;
        arrayListArguments = new ArrayList<>();
        ArgumentString shortDescription = new ArgumentString("placeholder","placeholder");
        arrayListArguments.add(shortDescription);
        
        CollectionStandard<ArgumentStandard> standardCollection = new CollectionStandard(arrayListArguments);
        ArgumentCollection argumentCollection = new ArgumentCollection(standardCollection);
        User user;
        user = (User) ObjectFactory.get("User", argumentCollection);
        return user;
    }
    
    /**
     * Returns the id
     * @return 
     */
    @Override
    public Integer getId() 
    {
        return this.getUserId();
    }
    
    /**
     * Constructor
     * @param arguments 
     */
    @Override
    public void construct(ArgumentCollection arguments) {}
    
    /**
     * @return the _user_id
     */
    public Integer getUserId() {
        return _user_id;
    }

    /**
     * @return the _user_role_id
     */
    public Integer getUserRoleId() 
    {
        return _user_role_id;
    }

    /**
     * @return the _role_name
     */
    public String getRoleName() {
        return _role_name;
    }

    /**
     * @return the _username
     */
    public String getUsername() {
        return _username;
    }

    /**
     * @return the _email
     */
    public String getEmail() {
        return _email;
    }

    /**
     * @return the _firstName
     */
    public String getFirstName() {
        return _firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the date_created
     */
    public Date getDateCreated() {
        return date_created;
    }

    /**
     * @param _user_id the _user_id to set
     */
    public void setUserId(Integer _user_id) {
        this._user_id = _user_id;
    }

    /**
     * @param _user_role_id the _user_role_id to set
     */
    public void setUserRoleId(Integer _user_role_id) {
        this._user_role_id = _user_role_id;
    }

    /**
     * @param _role_name the _role_name to set
     */
    public void setRoleName(String _role_name) {
        this._role_name = _role_name;
    }

    /**
     * @param _username the _username to set
     */
    public void setUsername(String _username) 
    {
        this._username = _username;
    }

    /**
     * @param _email the _email to set
     */
    public void setEmail(String _email) 
    {
        this._email = _email;
    }

    /**
     * @param _firstName the _firstName to set
     */
    public void setFirstName(String _firstName) 
    {
        this._firstName = _firstName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    /**
     * @param date_created the date_created to set
     */
    public void setDateCreated(Date date_created) 
    {
        this.date_created = date_created;
    }
    
    /**
     * Loads an instance
     * @param idFieldValue 
     */
    @Override
    public void loadById(Integer idFieldValue)
    {  
        /* Do not use a factory to create this object because it uses a generic*/
        Users usersCollection = new Users();
        usersCollection.loadSingleInstance(idFieldValue);
        
        User user = usersCollection.next();
        if(this.isIntegerNotNull(user.getId())) {
            this.setUserId(user.getUserId());
            this.setRoleName(user.getRoleName());
            this.setUsername(user.getUsername());
            this.setEmail(user.getEmail());
            this.setFirstName(user.getFirstName());
            this.setLastName(user.getLastName());
            this.setDateCreated(user.getDateCreated());
        }    
    }
    
    /**
     * Cloner
     * @return 
     */
    @Override
    public User cloneObject() {
        return new User();
    }
}
