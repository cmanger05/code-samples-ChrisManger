/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouteSimulator.Model;

import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.Model.Arguments.ArgumentObject;
import ObjectCreation.InterfacesCreational.InstanceFactoryBaseInterface;
import ObjectCreation.InterfacesCreational.InstanceFactoryBuilderInterface;
import ObjectCreation.Model.Arguments.ArgumentInteger;
import ObjectCreation.Model.Arguments.ArgumentStandard;
import ObjectCreation.Model.Prototypes.CollectionStandard;
import RouteMatrixBuilder.Model.BindPossibilitiesToStops;
import RouteSimulator.Model.Calculator.CalculatorExecutor;
import RouteSimulator.Model.Calculator.SetAdjustedDistance;
import RouteSimulator.Model.Calculator.SetDefaultQtyDeliverEachStop;
import RouteSimulator.Model.Calculator.SetDiscountedMiles;
import RouteSimulator.Model.Calculator.SetDistanceLastSegment;
import RouteSimulator.Model.Calculator.SetNumberTruckloadsDeliverEachStop;
import RouteSimulator.Model.Calculator.SetPreviousStop;
import RouteSimulator.Model.Calculator.SetQuantityToDeliver;
import RouteSimulator.Model.Calculator.SetTotalDistance;
import RouteSimulator.Model.Calculator.SetValuePerMile;
import RouteSimulator.Model.Calculator.SimulatorCalculatorInterface;
import Routes.Model.Route;
import Routes.Model.Stop;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class RouteCandidateBuilder implements InstanceFactoryBuilderInterface 
{
    @Override
    public InstanceFactoryBaseInterface buildInstance(ArgumentCollection arguments) 
    {
        RouteCandidate routeCandidate = this._createEmptyRouteCandidate(arguments);
        
        try { 
            ArgumentObject argumentRouteCandidates = arguments.getObjectArgumentByName("route_candidates");
            ArgumentInteger argumentHubId = arguments.getIntegerArgumentByName("hub_id");
            Integer hubId = argumentHubId.getValue();
            CollectionStandard<Integer> routeCandidates = (CollectionStandard<Integer>) argumentRouteCandidates.getValue();
            ArgumentObject argumentBindPossibilitiesToStops = arguments.getObjectArgumentByName("bindPossibilitiesToStops");
            BindPossibilitiesToStops bindPossibilitiesToStops = (BindPossibilitiesToStops) argumentBindPossibilitiesToStops.getValue();
            
            Stop stop;
            Integer stopOrderNumber = 1;
            RouteStopCandidate routeStopCandidate;
            Integer totalNumberOfStops = routeCandidates.getItems().size();
            Integer isFirstStop;
            Integer isLastStop;
            Integer isSecondToLastStop;
            
            for(Integer possibilityId : routeCandidates) {
                stop = bindPossibilitiesToStops.getItemByKey(possibilityId);
                isFirstStop = (stopOrderNumber == 1) ? 1 : 0;
                isLastStop = (Objects.equals(stopOrderNumber, totalNumberOfStops)) ? 1 : 0;    
                isSecondToLastStop = (Objects.equals(stopOrderNumber, totalNumberOfStops-1)) ? 1 : 0;
                routeStopCandidate = RouteStopCandidate.createInstance(routeCandidate, stop, isFirstStop, isLastStop, isSecondToLastStop);
                routeStopCandidate.setStopNumber(stopOrderNumber);
                if(routeStopCandidate.isFirstStop()) { //first stop is always the hub!
                    routeCandidate.setHub(routeStopCandidate);
                }
                
                routeCandidate.addItem(stopOrderNumber, routeStopCandidate);
                stopOrderNumber++;
            }
            
            ArrayList<SimulatorCalculatorInterface> executors = new ArrayList<>();
            executors.add(new SetPreviousStop());
            executors.add(new SetQuantityToDeliver());
            executors.add(new SetDefaultQtyDeliverEachStop());
            executors.add(new SetNumberTruckloadsDeliverEachStop());
            executors.add(new SetTotalDistance());
            executors.add(new SetDistanceLastSegment());
            executors.add(new SetDiscountedMiles());
            executors.add(new SetAdjustedDistance());
            executors.add(new SetValuePerMile());
            CalculatorExecutor masterExecutor = new CalculatorExecutor(executors);
            masterExecutor.execute(routeCandidate);
            
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RouteCandidateBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList<RouteStopCandidate> routeStopCandidateArrayList = new ArrayList<>();
        return routeCandidate;
    }

    /**
     * Creates an empty route candidate object
     * @return 
     */
    private RouteCandidate _createEmptyRouteCandidate(ArgumentCollection additionalArguments)
    {
        Route route = Route.createInstance();
        ArrayList<ArgumentStandard> arrayListArguments = new ArrayList<>(); 
        ArgumentObject<Route> argumentCollectionRoute = new ArgumentObject("route", route);
        arrayListArguments.add(argumentCollectionRoute);

        try {
            ArgumentObject<TruckCandidateSample> argumentTruckCandidateSample = additionalArguments.getObjectArgumentByName("truck_candidate_sample");
            arrayListArguments.add(argumentTruckCandidateSample);
        } catch (CloneNotSupportedException ex) {
            
        }
        
        CollectionStandard<ArgumentStandard> standardCollection = new CollectionStandard<>(arrayListArguments);
        ArgumentCollection routeCandidateArguments = new ArgumentCollection(standardCollection);
        RouteCandidate routeCandidate = new RouteCandidate();
        routeCandidate.construct(routeCandidateArguments);
        return routeCandidate;
    }
    
    /**
     * Returns instance of object
     * @return 
     */
    @Override
    public InstanceFactoryBaseInterface cloneObject() {
        return new RouteCandidateBuilder();
    }  
}
