/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rentals.Model;

import Core.Model.AbstractModel;
import Rentals.Model.Collection.RentalsAll;
import Rentals.Model.Resource.RentalSave;
import java.util.ArrayList;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author chris
 */
public class Rental extends AbstractModel
{
    private Integer rental_id;
    
    private Integer customer_id;
    
    private Date rental_date_start;
    
    private Date rental_date_end;
    
    private Double grand_total;
    
    private Boolean is_active;
    
    private String customer_first_name;
    
    private String customer_last_name;
    
    private Integer number_of_days;
    
    private final ArrayList<RentalVideo> list_rented_videos = new ArrayList<>();
    
    /**
     * Constructor
     */
    public Rental()
    {
        this._defaultFieldName = "rental_id";
        this._defaultTableName = "rental";
    }
    
    /**
     * Returns the video id
     * @return 
     */
    @Override
    public Integer getId()
    {
        return this.getRentalId();
    }

    /**
     * @return the rental_id
     */
    public Integer getRentalId() 
    {
        return rental_id;
    }

    /**
     * @return the customer_id
     */
    public Integer getCustomerId()
    {
        return customer_id;
    }

    /**
     * @return the rental_date_start
     */
    public Date getRentalDateStart()
    {
        return rental_date_start;
    }

    /**
     * @return the rental_date_end
     */
    public Date getRentalDateEnd()
    {
        return rental_date_end;
    }

    /**
     * @return the grand_total
     */
    public Double getGrandTotal()
    {
        return this.grand_total;
    }

    /**
     * @return the is_active
     */
    public Boolean getIsActive()
    {
        return is_active;
    }

    /**
     * @return the customer_first_name
     */
    public String getCustomerFirstName() 
    {
        return customer_first_name;
    }
    
    /**
     * @return the customer_last_name
     */
    public String getCustomerLastName()
    {
        return customer_last_name;
    }
    
    /**
     * @return the number_of_days
     */
    public Integer getNumberOfDays() 
    {
        return number_of_days;
    }

    /**
     * Returns the list of rented videos
     * @return 
     */
    public ArrayList<RentalVideo> getRentedVideos()
    {
        return this.list_rented_videos;
    }
    
    /**
     * @param rental_id the rental_id to set
     */
    public void setRentalId(Integer rental_id)
    {
        this.rental_id = rental_id;
    }

    /**
     * @param customer_id the customer_id to set
     */
    public void setCustomerId(Integer customer_id)
    {
        this.customer_id = customer_id;
    }

    /**
     * @param rental_date_start the rental_date_start to set
     */
    public void setRentalDateStart(Date rental_date_start)
    {
        this.rental_date_start = rental_date_start;
    }

    /**
     * @param rental_date_end the rental_date_end to set
     */
    public void setRentalDateEnd(Date rental_date_end)
    {
        this.rental_date_end = rental_date_end;
    }

    /**
     * @param grand_total the grand_total to set
     */
    public void setGrandTotal(Double grand_total)
    {
        this.grand_total = grand_total;
    }

    /**
     * @param is_active the is_active to set
     */
    public void setIsActive(Boolean is_active)
    {
        this.is_active = is_active;
    }
   
     /**
     * @param customer_first_name the customer_first_name to set
     */
    public void setCustomerFirstName(String customer_first_name) 
    {
        this.customer_first_name = customer_first_name;
    }
    
    /**
     * @param customer_last_name the customer_last_name to set
     */
    public void setCustomerLastName(String customer_last_name) 
    {
        this.customer_last_name = customer_last_name;
    }
    
    /**
     * @param number_of_days the number_of_days to set
     */
    public void setNumberOfDays(Integer number_of_days) 
    {
        this.number_of_days = number_of_days;
    }
   
    /**
     * Adds a rented video (this are ones rented in past)
     * @param rentedVideo 
     */
    public void addRentedVideo(RentalVideo rentedVideo)
    {
        this.list_rented_videos.add(rentedVideo);
    }
    
    /**
     * Returns the number of days of a rental
     * @return 
     */
    public Integer deriveNumberDays() {
        Integer numberOfDays = 0;

        Date rentalDateStart = this.getRentalDateStart();
        Date rentalDateEnd = this.getRentalDateEnd();
        Date defaultDate = new Date(0);

        if(rentalDateStart != defaultDate && rentalDateEnd != defaultDate) {
            long timeDiff = Math.abs(rentalDateEnd.getTime() - rentalDateStart.getTime());
            numberOfDays = (int) (long) TimeUnit.MILLISECONDS.toHours(timeDiff) / 24;
        }

        return numberOfDays;
    }
    
    /**
     * Saves the rental
     * @return 
     */
    @Override
    public boolean save()
    {
        RentalSave resourceSaveRental = new RentalSave();
        return resourceSaveRental.save(this);
    }
    
    /**
     * Loads by the id field
     * It's easier to accomplish this by using an existing collection, this is what is done below
     * @param idFieldValue
     */
    @Override
    public void loadById(Integer idFieldValue)
    {  
        RentalsAll rentals = new RentalsAll();
        rentals.addFilter("r.rental_id = " + idFieldValue);
        rentals.load();
        
        Rental rentalCandidate = rentals.next();
        if(this.isIntegerNotNull(rentalCandidate.getId())) {
            this.setRentalId(rentalCandidate.getId());
            this.setCustomerId(rentalCandidate.getCustomerId());
            this.setRentalDateStart(rentalCandidate.getRentalDateStart());
            this.setRentalDateEnd(rentalCandidate.getRentalDateEnd());
            this.setGrandTotal(rentalCandidate.getGrandTotal());
            this.setIsActive(rentalCandidate.getIsActive());
            this.setCustomerId(rentalCandidate.getCustomerId());
            this.setCustomerFirstName(rentalCandidate.getCustomerFirstName());
            this.setCustomerLastName(rentalCandidate.getCustomerLastName());
            Integer numberOfDays = this.deriveNumberDays();
            this.setNumberOfDays(numberOfDays);
        }    
    }
    
    /**
     * Returns the rental
     * @return 
     */
    public boolean returnRental()
    {
        boolean wasSuccessfullyReturned = true;
        if(this.is_active) {
            this.setIsActive(false);
            wasSuccessfullyReturned = this.save();
        }

        return wasSuccessfullyReturned;
    }
}
