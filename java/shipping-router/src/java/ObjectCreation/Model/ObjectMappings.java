/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectCreation.Model;

import ObjectCreation.InterfacesCreational.InstanceFactoryBaseInterface;
import RouteMatrixBuilder.Model.BindPossibilitiesToStopsBuilder;
import RouteSimulator.Model.Collections.RouteProxyCollectionBuilder;
import RouteSimulator.Model.RouteCandidateBuilder;
import RouteSimulator.Model.RouteStopCandidate;
import RouteSimulator.Model.TruckCandidateSample;
import Routes.Model.PendingRouteCreation;
import Routes.Model.Route;
import Routes.Model.RouteStop;
import Routes.Model.Stop;
import Routes.Model.Truck;
import Testframework.Model.DynamicConstructor;
import Testframework.Model.DynamicObjectComplexBuilder;
import Users.Model.User;
import Users.Model.UserRoute;
import java.util.HashMap;

/**
 *
 * @author chris
 */
public final class ObjectMappings {
    private final HashMap<String, InstanceFactoryBaseInterface> _objectMappings = new HashMap<>();
    
    /**
     * Returns the object mappings
     * @return 
     */
    public HashMap<String, InstanceFactoryBaseInterface> getObjectMappings() 
    {
        return this._objectMappings;
    }
    
    /**
     * Declares all object mappings
     */
    public ObjectMappings() 
    {
        this.addMapping("DynamicConstructor", new DynamicConstructor());
        this.addMapping("DynamicObjectComplex", new DynamicObjectComplexBuilder());
        this.addMapping("PendingRouteCreation", new PendingRouteCreation());
        this.addMapping("Stop", new Stop());
        this.addMapping("Truck", new Truck());
        this.addMapping("Route", new Route());
        this.addMapping("RouteStop", new RouteStop());
        this.addMapping("User", new User());
        this.addMapping("UserRoute", new UserRoute());
        this.addMapping("RouteProxyCollection", new RouteProxyCollectionBuilder());
        this.addMapping("RouteCandidate", new RouteCandidateBuilder());
        this.addMapping("RouteStopCandidate", new RouteStopCandidate());
        this.addMapping("BindPossibilitiesToStops", new BindPossibilitiesToStopsBuilder());
        this.addMapping("TruckCandidateSample", new TruckCandidateSample());
    }
    
    /**
     * Adds a mapping
     * @param objectAbbreviated
     * @param initialObject 
     */
    public void addMapping(String objectAbbreviated, InstanceFactoryBaseInterface initialObject)
    {
        this._objectMappings.put(objectAbbreviated, initialObject);
    }
    
    /**
     * Removes a mapping
     * @param objectAbbreviated 
     */
    public void removeMapping(String objectAbbreviated)
    {
        this._objectMappings.remove(objectAbbreviated);
    }  
}
