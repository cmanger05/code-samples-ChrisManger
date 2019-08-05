/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouteSimulator.Model;

import Core.Model.AbstractModel;
import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.Model.Arguments.ArgumentObject;
import ObjectCreation.InterfacesCreational.InstanceFactoryStandardInterface;
import ObjectCreation.Model.Arguments.ArgumentInteger;
import ObjectCreation.Model.Arguments.ArgumentStandard;
import ObjectCreation.Model.ObjectFactory;
import ObjectCreation.Model.Prototypes.CollectionStandard;
import Routes.Model.Route;
import Routes.Model.Stop;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class RouteStopCandidate extends AbstractModel implements InstanceFactoryStandardInterface 
{
    private Route _route;
    
    private Stop _stop;
    
    private RouteCandidate _routeCandidate;
    
    private Integer _routeOrder;
    
    private Boolean _isFirstStop;
    
    private Boolean _isLastStop;
    
    private Boolean _isSecondLastStop;
    
    private Double _totalDistance;
    
    private Double _discountedMiles;
    
    private Double _adjustedTotalDistance;
    
    private RouteStopCandidate _previousStop;
    
    private Double _qtyToDeliver;
    
    private Double _numberTruckloadsNeededDeliverAllGoods;
    
    private Double _valuePerMile;
    
    /**
     * Constructor
     * @param arguments 
     */
    @Override
    public void construct(ArgumentCollection arguments) {

        try {
            ArgumentObject argumentStopObject = arguments.getObjectArgumentByName("stop");
            ArgumentObject argumentRouteObject = arguments.getObjectArgumentByName("route");
            ArgumentObject argumentRouteCandidateObject = arguments.getObjectArgumentByName("route_candidate");
            ArgumentInteger argumentIsFirstStop = arguments.getIntegerArgumentByName("is_first_stop");
            ArgumentInteger argumentIsLastStop = arguments.getIntegerArgumentByName("is_last_stop");
            ArgumentInteger argumentIsSecondToLastStop = arguments.getIntegerArgumentByName("is_second_to_last_stop");
            Stop stop = (Stop) argumentStopObject.getValue();
            Route route = (Route) argumentRouteObject.getValue();
            RouteCandidate routeCandidate = (RouteCandidate) argumentRouteCandidateObject.getValue();
            Boolean isFirstStop = (argumentIsFirstStop.getValue() == 1);
            Boolean isLastStop = (argumentIsLastStop.getValue() == 1);
            Boolean isSecondToLastStop = (argumentIsSecondToLastStop.getValue() == 1);
            this._setRoute(route);
            this._setStop(stop);
            this._setIsFirstStop(isFirstStop);
            this._setIsLastStop(isLastStop);
            this._setIsSecondToLastStop(isSecondToLastStop);
            this._setRouteCandidate(routeCandidate);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RouteStopCandidate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Creates a new instance of the object
     * @param routeCandidate
     * @param stop
     * @param isFirstStop
     * @param isLastStop
     * @param isSecondToLastStop
     * @return 
     */
    public static RouteStopCandidate createInstance(RouteCandidate routeCandidate, Stop stop, 
            Integer isFirstStop, Integer isLastStop, Integer isSecondToLastStop)
    {
        ArrayList<ArgumentStandard> arrayListArguments = new ArrayList<>();
        Route route = routeCandidate.getRoute();
        ArgumentObject<Route> argumentCollectionRoute = new ArgumentObject("route", route);
        ArgumentObject<Stop> argumentCollectionStop = new ArgumentObject("stop", stop);
        ArgumentObject<RouteCandidate> argumentRouteCandidate = new ArgumentObject("route_candidate", routeCandidate);
        ArgumentInteger argumentIsFirstStop = new ArgumentInteger("is_first_stop", isFirstStop);
        ArgumentInteger argumentIsLastStop = new ArgumentInteger("is_last_stop", isLastStop);
        ArgumentInteger argumentIsSecondToLastStop = new ArgumentInteger("is_second_to_last_stop", isSecondToLastStop);
        arrayListArguments.add(argumentCollectionRoute);
        arrayListArguments.add(argumentCollectionStop);
        arrayListArguments.add(argumentIsFirstStop);
        arrayListArguments.add(argumentIsLastStop);
        arrayListArguments.add(argumentIsSecondToLastStop);
        arrayListArguments.add(argumentRouteCandidate);
        CollectionStandard<ArgumentStandard> standardCollection = new CollectionStandard<>(arrayListArguments);
        ArgumentCollection routeCandidateArguments = new ArgumentCollection(standardCollection);
        RouteStopCandidate routeStopCandidate = (RouteStopCandidate) ObjectFactory.get("RouteStopCandidate", routeCandidateArguments);
        return routeStopCandidate;
    }
    
    /**
     * The id of this object is its order.
     * @return 
     */
    @Override
    public Integer getId() 
    {
        return this.getStopNumber();
    }
    
    /**
     * Returns the route
     * @return 
     */
    public Route getRoute()
    {
        return this._route;
    }
    
    /**
     * Returns the stop
     * @return 
     */
    public Stop getStop()
    {
        return this._stop;
    }
    
        /**
     * @return the _routeOrder
     */
    public Integer getStopNumber() 
    {
        return _routeOrder;
    }
 
    /**
     * @return the _totalDistance
     */
    public Double getTotalDistance() 
    {
        return _totalDistance;
    }
    
    /**
     * @return the _previousStop
     */
    public RouteStopCandidate getPreviousRouteStopCandidate() 
    {
        return _previousStop;
    }
 
    /**
     * @return the _routeCandidate
     */
    public RouteCandidate getRouteCandidate() 
    {
        return _routeCandidate;
    }
    
    /**
     * @return the _adjustedTotalDistance
     */
    public Double getAdjustedTotalDistance() 
    {
        return _adjustedTotalDistance;
    }
    
    /**
     * @return the _discountedMiles
     */
    public Double getDiscountedMiles() 
    {
        return _discountedMiles;
    }
 
    /**
     * @return the _qtyToDeliver
     */
    public Double getQtyToDeliver() 
    {
        return _qtyToDeliver;
    }
    
    /**
     * @return the _numberTruckloadsNeededDeliverAllGoods
     */
    public Double getNumberTruckloadsNeededDeliverAllGoods() 
    {
        return _numberTruckloadsNeededDeliverAllGoods;
    }
    
    /**
     * @return the _valuePerMile
     */
    public Double getValuePerMile() 
    {
        return _valuePerMile;
    }
    
    /**
     * Sets the route
     * @param route 
     */
    private void _setRoute(Route route)
    {
        this._route = route;
    }
    
    /**
     * Sets the stop
     * @param stop 
     */
    private void _setStop(Stop stop)
    {
        this._stop = stop;
    }
   
    /**
     * @param _routeOrder the _routeOrder to set
     */
    public void setStopNumber(Integer _routeOrder) 
    {
        this._routeOrder = _routeOrder;
    }
   
    /**
     * Sets is first stop
     * @param isFirstStop 
     */
    private void _setIsFirstStop(Boolean isFirstStop)
    {
        this._isFirstStop = isFirstStop;
    }

    /**
     * Sets if its the last stop
     * @param isLastStop 
     */
    private void _setIsLastStop(Boolean isLastStop)
    {
        this._isLastStop = isLastStop;
    }
    
    /**
     * Sets if its second to last stop
     * @param isSecondToLastStop 
     */
    private void _setIsSecondToLastStop(Boolean isSecondToLastStop)
    {
        this._isSecondLastStop = isSecondToLastStop;
    }
  
    /**
     * @param _totalDistance the _totalDistance to set
     */
    public void setTotalDistance(Double _totalDistance) 
    {
        this._totalDistance = _totalDistance;
    }
    
    /**
     * @param _previousStop the _previousStop to set
     */
    public void setPreviousRouteStopCandidate(RouteStopCandidate _previousStop) 
    {
        this._previousStop = _previousStop;
    }
    
    /**
     * @param _routeCandidate the _routeCandidate to set
     */
    private void _setRouteCandidate(RouteCandidate _routeCandidate) 
    {
        this._routeCandidate = _routeCandidate;
    }
    
    /**
     * @param _adjustedTotalDistance the _adjustedTotalDistance to set
     */
    public void setAdjustedTotalDistance(Double _adjustedTotalDistance) 
    {
        this._adjustedTotalDistance = _adjustedTotalDistance;
    }
    
    /**
     * @param _discountedMiles the _discountedMiles to set
     */
    public void setDiscountedMiles(Double _discountedMiles) 
    {
        this._discountedMiles = _discountedMiles;
    }
    
    /**
     * @param _qtyToDeliver the _qtyToDeliver to set
     */
    public void setQtyToDeliver(Double _qtyToDeliver) 
    {
        this._qtyToDeliver = _qtyToDeliver;
    }
    
    /**
     * @param _numberTruckloadsNeededDeliverAllGoods the _numberTruckloadsNeededDeliverAllGoods to set
     */
    public void setNumberTruckloadsNeededDeliverAllGoods(Double _numberTruckloadsNeededDeliverAllGoods) 
    {
        this._numberTruckloadsNeededDeliverAllGoods = _numberTruckloadsNeededDeliverAllGoods;
    }
    
    /**
     * @param _valuePerMile the _valuePerMile to set
     */
    public void setValuePerMile(Double _valuePerMile) 
    {
        this._valuePerMile = _valuePerMile;
    }
    
    /**
     * Determines if the current stop is the first stop.
     * @return 
     */
    public Boolean isFirstStop()
    {
        return this._isFirstStop;
    }
    
    /**
     * Determines if its the last stop
     * @return 
     */
    public Boolean isLastStop()
    {
        return this._isLastStop;
    }
    
    /**
     * Determines if the stop is a hub
     * @return 
     */
    public Boolean isHub()
    {
        return (this.isFirstStop() || this.isLastStop());
    }
    
    /**
     * Determines if its second to last stop.
     * @return 
     */
    public Boolean isSecondToLastStop()
    {
        return this._isSecondLastStop;
    }
    
    /**
     * Determines if the next stop has been set.
     * @return 
     */
    public Boolean hasPreviousStop()
    {
        Boolean hasNextStop = true;
        if(this._previousStop == null) {
            hasNextStop = false;
        }
        
        return hasNextStop;
    }
    
    /**
     * Clones current object
     * @return 
     */
    @Override
    public InstanceFactoryStandardInterface cloneObject() 
    {
        return new RouteStopCandidate();
    }
}
