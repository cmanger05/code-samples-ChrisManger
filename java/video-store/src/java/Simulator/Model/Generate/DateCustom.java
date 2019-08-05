/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Model.Generate;

import Users.Model.Customer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author chris
 */
public final class DateCustom 
{
    public static final String STANDARD_DATE_FORMAT = "yyyy-MM-dd";
    
    private Date _dateCustom = new Date(0);
    
    private DateCustom() {}
    
    /**
     * Constructor
     * @param requestedDate 
     */
    public DateCustom(String requestedDate)
    {
        this.convertStringToDateStandard(requestedDate);
    }
    
    /**
     * Sets the date variable
     * @param dateCustom 
     */
    public void setDateCustom(Date dateCustom)
    {
        this._dateCustom = dateCustom;
    }
    
    /**
     * Returns the custom date
     * @return 
     */
    public Date getDateCustom()
    {
        return this._dateCustom;
    }
    
    /**
     * Converts a string to a date object
     * @param startDateText
     * @return 
     */
    public Date convertStringToDateStandard(String startDateText)
    {
        Date convertedDate = this.convertStringToDate(startDateText, DateCustom.STANDARD_DATE_FORMAT);
        return convertedDate;
    }
    
    /**
     * Converts a string to a date
     * @param dateText
     * @param startDateFormat
     * @return 
     */
    public Date convertStringToDate(String dateText, String startDateFormat)
    {
        Date convertedDate = new Date();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(startDateFormat);
            convertedDate = dateFormat.parse(dateText);
            this.setDateCustom(convertedDate);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return convertedDate;
    }   
    
    /**
     * Determines the due date of a new rental
     * @param customer
     * @return 
     */
    public String deriveNewRentalDueDate(Customer customer)
    {
        String dateAsString = "";
        Integer numberNights = customer.getVideoNumberNights();
        if(customer.isIntegerNotNull(customer.getCustomerId()) && customer.isIntegerNotNull(numberNights)) {    
            Date currentDate = this.getDateCustom();
            long currentTimesteampMillis = currentDate.getTime();
            long updatedTimeStampMillis = currentTimesteampMillis + (86400000 * numberNights);
            Date randomFutureDate = new Date(updatedTimeStampMillis);
            dateAsString = new SimpleDateFormat(DateCustom.STANDARD_DATE_FORMAT).format(randomFutureDate);
        }
        
        return dateAsString;
    }
    
    /**
     * Increase date objects value by specified number of days
     * @param numberDaysAddToOriginalDate 
     */
    public void addNumberDays(Integer numberDaysAddToOriginalDate)
    {
        for(int i = 1; i <= numberDaysAddToOriginalDate; i++) {
            this.setToNextDay();
        }
    }
    
    /**
     * Returns the date as a string
     * @return 
     */
    public String getDateAsString()
    {
        Date currentDate = this.getDateCustom();
        return new SimpleDateFormat(DateCustom.STANDARD_DATE_FORMAT).format(currentDate);
    }
    
    /**
     * Gets the date for the next day
     */
    public void setToNextDay()
    {
        Date dateCustomized = this.getDateCustom();
        long currentTimesteampMillis = dateCustomized.getTime();
        long updatedTimeStampMillis = currentTimesteampMillis + 86400000;
        dateCustomized.setTime(updatedTimeStampMillis);
        this.setDateCustom(dateCustomized);
    }
}
