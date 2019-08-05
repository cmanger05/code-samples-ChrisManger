/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rentals.controllers;

import Catalog.Model.Collection.Videos;
import Catalog.Model.Statistic.TotalInStore;
import Catalog.Model.Video;
import Core.controller.AbstractController;
import Rentals.Model.Collection.AbstractRentalsCollection;
import Rentals.Model.Collection.RentalsActive;
import Rentals.Model.Collection.RentalsCompleted;
import Rentals.Model.Rental;
import Rentals.Model.RentalVideo;
import Rentals.Statistic.TotalRevenueGenerated;
import Simulator.Model.Generate.DateCustom;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class RentalReport extends AbstractController {
    
    /**
     * Prints all necessary reports
     * @param dateStartText
     * @param dateEndText 
     */
    public void execute(String dateStartText, String dateEndText)
    {
        this.println("Video Store Reports between " + dateStartText + " AND " + dateEndText);
        this._outputTotalRevenue(dateStartText, dateEndText);
        this._outputInventoryReport();
        
        RentalsCompleted rentalsCompleted = new RentalsCompleted();
        rentalsCompleted.load(dateStartText, dateEndText);
        this.println("List of Completed Orders between " + dateStartText + " AND " + dateEndText);
        this.printBlankLine();
        this._outputRentals(rentalsCompleted);
        
        RentalsActive rentalsActive = new RentalsActive();
        /* If the database stored the date an item was created this variable would not need to be calculated */
        DateCustom finalPossibleEndDate = new DateCustom(dateEndText);
        finalPossibleEndDate.addNumberDays(7); //longest possible rental is 7 days, added 8 to be safe
        rentalsActive.load(dateStartText, finalPossibleEndDate.getDateAsString());
        this.println("List of Active Orders between " + dateStartText + " AND " + dateEndText);
        this.printBlankLine();
        this._outputRentals(rentalsActive);
    }
    
    /**
     * OUtputs the total revenue
     */
    private void _outputTotalRevenue(String dateStartText, String dateEndText)
    {
        TotalRevenueGenerated totalRevenueInquirer = new TotalRevenueGenerated(dateStartText,dateEndText);
        Double totalRevenueGenerated = totalRevenueInquirer.getValue();
        this.println("Total Revenue Generated over this time span: " + totalRevenueGenerated);
    }
    
    private void _outputInventoryReport()
    {
        TotalInStore totalVideoInquirer = new TotalInStore();
        Integer totalNumberVideosInStore = totalVideoInquirer.getValue();
        this.println("Total Number of Videos in the Store: " + totalNumberVideosInStore);
        this.println("List of Videos in the Store: ");

        Videos videosCollection = new Videos();
        videosCollection.load();
        for(Video video : videosCollection) {
            this.println(video.getVideoName() + " / qty currently in store: " + video.getQuantityInStock());     
        }
        
        this.printBlankLine();
    }
    
    /**
     * Outputs the rentals
     * @param rentalsCollection 
     */
    private void _outputRentals(AbstractRentalsCollection rentalsCollection)
    {
        ArrayList<RentalVideo> rentedVideosCollection;
        
        for(Rental rental : rentalsCollection) {
            rentedVideosCollection = rental.getRentedVideos();

            if(rentedVideosCollection.size() > 0) {
                this.println("Rental ID: " + rental.getId());
                this.println("Name: " + rental.getCustomerFirstName() + " " + rental.getCustomerLastName());
                this.println("Date Start: " + rental.getRentalDateStart());
                this.println("Date Start: " + rental.getRentalDateEnd());
                this.println("Grand Total: " + rental.getGrandTotal());
                this.println("Number of Days: " + rental.getNumberOfDays());
                this.println("List of Movies Rented: ");
                for(RentalVideo rentalVideo : rentedVideosCollection) {
                    this.println("     " + rentalVideo.getVideoName() + " (qty: " + rentalVideo.getQty() + ")");
                }
                this.printBlankLine();
            }         
        }
        
        this.printBlankLine(); this.printBlankLine();
    }
}
