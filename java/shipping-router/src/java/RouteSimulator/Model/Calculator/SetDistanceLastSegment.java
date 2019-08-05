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
public class SetDistanceLastSegment implements SimulatorCalculatorInterface 
{    
    /**
     * Executes
     * @param routeCandidate 
     */
    @Override
    public void execute(RouteCandidate routeCandidate) 
    {
        Double totalDistanceLastSegment;
        
        for(RouteStopCandidate routeStopCandidate : routeCandidate) {
            if(routeStopCandidate.isLastStop() && routeStopCandidate.hasPreviousStop()) {
                totalDistanceLastSegment = routeStopCandidate.getTotalDistance();
                routeCandidate.setDistanceLastSegment(totalDistanceLastSegment);
            }
        } 
    }
}
