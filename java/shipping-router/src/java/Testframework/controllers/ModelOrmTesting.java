/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testframework.controllers;

import Core.controller.AbstractController;
import Routes.Model.Collection.Routes;
import Routes.Model.PendingRouteCreation;
import Routes.Model.Route;
import Routes.Model.RouteStop;
import Routes.Model.Stop;
import Routes.Model.Truck;
import Users.Model.User;

/**
 *
 * @author chris
 */
public class ModelOrmTesting extends AbstractController 
{
    /**
     * Runs the program
     * @param args 
     */
    public static void main(String args[])
    { 
        ModelOrmTesting currentController = new ModelOrmTesting();
        currentController.testLoadUserRoute();
    } 
    
    /**
     * Test loading stop model
     */
    public void testLoadStop()
    {
        Stop stop = Stop.createInstance();
        stop.loadById(2);
        System.out.println(stop.getId());
        System.out.println(stop.getTypeId());
        System.out.println(stop.getStopTypeName());
        System.out.println(stop.getStopName());
        System.out.println(stop.getStreetAddress());
        System.out.println(stop.getStreetAddress2());
        System.out.println(stop.getCity());
        System.out.println(stop.getRegion());
        System.out.println(stop.getPostcode());    
        System.out.println("");
    }
    
    /**
     * Test Loading truck model
     */
    public void testLoadTruck()
    {
        Truck truck = Truck.createInstance();
        truck.loadById(3);
        System.out.println(truck.getId());
        System.out.println(truck.getTruckName());
        System.out.println(truck.getTruckTypeName());
        System.out.println(truck.getAvgSpeed());
        System.out.println(truck.getHoldingCapacity());
        System.out.println(truck.getHubId());
        System.out.println("");
    }
   
    /**
     * Tests loading a route
     */
    public void testLoadRoute()
    {
        Route route = Route.createInstance();
        route.loadById(1);
        System.out.println(route.getId());
        System.out.println(route.getName());
        System.out.println(route.getHubId());
        System.out.println(route.getTruckId());
        System.out.println(route.getDateStarted());
        System.out.println(route.getDateDelivered());
        System.out.println(route.getTotalDistance());
        System.out.println(route.getAverageMilesPerLeg());
        System.out.println(route.getValuePerMile());
        System.out.println(route.getIsComplete());
        System.out.println("");
    }
    
    /**
     * Tests loading a user route
     */
    public void testLoadUserRoute()
    {
        User user = User.createInstance();
        user.loadById(4);
        System.out.println(user.getId());
        System.out.println(user.getRoleName());
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getDateCreated());
        System.out.println("");
        
        Routes routes = new Routes();
        routes.loadByUser(user);
        for (Route userRoute : routes) {
            System.out.println(userRoute.getId());
            System.out.println(userRoute.getName());
            System.out.println(userRoute.getHubId());
            System.out.println(userRoute.getTruckId());
            System.out.println(userRoute.getDateStarted());
            System.out.println(userRoute.getDateDelivered());
            System.out.println(userRoute.getTotalDistance());
            System.out.println(userRoute.getAverageMilesPerLeg());
            System.out.println(userRoute.getValuePerMile());
            System.out.println(userRoute.getIsComplete());
            System.out.println("");
        }
    }
    
    /**
     * Tests to ensure pending route creation loading is working correctly.
     */
    public void testLoadPendingRouteCreation()
    {
        PendingRouteCreation pendingRouteCreation = PendingRouteCreation.createInstance();
        pendingRouteCreation.loadById(1);
        System.out.println(pendingRouteCreation.getPendingRouteId());
        System.out.println(pendingRouteCreation.getDateCreated());
        System.out.println(pendingRouteCreation.getStoreId());
        System.out.println(pendingRouteCreation.getQtyRequested());
        System.out.println(pendingRouteCreation.getIsCreated());
        System.out.println("");
    }
    
    public void testLoadRouteStop()
    {
        RouteStop routeStop = RouteStop.createInstance();
        routeStop.loadById(2);
        System.out.println(routeStop.getRouteStopId());
        System.out.println(routeStop.getRouteId());
        System.out.println(routeStop.getStopId());
        System.out.println(routeStop.getStopName());
        System.out.println(routeStop.getQtyToDeliver());
        System.out.println(routeStop.getQtyDelivered());
        System.out.println(routeStop.getDateDelivered());
        System.out.println(routeStop.getTotalDistance());
        System.out.println(routeStop.getValuePerMile());
        System.out.println(routeStop.getOrderNumber());
        System.out.println(routeStop.getIsComplete());
        System.out.println("");
    }
    
    /**
     * Loads all stops associated with a route
     */
    public void testLoadRouteAssociatedStops()
    {
        Routes routes = new Routes();
        routes.load();

        for (Route userRoute : routes) {
            System.out.println(userRoute.getId());
            System.out.println(userRoute.getName());
            System.out.println(userRoute.getHubId());
            System.out.println(userRoute.getTruckId());
            System.out.println(userRoute.getDateStarted());
            System.out.println(userRoute.getDateDelivered());
            System.out.println(userRoute.getTotalDistance());
            System.out.println(userRoute.getAverageMilesPerLeg());
            System.out.println(userRoute.getValuePerMile());
            System.out.println(userRoute.getIsComplete());
            System.out.println("");
            System.out.println("");
            System.out.println("");

            for(RouteStop routeStop : userRoute.getRouteStops()) {
                System.out.println(routeStop.getRouteStopId());
                System.out.println(routeStop.getRouteId());
                System.out.println(routeStop.getStopId());
                System.out.println(routeStop.getStopName());
                System.out.println(routeStop.getQtyToDeliver());
                System.out.println(routeStop.getQtyDelivered());
                System.out.println(routeStop.getDateDelivered());
                System.out.println(routeStop.getTotalDistance());
                System.out.println(routeStop.getValuePerMile());
                System.out.println(routeStop.getOrderNumber());
                System.out.println(routeStop.getIsComplete());
                System.out.println("");
            }
        }
    }
}
