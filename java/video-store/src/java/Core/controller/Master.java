/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.controller;

import Catalog.Model.Video;
import Core.Model.AbstractModel;
import Rentals.Model.Quote;
import Rentals.controllers.RentalReport;
import Simulator.Model.Generate.CustomersNewGenerator;
import Simulator.Model.Generate.DateCustom;
import Simulator.Model.Generate.VideoGenerator;
import Simulator.controllers.Run;
import Users.Model.Customer;


/**
 *
 * @author chris
 */
public class Master extends AbstractController {
    
    /**
     * Runs the program
     * @param args 
     */
    public static void main(String args[])
    { 
        Run controllerRunSimulation = new Run();
        RentalReport controllerReportRentals = new RentalReport();
        
        String dateStartText = "2017-04-29";
        Integer numberDaysRunSimulation = 34;
        Integer numberIterationsSystemNeedsPerform = numberDaysRunSimulation - 1;
        DateCustom dateObject = new DateCustom(dateStartText);
        dateObject.addNumberDays(numberIterationsSystemNeedsPerform);
        String dateEndText = dateObject.getDateAsString();
 
        controllerRunSimulation.executeAction("2017-04-29", numberDaysRunSimulation); //this runs the simulation.   
        controllerReportRentals.execute(dateStartText, dateEndText);
        System.out.println("Program has finished executing");
    }
}
