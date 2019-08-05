/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouteSimulator.Model.Calculator;

import RouteSimulator.Model.RouteCandidate;
import RouteSimulator.Model.RouteStopCandidate;

/**
 *
 * @author chris
 */
public class SetAdjustedDistance implements SimulatorCalculatorInterface 
{    
    /**
     * Executes
     * @param routeCandidate 
     */
    @Override
    public void execute(RouteCandidate routeCandidate) 
    {
        Double totalDistance;
        Integer totalNumberStops = routeCandidate.getSize();
        Double totalDistanceLastSegment = routeCandidate.getDistanceLastSegment();
        if(totalDistanceLastSegment > 0 && totalNumberStops > 1) {
            Double milesLastSegmentDividedStops = totalDistanceLastSegment / (totalNumberStops - 1);
            Double totalDistanceAdjusted;

            for(RouteStopCandidate routeStopCandidate : routeCandidate) {
                if(!routeStopCandidate.isHub() &&  routeStopCandidate.hasPreviousStop()) {
                    totalDistance = routeStopCandidate.getTotalDistance();
                    totalDistanceAdjusted = totalDistance + milesLastSegmentDividedStops;
                    routeStopCandidate.setAdjustedTotalDistance(totalDistanceAdjusted);
                }
            } 
        }
    }
}
