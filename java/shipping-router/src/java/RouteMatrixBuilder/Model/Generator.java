/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouteMatrixBuilder.Model;

import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class Generator 
{
    /**
     * Returns all the routes
     * @param numberOfStops
     * @return 
     */
    public ArrayList<ArrayList<Integer>> getAllRoutes(Integer numberOfStops)
    {
        RouteProducer routeProducer = this._createGenerator(numberOfStops);
        routeProducer.generateRouteMatrix();
        AppendHub routeHubAppender = new AppendHub(routeProducer);
        ArrayList<ArrayList<Integer>> allRoutes = routeHubAppender.getRoutes();
        return allRoutes;
    }
    
    /**
     * Creates a generator, which finds all the different possible routes
     * @param numberOfStops
     * @return 
     */
    private RouteProducer _createGenerator(Integer numberOfStops)
    {
        RouteProducer routeProducer = new RouteProducer(numberOfStops, true);
        RouteProducer child = routeProducer;
        
        for(int i = 1; i <= numberOfStops; i++) {
            child.addChild(numberOfStops);
            child = child.getChild();
        }
        
        return routeProducer;
    }
}
