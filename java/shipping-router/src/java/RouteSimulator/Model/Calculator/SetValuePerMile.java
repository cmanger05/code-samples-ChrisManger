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
public class SetValuePerMile implements SimulatorCalculatorInterface 
{
    private static final Double AVERAGE_VALUE_PER_SQUARE_FOOT_GOODS = 75.0;
    /**
     * Executes the task
     * @param routeCandidate 
     */
    @Override
    public void execute(RouteCandidate routeCandidate)
    {
        Double valueOfGoods;
        Double valuationCritereaDivisor;
        Double valuePerMile;
        
        Double qtyCapableBeingCarriedInOneDelivery = routeCandidate.getTruckCandidateSample().getDefaultQtyAllocationPerStop();
        Double desiredQty;
        Double qtyAssignedToTruck;
        Double routesValuePerMile = 0.0;
        
        for(RouteStopCandidate routeStopCandidate : routeCandidate) {
            if(!routeStopCandidate.isHub()) { //Hubs don't get deliveries!
                desiredQty = routeStopCandidate.getQtyToDeliver();
                if(desiredQty > qtyCapableBeingCarriedInOneDelivery) {
                    qtyAssignedToTruck = qtyCapableBeingCarriedInOneDelivery;
                } else {
                    qtyAssignedToTruck = desiredQty;
                }
                
                valueOfGoods = qtyAssignedToTruck * SetValuePerMile.AVERAGE_VALUE_PER_SQUARE_FOOT_GOODS;
                valuationCritereaDivisor = routeStopCandidate.getAdjustedTotalDistance() - routeStopCandidate.getDiscountedMiles();
                if(valuationCritereaDivisor < 0) { //prevents it from being a negative number
                    valuationCritereaDivisor = 1.0;
                }
                
                valuePerMile = valueOfGoods / valuationCritereaDivisor;
                routeStopCandidate.setValuePerMile(valuePerMile);
                routesValuePerMile += valuePerMile;
            }
        }
        
        routeCandidate.setValuePerMile(routesValuePerMile);
    }
}
