/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouteSimulator.Model.Calculator;

import RouteSimulator.Model.RouteCandidate;
import RouteSimulator.Model.RouteStopCandidate;
import Routes.Model.Statistic.QtyToDeliver;

/**
 *
 * @author chris
 */
public class SetQuantityToDeliver implements SimulatorCalculatorInterface 
{
    /**
     * Executes
     * @param routeCandidate 
     */
    @Override
    public void execute(RouteCandidate routeCandidate) 
    {
        Double totalDistanceLastSegment;
        QtyToDeliver calculatorQtyToDeliver;
        
        for(RouteStopCandidate routeStopCandidate : routeCandidate) {
            if(!routeStopCandidate.isHub()) { //Hubs don't get deliveries!
                calculatorQtyToDeliver = new QtyToDeliver(routeStopCandidate.getStop());
                Double qtyToDeliver = calculatorQtyToDeliver.getValue();
                routeStopCandidate.setQtyToDeliver(qtyToDeliver);
            }
        } 
    }    
}
