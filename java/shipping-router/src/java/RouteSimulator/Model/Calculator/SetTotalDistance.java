/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouteSimulator.Model.Calculator;

import RouteSimulator.Model.RouteCandidate;
import RouteSimulator.Model.RouteStopCandidate;
import Routes.Model.Statistic.DistanceBetweenStops;
import Routes.Model.Stop;

/**
 *
 * @author chris
 */
public class SetTotalDistance implements SimulatorCalculatorInterface {

    @Override
    public void execute(RouteCandidate routeCandidate) 
    {
        DistanceBetweenStops distanceBetweenStops;
        Stop currentStop;
        Stop previousStop;
        Double totalDistance;
        Double allDistancesCombined = 0.0;
        
        for(RouteStopCandidate routeStopCandidate : routeCandidate) {
            if(!routeStopCandidate.isFirstStop() && routeStopCandidate.hasPreviousStop()) {
                currentStop = routeStopCandidate.getStop();
                previousStop = routeStopCandidate.getPreviousRouteStopCandidate().getStop();
                distanceBetweenStops = new DistanceBetweenStops(currentStop, previousStop);
                totalDistance = distanceBetweenStops.getValue();
                routeStopCandidate.setTotalDistance(totalDistance);
                allDistancesCombined += totalDistance;
            }
        }
        
        routeCandidate.setTotalDistance(allDistancesCombined);
    }
    
}
