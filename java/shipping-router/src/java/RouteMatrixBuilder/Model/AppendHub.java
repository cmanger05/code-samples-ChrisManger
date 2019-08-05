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
public class AppendHub {
    
    private ArrayList<ArrayList<Integer>> _routes;
    
    private AppendHub() {
        ArrayList<ArrayList<Integer>> routesWithHubAppended = new ArrayList<>();
        this._setRoutes(routesWithHubAppended);
    }
    
    /**
     * Appends the hub to beginning and the end
     * @param routeProducer 
     */
    public AppendHub(RouteProducer routeProducer)
    {
        Integer hubStopLocation = routeProducer.getNumberStops() + 1;
        ArrayList<ArrayList<Integer>> routesWithHubAppended = new ArrayList<>();
        ArrayList<ArrayList<Integer>> allRoutes = routeProducer.getAllRoutes();
        for(ArrayList<Integer> routeOriginal : allRoutes) {
            ArrayList<Integer> routeWithHub = new ArrayList<>();
            routeWithHub.add(hubStopLocation);
            for(Integer spotNumber : routeOriginal) {
                routeWithHub.add(spotNumber);
            }
            routeWithHub.add(hubStopLocation);
            routesWithHubAppended.add(routeWithHub);
            
        }
        
        this._setRoutes(routesWithHubAppended);         
    }

    /**
     * @return the _routes
     */
    public ArrayList<ArrayList<Integer>> getRoutes() 
    {
        return _routes;
    }

    /**
     * @param _routes the _routes to set
     */
    private void _setRoutes(ArrayList<ArrayList<Integer>> _routes) 
    {
        this._routes = _routes;
    }
}
