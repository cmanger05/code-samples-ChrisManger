/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routes.Model;

import Core.Model.AbstractModel;
import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.Model.Arguments.ArgumentStandard;
import ObjectCreation.Model.Arguments.ArgumentString;
import ObjectCreation.InterfacesCreational.InstanceFactoryStandardInterface;
import ObjectCreation.Model.ObjectFactory;
import ObjectCreation.Model.Prototypes.CollectionStandard;
import Routes.Model.Collection.RouteStops;
import Routes.Model.Resource.RouteStopSave;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class RouteStop extends AbstractModel implements InstanceFactoryStandardInterface
{
    private Integer _route_stop_id;
    
    private Integer _route_id;
    
    private Integer _stop_id;
    
    private Double _qty_to_deliver;
    
    private Double _qty_delivered;
    
    private Date _date_delivered;
    
    private Double _total_distance;
    
    private Double _value_per_mile;
    
    private Integer _order_number;
    
    private Integer _is_complete;
    
    private String _stop_name;

    
    /**
     * Constructor
     */
    public RouteStop()
    {
        this._defaultFieldName = "route_stop_id";
        this._defaultTableName = "route_stop";
    }
    
    /**
     * Creates an instance of current class
     * @return 
     */
    public static RouteStop createInstance()
    {
        ArrayList<ArgumentStandard> arrayListArguments;
        arrayListArguments = new ArrayList<>();
        ArgumentString shortDescription = new ArgumentString("placeholder","placeholder");
        arrayListArguments.add(shortDescription);
        
        CollectionStandard<ArgumentStandard> standardCollection = new CollectionStandard(arrayListArguments);
        ArgumentCollection argumentCollection = new ArgumentCollection(standardCollection);
        RouteStop routeStop;
        routeStop = (RouteStop) ObjectFactory.get("RouteStop", argumentCollection);
        return routeStop;
    }
    
    /**
     * Returns the id
     * @return 
     */
    @Override
    public Integer getId() 
    {
        return this.getRouteStopId();
    }
    
    /**
     * Constructor
     * @param arguments 
     */
    @Override
    public void construct(ArgumentCollection arguments) {}
    
    /**
     * @return the _route_stop_id
     */
    public Integer getRouteStopId()
    {
        return _route_stop_id;
    }

    /**
     * @return the _route_id
     */
    public Integer getRouteId()
    {
        return _route_id;
    }

    /**
     * @return the _stop_id
     */
    public Integer getStopId() 
    {
        return _stop_id;
    }

    /**
     * @return the _qty_to_deliver
     */
    public Double getQtyToDeliver() 
    {
        return _qty_to_deliver;
    }

    /**
     * @return the _qty_delivered
     */
    public Double getQtyDelivered() 
    {
        return _qty_delivered;
    }

    /**
     * @return the _date_delivered
     */
    public Date getDateDelivered() 
    {
        return _date_delivered;
    }

    /**
     * @return the _is_complete
     */
    public Integer getIsComplete() 
    {
        return _is_complete;
    }

    /**
     * @return the order_num
     */
    public Integer getOrderNumber() 
    {
        return _order_number;
    }

    /**
     * @return the total_distance
     */
    public Double getTotalDistance() 
    {
        return _total_distance;
    }
    
    /**
     * @return the _value_per_mile
     */
    public Double getValuePerMile() 
    {
        return _value_per_mile;
    }

    /**
     * @return the _stop_name
     */
    public String getStopName() 
    {
        return _stop_name;
    }
    
    /**
     * @param _route_stop_id the _route_stop_id to set
     */
    public void setRouteStopId(Integer _route_stop_id)
    {
        this._route_stop_id = _route_stop_id;
    }

    /**
     * @param _route_id the _route_id to set
     */
    public void setRouteId(Integer _route_id)
    {
        this._route_id = _route_id;
    }

    /**
     * @param _stop_id the _stop_id to set
     */
    public void setStopId(Integer _stop_id) 
    {
        this._stop_id = _stop_id;
    }

    /**
     * @param _qty_to_deliver the _qty_to_deliver to set
     */
    public void setQtyToDeliver(Double _qty_to_deliver) 
    {
        this._qty_to_deliver = _qty_to_deliver;
    }

    /**
     * @param _qty_delivered the _qty_delivered to set
     */
    public void setQtyDelivered(Double _qty_delivered) 
    {
        this._qty_delivered = _qty_delivered;
    }

    /**
     * @param _date_delivered the _date_delivered to set
     */
    public void setDateDelivered(Date _date_delivered) 
    {
        this._date_delivered = _date_delivered;
    }

    /**
     * @param _is_complete the _is_complete to set
     */
    public void setIsComplete(Integer _is_complete) 
    {
        this._is_complete = _is_complete;
    }

    /**
     * @param order_num the order_num to set
     */
    public void setOrderNumber(Integer order_num) 
    {
        this._order_number = order_num;
    }
   
        /**
     * @param total_distance the total_distance to set
     */
    public void setTotalDistance(Double total_distance) 
    {
        this._total_distance = total_distance;
    }
    
    /**
     * @param _value_per_mile the _value_per_mile to set
     */
    public void setValuePerMile(Double _value_per_mile) 
    {
        this._value_per_mile = _value_per_mile;
    }
   
    /**
     * @param stopName the stopName to set
     */
    public void setStopName(String stopName) 
    {
        this._stop_name = stopName;
    }
    
    /**
     * Loads an instance
     * @param idFieldValue 
     */
    @Override
    public void loadById(Integer idFieldValue)
    {  
        /* Do not use a factory to create this object because it uses a generic*/
        RouteStops routeStopsCollection = new RouteStops();
        routeStopsCollection.loadSingleInstance(idFieldValue);
        
        RouteStop routeStop = routeStopsCollection.next();
        if(this.isIntegerNotNull(routeStop.getId())) {
            this.setRouteStopId(routeStop.getRouteStopId());
            this.setRouteId(routeStop.getRouteId());
            this.setStopId(routeStop.getStopId());
            this.setStopName(routeStop.getStopName());
            this.setQtyToDeliver(routeStop.getQtyToDeliver());
            this.setQtyDelivered(routeStop.getQtyDelivered());
            this.setDateDelivered(routeStop.getDateDelivered());
            this.setTotalDistance(routeStop.getTotalDistance());
            this.setValuePerMile(routeStop.getValuePerMile());
            this.setOrderNumber(routeStop.getOrderNumber());
            this.setIsComplete(routeStop.getIsComplete());
        }    
    }
   
    /**
     * Saves the route stop
     * @return 
     */
    @Override
    public boolean save()
    {
        boolean didRecordSaveCorrectly;
        RouteStopSave resourceSaveRouteStop = new RouteStopSave();
        
        if(!this.doesRecordAlreadyExist()) { 
            didRecordSaveCorrectly = resourceSaveRouteStop.saveNew(this);
        } else {
            didRecordSaveCorrectly = resourceSaveRouteStop.saveExisting(this);
        }

        return didRecordSaveCorrectly;
    }    
    /**
     * Cloner
     * @return 
     */
    @Override
    public RouteStop cloneObject() 
    {
        return new RouteStop();
    }
}
