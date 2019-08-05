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
public class SetDiscountedMiles implements SimulatorCalculatorInterface
{
    @Override
    public void execute(RouteCandidate routeCandidate) 
    {
        DistanceBetweenStops distanceCalculator;
        Double distanceFromHubToStore;
        Double distanceFromPreviousStopToCurrentStop;
        Double discountedMiles;
        Stop currentStop;
        Stop hub = routeCandidate.getHub().getStop();
        
        
        for(RouteStopCandidate routeStopCandidate : routeCandidate) {
            discountedMiles = 0.0;
            if(!routeStopCandidate.isHub() && routeStopCandidate.getStopNumber() > 2 &&  routeStopCandidate.hasPreviousStop()) {
                currentStop = routeStopCandidate.getStop();
                distanceFromPreviousStopToCurrentStop = routeStopCandidate.getTotalDistance();
                distanceCalculator = new DistanceBetweenStops(currentStop, hub);
                distanceFromHubToStore = distanceCalculator.getValue();
                Double numberTruckloadsNeededDeliverAllGoods = routeStopCandidate.getNumberTruckloadsNeededDeliverAllGoods();
                if(distanceFromHubToStore > distanceFromPreviousStopToCurrentStop) {
                    discountedMiles = (distanceFromHubToStore - distanceFromPreviousStopToCurrentStop) / numberTruckloadsNeededDeliverAllGoods;
                }
            }
            
            routeStopCandidate.setDiscountedMiles(discountedMiles);
        }     
    }
}
