/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testframework.controllers;

import Core.controller.AbstractController;
import RouteSimulator.Model.Collections.RouteProxyCollection;
import RouteSimulator.Model.RouteCandidate;
import RouteSimulator.Model.RouteStopCandidate;
import RouteSimulator.Model.TruckCandidateSample;

/**
 *
 * @author chris
 */
public class RouteGenerator extends AbstractController {
          /**
     * Runs the program
     * @param args 
     */
    public static void main(String args[])
    { 
        RouteGenerator currentController = new RouteGenerator();
        currentController.generateAllPossibleRoutes(1);
    } 
    
    public void generateAllPossibleRoutes(Integer hubId)
    {
        RouteProxyCollection routeProxyCollection = RouteProxyCollection.createInstance(hubId, 1);
        Integer routeCandidateCounter = 1;
        for(RouteCandidate routeCandidate : routeProxyCollection) {
            this.println("Route Candidate: " + routeCandidateCounter);
            this.println("Distance Last Segment: " + routeCandidate.getDistanceLastSegment());
            this.println("Truck ID: " + routeCandidate.getTruckCandidateSample().getTruck().getId());
            this.println("Default Qty Deliver Each Stop: " + routeCandidate.getTruckCandidateSample().getDefaultQtyAllocationPerStop());
            this.println("Value Per Mile (Total): " + routeCandidate.getValuePerMile());
            this.printBlankLine();
            
            for(RouteStopCandidate routeStopCandidate : routeCandidate) {
                System.out.println(routeStopCandidate.getStop().getId() + " : " + routeStopCandidate.getStop().getStopName());
                System.out.println("Total Distance : " + routeStopCandidate.getTotalDistance());
                System.out.println("Adjusted Distance : " + routeStopCandidate.getAdjustedTotalDistance());
                System.out.println("Discounted Miles : " + routeStopCandidate.getDiscountedMiles());
                System.out.println("Quantity Requested : " + routeStopCandidate.getQtyToDeliver());
                System.out.println("Number Truckloads Deliver All: " + routeStopCandidate.getNumberTruckloadsNeededDeliverAllGoods());
                System.out.println("Value Per Mile: " + routeStopCandidate.getValuePerMile());
                this.printBlankLine();
            }
            
            this.printBlankLine();
            this.printBlankLine();
            routeCandidateCounter++;
        }
    }
}
