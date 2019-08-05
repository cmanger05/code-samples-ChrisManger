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
public class SetNumberTruckloadsDeliverEachStop implements SimulatorCalculatorInterface {
    
    /**
     * Executes
     * @param routeCandidate 
     */
    @Override
    public void execute(RouteCandidate routeCandidate) 
    {
        Double defaultQtyDeliverEachStop = routeCandidate.getTruckCandidateSample().getDefaultQtyAllocationPerStop();
        Double amountGoodsNeedDeliveredToStop;
        Double numberDeliveriesNeededFulfillStopsRequirements;
        
        for(RouteStopCandidate routeStopCandidate : routeCandidate) {
            if(!routeStopCandidate.isHub()) { //Hubs don't get deliveries!
                amountGoodsNeedDeliveredToStop = routeStopCandidate.getQtyToDeliver();
                numberDeliveriesNeededFulfillStopsRequirements = amountGoodsNeedDeliveredToStop / defaultQtyDeliverEachStop;
                routeStopCandidate.setNumberTruckloadsNeededDeliverAllGoods(numberDeliveriesNeededFulfillStopsRequirements);
            }
        }
    }
}
