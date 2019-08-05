/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rentals.Model;

import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author chris
 */
public class Order {
    
    private final ArrayList<RentalVideo> _orderItems;

    private Rental _rental;
    
    /**
     * Constructor
     * @param customerId
     * @param dateStart
     * @param dateEnd
     */
    public Order(Integer customerId, Date dateStart, Date dateEnd) {
        this._orderItems = new ArrayList<>();
        this._createRental(customerId, dateStart, dateEnd);
    }
    
    /**
     * Sets the rental object
     * @param rental 
     */
    public void setRental(Rental rental)
    {
        this._rental = rental;
    }
    
    /**
     * Returns the rental object
     * @return 
     */
    public Rental getRental()
    {
        return this._rental;
    }
    
    /**
     * Adds an item to the order
     * @param rentalVideo 
     */
    public void addOrderItem(RentalVideo rentalVideo)
    {
        this._orderItems.add(rentalVideo);
    }
    
    /**
     * Returns the order items
     * @return 
     */
    public ArrayList<RentalVideo> getOrderItems()
    {
        return this._orderItems;
    }
    
    /**
     * Creates a new rental object
     * @param customerId
     * @param startDate
     * @param endDate
     * @return 
     */
    private Rental _createRental(Integer customerId, Date startDate, Date endDate)
    {
        Rental rental = new Rental();
        rental.setCustomerId(customerId);
        rental.setRentalDateStart(startDate);
        rental.setRentalDateEnd(endDate);
        Integer numberOfDays = rental.deriveNumberDays();
        rental.setNumberOfDays(numberOfDays);
        rental.setIsActive(true);
        rental.setGrandTotal(0.0);
        rental.save();
        this.setRental(rental);
        return rental;
    }
    
    /**
     * Places the order
     * @param grandTotal
     * @return 
     */
    public boolean placeOrder(Double grandTotal)
    {
        boolean didOrderSaveCorrectly = false;
        
        boolean didOrderItemSaveCorrectly;
        Rental rental = this.getRental();
        rental.setGrandTotal(grandTotal);
        boolean didRentalSaveCorrectly = rental.save();
        
        ArrayList<RentalVideo> orderItems = this.getOrderItems();
        Integer numberOrderItems = orderItems.size();
        
        if(didRentalSaveCorrectly && numberOrderItems > 0 && grandTotal > 0) {
           didOrderSaveCorrectly = true;
           for(RentalVideo orderItem : orderItems) {
               didOrderItemSaveCorrectly = orderItem.save();
               if(!didOrderItemSaveCorrectly) {
                   didOrderSaveCorrectly = false;
               }
           }  
        } 
        
        return didOrderSaveCorrectly;
    }
}
