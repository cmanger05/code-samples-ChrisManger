/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testframework.controllers;

import Core.controller.AbstractController;
import Routes.Model.Route;
import Routes.Model.RouteStop;
import java.sql.Date;

/**
 *
 * @author chris
 */
public class ModelSaveTesting extends AbstractController 
{
     /**
     * Runs the program
     * @param args 
     */
    public static void main(String args[])
    { 
        ModelSaveTesting currentController = new ModelSaveTesting();
        currentController.testSavingNewRoute();
    } 
    
    public void testSavingNewRoute()
    {
        this.println("Testing to ensure NEW route saves properly.");
        Route route = Route.createInstance();
        route.setName("Route 44");
        route.setHubId(7);
        route.setTruckId(2);
        route.setDateStarted(Date.valueOf("2017-03-20"));
        route.setDateDelivered(Date.valueOf("2017-05-20"));
        route.setTotalDistance(4500.0);
        route.setAverageMilesPerLeg(450.0);
        route.setValuePerMile(452.0);
        route.setIsComplete(0);
        route.save();
    }
    
    public void testUpdatingExistingRoute()
    {
        Route route = Route.createInstance();
        route.loadById(3);
        route.setName("Route 3333");
        route.setHubId(7);
        route.setTruckId(2);
        route.setDateStarted(Date.valueOf("2017-01-23"));
        route.setDateDelivered(Date.valueOf("2017-02-22"));
        route.setTotalDistance(3300.0);
        route.setAverageMilesPerLeg(330.0);
        route.setValuePerMile(332.0);
        route.setIsComplete(1);
        route.save();
    }
    
    /**
     * Test saving a new route stop
     */
    public void testSavingNewRouteStop()
    {
        RouteStop routeStop = RouteStop.createInstance();
        routeStop.setRouteId(3);
        routeStop.setStopId(2);
        routeStop.setQtyToDeliver(1500.0);
        routeStop.setQtyDelivered(1499.0);
        routeStop.setDateDelivered(Date.valueOf("2017-01-28"));
        routeStop.setTotalDistance(4500.0);
        routeStop.setValuePerMile(45.0);
        routeStop.setOrderNumber(2);
        routeStop.setIsComplete(1);
        routeStop.save();
    }
    
    /**
     * Test update of an existing route stop
     */
    public void testUpdatingExistingRouteStop()
    {
        RouteStop routeStop = RouteStop.createInstance();
        routeStop.loadById(8);
        routeStop.setRouteId(2);
        routeStop.setStopId(4);
        routeStop.setQtyToDeliver(1700.0);
        routeStop.setQtyDelivered(1799.0);
        routeStop.setDateDelivered(Date.valueOf("2017-01-27"));
        routeStop.setTotalDistance(4700.0);
        routeStop.setValuePerMile(47.0);
        routeStop.setOrderNumber(2);
        routeStop.setIsComplete(1);
        routeStop.save();        
    }
    
    public void testSaveAssociatedRouteStops()
    {
        System.out.println("Starting Test");
        Route route = Route.createInstance();
        route.loadById(1);
        route.setName("Test Save Assoc 1");
        
        RouteStop routeStop3 = route.getRouteStops().getItemByKey(3);
        routeStop3.setQtyDelivered(79.0);
        routeStop3.setQtyToDeliver(79.0);
        
        RouteStop routeStop5 = route.getRouteStops().getItemByKey(5);
        routeStop5.setQtyDelivered(79.0);
        routeStop5.setQtyToDeliver(79.0);
      
        RouteStop routeStop = RouteStop.createInstance();
        routeStop.setRouteId(1);
        routeStop.setStopId(2);
        routeStop.setQtyToDeliver(1800.0);
        routeStop.setQtyDelivered(1899.0);
        routeStop.setDateDelivered(Date.valueOf("2017-02-28"));
        routeStop.setTotalDistance(4800.0);
        routeStop.setValuePerMile(48.0);
        routeStop.setOrderNumber(8);
        routeStop.setIsComplete(1);
        route.getRouteStops().addItem(13, routeStop);
        
        route.save();
    }
}
