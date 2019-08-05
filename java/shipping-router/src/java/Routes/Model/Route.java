/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routes.Model;

import Core.Model.AbstractModel;
import ObjectCreation.InterfacesCreational.CreationalInterfaces.BaseCreationalInterface;
import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.Model.Arguments.ArgumentStandard;
import ObjectCreation.Model.Arguments.ArgumentString;
import ObjectCreation.Model.ObjectFactory;
import ObjectCreation.Model.Prototypes.CollectionStandard;
import Routes.Model.Collection.RouteStops;
import Routes.Model.Collection.Routes;
import Routes.Model.Resource.RouteSave;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class Route extends AbstractModel implements BaseCreationalInterface
{
    private Integer _route_id;
    
    private String _name;
    
    private Integer _hub_id;
    
    private Integer _truck_id;
    
    private Date _date_started;
    
    private Date _date_delivered;
    
    private Double _total_distance;
    
    private Double _average_miles_leg;
    
    private Double _value_per_mile;
    
    private Integer _is_complete;
    
    private RouteStops _route_stops;

    /**
     * Route constructor
     */
    public Route()
    {
        this._defaultFieldName = "route_id";
        this._defaultTableName = "route";
        this._setRouteStops(new RouteStops());
    }
    
    /**
     * Returns an instance of current object
     * @return 
     */
    public static Route createInstance()
    {
        ArrayList<ArgumentStandard> arrayListArguments;
        arrayListArguments = new ArrayList<>();
        ArgumentString shortDescription = new ArgumentString("placeholder","placeholder");
        arrayListArguments.add(shortDescription);
        
        CollectionStandard<ArgumentStandard> standardCollection = new CollectionStandard(arrayListArguments);
        ArgumentCollection argumentCollection = new ArgumentCollection(standardCollection);
        Route route;
        route = (Route) ObjectFactory.get("Route", argumentCollection);
        route.getRouteStops().removeAllItems();
        return route;
    }
    
    /**
     * Returns the route id
     * @return 
     */
    @Override
    public Integer getId() {
        return this.getRouteId();
    }
    
    /**
     * No constructor needed
     * @param arguments 
     */
    @Override
    public void construct(ArgumentCollection arguments) {}
    
    /**
     * @return the _route_id
     */
    public Integer getRouteId() 
    {
        return _route_id;
    }

    /**
     * @return the _name
     */
    public String getName() 
    {
        return _name;
    }

    /**
     * @return the _hub_id
     */
    public Integer getHubId() 
    {
        return _hub_id;
    }

    /**
     * @return the _truck_id
     */
    public Integer getTruckId() 
    {
        return _truck_id;
    }

    /**
     * @return the _date_started
     */
    public Date getDateStarted() 
    {
        return _date_started;
    }

    /**
     * @return the _date_delivered
     */
    public Date getDateDelivered() 
    {
        return _date_delivered;
    }

    /**
     * @return the _total_distance
     */
    public Double getTotalDistance() 
    {
        return _total_distance;
    }

    /**
     * @return the _average_miles_leg
     */
    public Double getAverageMilesPerLeg() 
    {
        return _average_miles_leg;
    }

    /**
     * @return the _value_per_mile
     */
    public Double getValuePerMile() 
    {
        return _value_per_mile;
    }

    /**
     * @return the _is_complete
     */
    public Integer getIsComplete() 
    {
        return _is_complete;
    }
  
    /**
     * @return the _route_stops
     */
    public RouteStops getRouteStops() 
    {
        return _route_stops;
    }
    
    /**
     * @param _route_id the _route_id to set
     */
    public void setRouteId(Integer _route_id) {
        this._route_id = _route_id;
    }

    /**
     * @param _name the _name to set
     */
    public void setName(String _name) 
    {
        this._name = _name;
    }

    /**
     * @param _hub_id the _hub_id to set
     */
    public void setHubId(Integer _hub_id) 
    {
        this._hub_id = _hub_id;
    }

    /**
     * @param _truck_id the _truck_id to set
     */
    public void setTruckId(Integer _truck_id) 
    {
        this._truck_id = _truck_id;
    }

    /**
     * @param _date_started the _date_started to set
     */
    public void setDateStarted(Date _date_started) 
    {
        this._date_started = _date_started;
    }

    /**
     * @param _date_delivered the _date_delivered to set
     */
    public void setDateDelivered(Date _date_delivered) 
    {
        this._date_delivered = _date_delivered;
    }

    /**
     * @param _total_distance the _total_distance to set
     */
    public void setTotalDistance(Double _total_distance) 
    {
        this._total_distance = _total_distance;
    }

    /**
     * @param _average_miles_leg the _average_miles_leg to set
     */
    public void setAverageMilesPerLeg(Double _average_miles_leg) 
    {
        this._average_miles_leg = _average_miles_leg;
    }

    /**
     * @param _value_per_mile the _value_per_mile to set
     */
    public void setValuePerMile(Double _value_per_mile) 
    {
        this._value_per_mile = _value_per_mile;
    }

    /**
     * @param _is_complete the _is_complete to set
     */
    public void setIsComplete(Integer _is_complete) 
    {
        this._is_complete = _is_complete;
    }
  
    /**
     * Loads associated route stops
     */
    public void loadAssociatedRouteStops()
    {
        this.getRouteStops().loadByRoute(this);
    }
    
    /**
     * Loads an instance
     * @param idFieldValue 
     */
    @Override
    public void loadById(Integer idFieldValue)
    {  
        /* Do not use a factory to create this object because it uses a generic*/
        Routes routesCollection = new Routes();
        routesCollection.loadSingleInstance(idFieldValue);
        
        Route route = routesCollection.next();
        if(this.isIntegerNotNull(route.getId())) {
            this.setRouteId(route.getRouteId());
            this.setName(route.getName());
            this.setHubId(route.getHubId());
            this.setTruckId(route.getTruckId());
            this.setDateStarted(route.getDateStarted());
            this.setDateDelivered(route.getDateDelivered());
            this.setTotalDistance(route.getTotalDistance());
            this.setAverageMilesPerLeg(route.getAverageMilesPerLeg());
            this.setValuePerMile(route.getValuePerMile());
            this.setIsComplete(route.getIsComplete());
            this.loadAssociatedRouteStops();
        }    
    }
 
    /**
     * Saves the rental
     * @return 
     */
    @Override
    public boolean save()
    {
        boolean didRecordSaveCorrectly = false;
        RouteSave resourceSaveRoute = new RouteSave();
        
        if(!this.doesRecordAlreadyExist()) { 
            didRecordSaveCorrectly = resourceSaveRoute.saveNew(this);
        } else {
            didRecordSaveCorrectly = resourceSaveRoute.saveExisting(this);
        }
        
        if(didRecordSaveCorrectly) {
            for(RouteStop routeStop : this.getRouteStops()) {
                if(!routeStop.save()) {
                    didRecordSaveCorrectly = false;
                }
            }
        }

        return didRecordSaveCorrectly;
    }
    
    /**
     * @param route_stops the route_stops to set
     */
    private void _setRouteStops(RouteStops _route_stops) 
    {
        this._route_stops = _route_stops;
    }
    
    /**
     * Clones object
     * @return 
     */
    @Override
    public Route cloneObject() 
    {
        return new Route();
    }
}
