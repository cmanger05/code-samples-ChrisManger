/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.controllers;

import Catalog.Model.Statistic.TotalInStore;
import Catalog.Model.Video;
import Core.controller.AbstractController;
import Rentals.Model.Quote;
import Simulator.Model.Command.CustomerReturnRental;
import Simulator.Model.Generate.CustomersNewGenerator;
import Simulator.Model.Generate.CustomersReturningGenerator;
import Simulator.Model.Generate.DateCustom;
import Simulator.Model.Generate.VideoGenerator;
import Users.Model.Customer;

/**
 *
 * @author chris
 */
public class Run extends AbstractController {
    
    private static final String ERROR_MESSAGE_RETURN_FAILED = " (Error: Return Failed!)";
    
    private static final String ERROR_MESSAGE_ORDER_FAILED = " Error: The order was not placed";
    
    private static final String ERROR_MESSAGE_NO_VIDEOS = " Notice: The quote has no videos associated with it.";
    
    private static final String WARNING_NO_ORDERS_PLACED_TODAY = "There were no orders placed today.";
    
    private final CustomersNewGenerator _customerNewRegenerator;
    
    /**
     * Constructor
     */
    public Run()
    {
        this._customerNewRegenerator = new CustomersNewGenerator();
    }
   
    /**
     * Regenerates the customers
     * @return 
     */
    private CustomersNewGenerator _getCustomerNewRegenerator()
    {
        return this._customerNewRegenerator;
    }
    
    /**
     * Runs the simulation
     * @param startDateText
     * @param numberOfDays
     */
    public void executeAction(String startDateText, Integer numberOfDays)
    {
        DateCustom dateGeneratorObject = new DateCustom(startDateText);
        for(int i = 1; i <= numberOfDays; i++) {
            this._simulateDayNewReturns(dateGeneratorObject);
            this._simulateDayNewRentals(dateGeneratorObject);
            dateGeneratorObject.setToNextDay();
        }
    }
    
    /**
     * Simulates returns and new rentals for an entire day
     * @param dateGeneratorObject 
     */
    private void _simulateDayNewRentals(DateCustom dateGeneratorObject)
    {
        String dateAsString = dateGeneratorObject.getDateAsString();
        CustomersNewGenerator customersGenerator = this._getCustomerNewRegenerator();
        Integer numberCustomersComingToday = customersGenerator.getRandomNumberCustomersToGenerate();
        Integer numberOrdersPlacedToday = 0;
        Customer customerGenerated;
        VideoGenerator videoGenerator;
        TotalInStore numberVideosInStoreInquirer;
        Integer numberVideosInStore;
        Quote customerQuote;
        Boolean wasOrderProcessedSuccessfully;
        
        this.printDebugText("");
        this.printDebugText("Beginning of Day: " + dateAsString);
        for(int i = 1; i <= numberCustomersComingToday; i++) {
            numberVideosInStoreInquirer = new TotalInStore();
            numberVideosInStore = numberVideosInStoreInquirer.getValue();
            
            if(numberVideosInStore > 0) {
                customerGenerated = customersGenerator.next();
                this.printDebugText("Quote Customer Name: " + customerGenerated.getFirstName() + " " + customerGenerated.getLastName());
                videoGenerator = new VideoGenerator(customerGenerated);
                videoGenerator.load();
                customerQuote = customerGenerated.getQuote();
                
                if(videoGenerator.getSize() > 0) {
                    for(Video video: videoGenerator) {
                        customerQuote.addQuoteItem(video, 1);
                        this.printDebugText("      " + video.getVideoName());
                    }

                    DateCustom newRentalDateStart = new DateCustom(dateAsString);
                    DateCustom newRentalDateEnd = new DateCustom(dateAsString);
                    newRentalDateEnd.addNumberDays(customerGenerated.getVideoNumberNights());
                    wasOrderProcessedSuccessfully = customerQuote.processRentVideosRequest(newRentalDateStart.getDateAsString(), newRentalDateEnd.getDateAsString());
                    if(wasOrderProcessedSuccessfully) {
                        numberOrdersPlacedToday++;
                    } else {
                        this.printDebugText(Run.ERROR_MESSAGE_ORDER_FAILED);
                    }
                } else {
                    this.printDebugText(Run.ERROR_MESSAGE_NO_VIDEOS);
                }
            }        
        }
        
        if(numberOrdersPlacedToday <= 0) {
            this.printDebugText(Run.WARNING_NO_ORDERS_PLACED_TODAY);
        }
    }
    
    /**
     * Simulates returns to be performed for the current date
     */
    private void _simulateDayNewReturns(DateCustom dateGeneratorObject)
    {
        String dateAsString = dateGeneratorObject.getDateAsString();
        CustomersReturningGenerator customersReturningGenerator = new CustomersReturningGenerator(dateAsString);
        customersReturningGenerator.load();
        CustomerReturnRental customerReturningRental;
        Boolean rentalSuccessfullyReturned;
        String errorText;

        for(CustomerReturnRental customerReturningRaw : customersReturningGenerator) {
            customerReturningRental = (CustomerReturnRental)customerReturningRaw;
            rentalSuccessfullyReturned = customerReturningRental.execute();
            if(!rentalSuccessfullyReturned) {
                errorText = Run.ERROR_MESSAGE_RETURN_FAILED;
            } else {
                errorText = "";
            }

            this.printDebugText("The following customer returned their videos for rental#" + customerReturningRental.getId() + " : " +
                    customerReturningRental.getFirstName() + " " + customerReturningRental.getLastName() + " " + errorText);        
        }
    }
}
