/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouteSimulator.Model.Calculator;

import ObjectCreation.Model.Prototypes.CollectionStandard;
import RouteSimulator.Model.RouteCandidate;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class CalculatorExecutor extends CollectionStandard<SimulatorCalculatorInterface> implements SimulatorCalculatorInterface
{

    public CalculatorExecutor(ArrayList<SimulatorCalculatorInterface> items) {
        super(items);
    }
    
    /**
     * Executes all the calculators
     * @param routeCandidate 
     */
    @Override
    public void execute(RouteCandidate routeCandidate) 
    {
        for(SimulatorCalculatorInterface executor : this) {
            executor.execute(routeCandidate);
        }
    }
}
