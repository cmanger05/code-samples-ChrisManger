/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouteSimulator.Model.Collections;

import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.InterfacesCreational.InstanceFactoryBaseInterface;
import ObjectCreation.InterfacesCreational.InstanceFactoryBuilderInterface;
import ObjectCreation.Model.Arguments.ArgumentInteger;
import RouteMatrixBuilder.Model.BindPossibilitiesToStops;
import RouteMatrixBuilder.Model.Collection.RoutePossibilitiesCollection;
import RouteMatrixBuilder.Model.Generator;
import RouteSimulator.Model.RouteCandidate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class RouteProxyCollectionBuilder implements InstanceFactoryBuilderInterface {

    @Override
    public InstanceFactoryBaseInterface buildInstance(ArgumentCollection arguments) 
    {
        RouteCandidate routeCandidate;
        ArrayList<RouteCandidate> allRouteCandidates = new ArrayList<>();
        Integer hubId = 0;
        
        try {
            hubId = arguments.getIntegerArgumentByName("hub_id").getValue();
            ArgumentInteger argumentTruckCandidateSampleId = arguments.getIntegerArgumentByName("truck_Candidate_sample_id");
            Integer truckCandidateSampleId = argumentTruckCandidateSampleId.getValue();
            BindPossibilitiesToStops bindPossibilitiesToStops = BindPossibilitiesToStops.createInstance(hubId);
            Integer numberOfStops = bindPossibilitiesToStops.getSize() - 1;
            RoutePossibilitiesCollection allPossibleRoutes = this._getCollectionAllRoutePossibilities(numberOfStops);
            for(ArrayList<Integer> stopsInPossibleRoute: allPossibleRoutes) {
                routeCandidate = RouteCandidate.createInstance(stopsInPossibleRoute, hubId, bindPossibilitiesToStops, truckCandidateSampleId);
                allRouteCandidates.add(routeCandidate);
            }
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RouteProxyCollectionBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

        RouteProxyCollection routeProxyCollection = new RouteProxyCollection(allRouteCandidates);
        routeProxyCollection.setHubId(hubId);
        return routeProxyCollection;
    }

    /**
     * Returns collection route candidates
     * @param numberStops
     * @return 
     */
    private RoutePossibilitiesCollection _getCollectionAllRoutePossibilities(Integer numberStops)
    {
        Generator routeGenerator = new Generator();
        ArrayList<ArrayList<Integer>> allPossibilities = routeGenerator.getAllRoutes(numberStops);
        RoutePossibilitiesCollection routePossibilitiesCollection = new RoutePossibilitiesCollection(allPossibilities);       
        return routePossibilitiesCollection;
    }
    
    /**
     * Clones the object
     * @return 
     */
    @Override
    public RouteProxyCollectionBuilder cloneObject() {
        return new RouteProxyCollectionBuilder();
    } 
}
