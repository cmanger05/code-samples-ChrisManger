/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouteSimulator.Model.Calculator;

import RouteSimulator.Model.RouteCandidate;
import RouteSimulator.Model.TruckCandidateSample;

/**
 *
 * @author chris
 */
public class SetDefaultQtyDeliverEachStop implements SimulatorCalculatorInterface {
        /**
     * Executes
     * @param routeCandidate 
     */
    @Override
    public void execute(RouteCandidate routeCandidate) 
    {
        Integer numberOfStops = routeCandidate.getSize();
        TruckCandidateSample truckCandidateSample = routeCandidate.getTruckCandidateSample();
        Double holdingCapacity = truckCandidateSample.getTruck().getHoldingCapacity();
        Double numberStopsBeingDeliveredTo = Math.max(numberOfStops - 2,1.0);
        Double defaultQtyDeliverEachStop = holdingCapacity / numberStopsBeingDeliveredTo;
        truckCandidateSample.setDefaultQtyAllocationPerStop(defaultQtyDeliverEachStop);
    }
}
