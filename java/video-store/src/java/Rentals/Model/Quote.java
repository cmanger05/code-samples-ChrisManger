/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rentals.Model;

import Catalog.Model.Video;
import Users.Model.Customer;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author chris
 */
public class Quote {
    
    private Integer customer_id;
    
    private Double grand_total = 0.00;
    
    private final ArrayList<Video> quoteItems = new ArrayList<>();
    
    /**
     * Constructor
     * @param customer 
     */
    public Quote(Customer customer)
    {
        if(customer.isIntegerNotNull(customer.getId())) {
            this._setCustomerId(customer.getId());
        }
    }
    
    /**
     * Adds a quote item
     * @param quoteItemCandidate 
     * @param qty 
     */
    public void addQuoteItem(Video quoteItemCandidate, Integer qty)
    {
        quoteItemCandidate.setQtyRequested(qty);
        this.quoteItems.add(quoteItemCandidate);
    }
  
    /**
     * Returns all the quote items
     * @return 
     */
    public ArrayList<Video> getQuoteItems()
    {
        return this.quoteItems;
    }
    
    /**
     * Sets the customer id
     * @param customerId 
     */
    private void _setCustomerId(Integer customerId)
    {
        this.customer_id = customerId;
    }
    
    /**
     * Returns the customer id
     * @return 
     */
    private Integer _getCustomerId()
    {
        return this.customer_id;
    }
    
    /**
     * Increases the grand total
     * @param valueToIncreaseTotalBy 
     */
    private void _increaseGrandTotal(Double valueToIncreaseTotalBy)
    {
        this.grand_total += valueToIncreaseTotalBy;
    }
    
    /**
     * Returns the grand total
     * @return 
     */
    private Double _getGrandTotal()
    {
        return this.grand_total;
    }
    
    /**
     * Processes the rent
     * @param startDateText
     * @param endDateText
     * @return 
     */
    public Boolean processRentVideosRequest(String startDateText,String endDateText)
    {
        Date startDate = Date.valueOf(startDateText);
        Date endDate = Date.valueOf(endDateText);
        Order order = this.convertQuoteToOrder(startDate, endDate);
        Boolean isOrderSavedCorrectly = order.placeOrder(this._getGrandTotal());
        return isOrderSavedCorrectly;
    }
    
    /**
     * Converts the quote to an order.
     * @param startDate
     * @param endDate
     * @return 
     */
    public Order convertQuoteToOrder(Date startDate, Date endDate)
    {
        Order order = new Order(this._getCustomerId(), startDate, endDate);
        Rental rental = order.getRental();
        if(rental instanceof Rental && rental.isIntegerNotNull(rental.getId()) && this.getQuoteItems().size() > 0) {
            this.convertQuoteItemsToOrderItems(order);        
        }
        
        return order;
    }
    
    /**
     * Converts a quote item to an order item. Then, automatically adds it to the order.
     * @param order 
     */
    public void convertQuoteItemsToOrderItems(Order order)
    {
        Rental rental = order.getRental();
        Integer numberDays;
        RentalVideo orderItem;
        Integer qtyRequested;
        Integer videoId;
        Double price;
        Double totalCost;
        Integer rentalId = rental.getId();
        
        if(rental.isIntegerNotNull(rentalId)) { //Ensures the rental object is valid.
            numberDays = rental.getNumberOfDays();
            if(numberDays > 0) {
                for(Video video : this.getQuoteItems()) {
                    if(video.isIntegerNotNull(video.getVideoId())) {
                        orderItem = new RentalVideo();
                        videoId = video.getVideoId();
                        qtyRequested = video.getQtyRequested();
                        price = video.getPrice();
                        totalCost = qtyRequested * price * numberDays;
                        this._increaseGrandTotal(totalCost);

                        orderItem.setRentalId(rentalId);
                        orderItem.setVideoId(videoId);
                        orderItem.setQty(qtyRequested);
                        orderItem.setPrice(price);
                        orderItem.setTotalCost(totalCost);
                        order.addOrderItem(orderItem);
                    }
                }
            }
        }
    }
}
