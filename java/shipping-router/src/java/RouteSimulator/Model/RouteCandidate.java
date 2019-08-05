/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouteSimulator.Model;

import Core.Model.AbstractCollection;
import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.Model.Arguments.ArgumentObject;
import ObjectCreation.Model.Arguments.ArgumentStandard;
import ObjectCreation.InterfacesCreational.InstanceFactoryStandardInterface;
import ObjectCreation.Model.Arguments.ArgumentInteger;
import ObjectCreation.Model.ObjectFactory;
import ObjectCreation.Model.Prototypes.CollectionStandard;
import RouteMatrixBuilder.Model.BindPossibilitiesToStops;
import Routes.Model.Route;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class RouteCandidate extends AbstractCollection<RouteStopCandidate>
    implements InstanceFactoryStandardInterface 
{
    private Route _route;
    
    private Double _totalDistance;
            
    private Double _distanceLastSegment;
    
    private RouteStopCandidate _hub;
    
    private TruckCandidateSample _truckCandidateSample;
    
    private Double _valuePerMile;
    
    /**
     * Constructor
     * @param arguments 
     */
    @Override
    public void construct(ArgumentCollection arguments) 
    {
        try {
            ArgumentObject argumentRouteObject = arguments.getObjectArgumentByName("route");
            Route route = (Route) argumentRouteObject.getValue();
            ArgumentObject<TruckCandidateSample> argumentTruckCandidateSample = arguments.getObjectArgumentByName("truck_candidate_sample");
            TruckCandidateSample truckCandidateSample = argumentTruckCandidateSample.getValue();
            this._setRoute(route);
            this._setTruckCandidateSample(truckCandidateSample);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RouteCandidate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Creates an instance of route candidate object
     * @param possibilitiesArray
     * @param hubId
     * @param bindPossibilitiesToStops
     * @return 
     */
    public static RouteCandidate createInstance(ArrayList<Integer> possibilitiesArray, Integer hubId, 
            BindPossibilitiesToStops bindPossibilitiesToStops, Integer truckCandidateSampleId)
    {
        TruckCandidateSample truckSampleCandidate = TruckCandidateSample.createInstance(truckCandidateSampleId);
        
        ArrayList<ArgumentStandard> arrayListArguments = new ArrayList<>();   
        CollectionStandard<Integer> routeCandidates = new CollectionStandard<>(possibilitiesArray);
        ArgumentObject<CollectionStandard<Integer>> argumentCollectionPossibilities = new ArgumentObject("route_candidates",routeCandidates);
        ArgumentInteger argumentHubId = new ArgumentInteger("hub_id", hubId);
        ArgumentObject argumentBinderPossibilitiesToStops = new ArgumentObject("bindPossibilitiesToStops", bindPossibilitiesToStops);
        ArgumentObject<TruckCandidateSample> argumentTruckCandidateSample = new ArgumentObject("truck_candidate_sample", truckSampleCandidate);
        arrayListArguments.add(argumentCollectionPossibilities);
        arrayListArguments.add(argumentHubId);
        arrayListArguments.add(argumentBinderPossibilitiesToStops);
        arrayListArguments.add(argumentTruckCandidateSample);
        CollectionStandard<ArgumentStandard> standardCollection = new CollectionStandard<>(arrayListArguments);
        ArgumentCollection routeCandidateArguments = new ArgumentCollection(standardCollection);
        RouteCandidate routeCandidate = (RouteCandidate) ObjectFactory.get("RouteCandidate", routeCandidateArguments);
        return routeCandidate;
    }
    
    /**
     * Clones object.  This is never used because builder creates the object.
     * @return 
     */
    @Override
    public RouteCandidate cloneObject() 
    {
        return new RouteCandidate();
    }

    @Override
    public void load() {}

    /**
     * Creates a new item for the collection
     * @return 
     */
    @Override
    public RouteStopCandidate createNewItem() 
    {
        return new RouteStopCandidate();
    }
    
    /**
     * @param _route the _route to set
     */
    private void _setRoute(Route _route) 
    {
        this._route = _route;
    }
   
    /**
     * @param _hub the _hub to set
     */
    public void setHub(RouteStopCandidate _hub) 
    {
        this._hub = _hub;
    }
    
    /**
     * @param _totalDistance the _totalDistance to set
     */
    public void setTotalDistance(Double _totalDistance) 
    {
        this._totalDistance = _totalDistance;
    }
    
    /**
     * @param _distanceLastSegment the _distanceLastSegment to set
     */
    public void setDistanceLastSegment(Double _distanceLastSegment) {
        this._distanceLastSegment = _distanceLastSegment;
    }
    
    /**
     * @param _truckCandidateSample the _truckCandidateSample to set
     */
    private void _setTruckCandidateSample(TruckCandidateSample _truckCandidateSample) 
    {
        this._truckCandidateSample = _truckCandidateSample;
    }
 
    /**
     * @param _valuePerMile the _valuePerMile to set
     */
    public void setValuePerMile(Double _valuePerMile) 
    {
        this._valuePerMile = _valuePerMile;
    }
    
    /**
     * @return the _route
     */
    public Route getRoute() 
    {
        return _route;
    }

    /**
     * @return the _totalDistance
     */
    public Double getTotalDistance() 
    {
        return _totalDistance;
    }

    /**
     * @return the _distanceLastSegment
     */
    public Double getDistanceLastSegment() 
    {
        return _distanceLastSegment;
    }

    /**
     * @return the _hub
     */
    public RouteStopCandidate getHub() 
    {
        return _hub;
    }

    /**
     * @return the _truckCandidateSample
     */
    public TruckCandidateSample getTruckCandidateSample() 
    {
        return _truckCandidateSample;
    }
    
    /**
     * @return the _valuePerMile
     */
    public Double getValuePerMile() 
    {
        return _valuePerMile;
    }
}
