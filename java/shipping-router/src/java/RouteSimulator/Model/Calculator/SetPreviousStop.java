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
public class SetPreviousStop implements SimulatorCalculatorInterface 
{
    /**
     * Executes
     * @param routeCandidate 
     */
    @Override
    public void execute(RouteCandidate routeCandidate)
    {
        Integer orderNumberNextStop;
        RouteStopCandidate previousRouteStopCandidate;
        
        for(RouteStopCandidate routeCandidateStop : routeCandidate) {
            if(!routeCandidateStop.isFirstStop()) {
                orderNumberNextStop = routeCandidateStop.getStopNumber() - 1;
                previousRouteStopCandidate = routeCandidate.getItemByKey(orderNumberNextStop);
                routeCandidateStop.setPreviousRouteStopCandidate(previousRouteStopCandidate);
            }
        }
    }
}
