/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouteSimulator.Model.Collections;

import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.Model.Arguments.ArgumentInteger;
import ObjectCreation.Model.Arguments.ArgumentStandard;
import ObjectCreation.InterfacesCreational.InstanceFactoryBaseInterface;
import ObjectCreation.Model.ObjectFactory;
import ObjectCreation.Model.Prototypes.CollectionStandard;
import RouteSimulator.Model.RouteCandidate;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class RouteProxyCollection extends CollectionStandard<RouteCandidate>
    implements InstanceFactoryBaseInterface
{
    private Integer _hubId;
    
    /**
     * Constructor
     * @param items 
     */
    public RouteProxyCollection(ArrayList<RouteCandidate> items) 
    {
        super(items);
    }
 
    /**
     * Creates an instance of this class
     * @param hubId
     * @param truckSampleCandidateId
     * @return 
     */
    public static RouteProxyCollection createInstance(Integer hubId, Integer truckSampleCandidateId)
    {
        ArrayList<ArgumentStandard> arrayListArguments = new ArrayList<>();
        ArgumentInteger argumentHubId = new ArgumentInteger("hub_id", hubId);
        ArgumentInteger argumentTruckCandidateSampleId = new ArgumentInteger("truck_Candidate_sample_id", truckSampleCandidateId);
        arrayListArguments.add(argumentHubId);
        arrayListArguments.add(argumentTruckCandidateSampleId);
        
        CollectionStandard<ArgumentStandard> standardCollection = new CollectionStandard(arrayListArguments);
        ArgumentCollection argumentCollection = new ArgumentCollection(standardCollection);
        RouteProxyCollection routeProxyCollection = (RouteProxyCollection) ObjectFactory.get("RouteProxyCollection", argumentCollection);

        return routeProxyCollection;
    }
      
        /**
     * Gets the hub id
     * @return the _hubId
     */
    public Integer getHubId() 
    {
        return _hubId;
    }

    /**
     * Sets the hub id
     * @param _hubId the _hubId to set
     */
    public void setHubId(Integer _hubId) 
    {
        this._hubId = _hubId;
    }
    
    /**
     * Never called but required by constructor
     * The builder class always creates this object
     * @return 
     */
    @Override
    public InstanceFactoryBaseInterface cloneObject() {
        ArrayList<RouteCandidate> allPossibilities = new ArrayList<>();
        return new RouteProxyCollection(allPossibilities);
    }
}
