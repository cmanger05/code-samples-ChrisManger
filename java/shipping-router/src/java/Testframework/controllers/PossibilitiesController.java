/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testframework.controllers;

import Core.controller.AbstractController;
import RouteMatrixBuilder.Model.Generator;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class PossibilitiesController extends AbstractController 
{
         /**
     * Runs the program
     * @param args 
     */
    public static void main(String args[])
    { 
        PossibilitiesController currentController = new PossibilitiesController();
        currentController.displayAllPossibleCombinationsOfRoutes(5);
    } 
    
    /**
     * 
     * @param numberStops 
     */
    public void displayAllPossibleCombinationsOfRoutes(Integer numberStops)
    {
        this.println("Starting to Generate routes...");
        Generator routeGenerator = new Generator();
        Integer routeCounter = 0;
        String currentRoute = "";
        for(ArrayList<Integer> route : routeGenerator.getAllRoutes(numberStops)) {
            routeCounter++;
            for(Integer stop : route) {
                currentRoute += stop;
            }
            System.out.println(currentRoute);
            currentRoute = ""; //Resets
        }
        
        System.out.println("Total Number Routes: " + routeCounter);
    }
}
