/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users.Model;

import Core.Model.AbstractModel;
import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.Model.Arguments.ArgumentStandard;
import ObjectCreation.Model.Arguments.ArgumentString;
import ObjectCreation.InterfacesCreational.InstanceFactoryStandardInterface;
import ObjectCreation.Model.ObjectFactory;
import ObjectCreation.Model.Prototypes.CollectionStandard;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class UserRoute extends AbstractModel implements InstanceFactoryStandardInterface
{
    private Integer _user_route_id;
    
    private Integer _user_id;
    
    private Integer _route_id;

    public UserRoute()
    {
        this._defaultFieldName = "user_route_id";
        this._defaultTableName = "user_route";
    }
    
    /**
     * Creates an instance of the current class
     * @return 
     */
    public UserRoute createInstance()
    {
        ArrayList<ArgumentStandard> arrayListArguments;
        arrayListArguments = new ArrayList<>();
        ArgumentString shortDescription = new ArgumentString("placeholder","placeholder");
        arrayListArguments.add(shortDescription);
        
        CollectionStandard<ArgumentStandard> standardCollection = new CollectionStandard(arrayListArguments);
        ArgumentCollection argumentCollection = new ArgumentCollection(standardCollection);
        UserRoute userRoute;
        userRoute = (UserRoute) ObjectFactory.get("UserRoute", argumentCollection);
        return userRoute;
    }
    
    /**
     * Returns user route id
     * @return 
     */
    @Override
    public Integer getId() 
    {
        return this.getUserRouteId();
    }
    
    /**
     * Constructor
     * @param arguments 
     */
    @Override
    public void construct(ArgumentCollection arguments) {}
    
    /**
     * @return the _user_route_id
     */
    public Integer getUserRouteId() 
    {
        return _user_route_id;
    }

    /**
     * @return the _user_id
     */
    public Integer getUserId() 
    {
        return _user_id;
    }

    /**
     * @return the route_id
     */
    public Integer getRouteId() 
    {
        return _route_id;
    }

    /**
     * @param _user_route_id the _user_route_id to set
     */
    public void setUserRouteId(Integer _user_route_id) 
    {
        this._user_route_id = _user_route_id;
    }

    /**
     * @param _user_id the _user_id to set
     */
    public void setUserId(Integer _user_id) 
    {
        this._user_id = _user_id;
    }

    /**
     * @param _route_id the _route_id to set
     */
    public void setRouteId(Integer _route_id) 
    {
        this._route_id = _route_id;
    }
        
    /**
     * Cloner
     * @return 
     */
    @Override
    public UserRoute cloneObject() {
        return new UserRoute();
    }
}
