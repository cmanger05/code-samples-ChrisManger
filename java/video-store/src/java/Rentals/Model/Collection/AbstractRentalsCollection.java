/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rentals.Model.Collection;

import Core.Model.AbstractCollection;
import Core.Model.CollectionItemValidator;
import Core.Model.DBConnectionsUtil;
import Rentals.Model.Rental;
import Rentals.Model.RentalVideo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;

/**
 *
 * @author chris
 */
public abstract class AbstractRentalsCollection extends AbstractCollection<Rental>  {

    public void load(String dateStart, String dateEnd)
    {
        this.addFilter("r.rental_date_start >= '" + dateStart + "'");
        this.addFilter("r.rental_date_end <= '" + dateEnd + "'");   
        this.load();
    }
    
    @Override
    public void load() {
        
        String whereClause = this._renderWhereClause();
        String queryLoadAllVideos = "SELECT r.rental_id, c.customer_id, c.first_name, c.last_name, r.rental_date_start, "
                + "r.rental_date_end, r.grand_total, r.is_active " +
            "FROM rental r " +
            "INNER JOIN customer c ON r.customer_id = c.customer_id " + whereClause;
        
        RentalVideos rentalVideosCollection;
           
        try ( //connect to DB
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryLoadAllVideos)) {

            Rental rentalItem;
            CollectionItemValidator itemValidator;
            Integer rentalId;
            Integer customerId;
            Date rentalDateStart;
            Date rentalDateEnd;
            Double grandTotal;
            Boolean isActive;
            String customerFirstName;
            String customerLastName;
            Integer numberOfDays;


            while(rs.next()) {

                rentalItem = (Rental)this.createNewItem();
                itemValidator = this.createNewValidator(rentalItem);
                
                rentalId   = rs.getInt("rental_id");
                if(itemValidator.isIntegerNotNull(rentalId, true)) {
                    rentalItem.setRentalId(rentalId);
                }
                
                customerId = rs.getInt("customer_id");
                if(itemValidator.isIntegerNotNull(customerId, true)) {
                    rentalItem.setCustomerId(customerId);
                }
            
                rentalDateStart = rs.getDate("rental_date_start");
                if(itemValidator.isDateNotEmpty(rentalDateStart, true)) {
                    rentalItem.setRentalDateStart(rentalDateStart);
                }
                
                rentalDateEnd = rs.getDate("rental_date_end");
                if(itemValidator.isDateNotEmpty(rentalDateEnd, true)) {
                    rentalItem.setRentalDateEnd(rentalDateEnd);
                }
                
                grandTotal = rs.getDouble("grand_total");
                if(itemValidator.isDoubleNotNull(grandTotal, true)) {
                    rentalItem.setGrandTotal(grandTotal);
                }
                
                isActive = rs.getBoolean("is_active");
                if(itemValidator.isBooleanValid(isActive, true)) {
                    rentalItem.setIsActive(isActive);
                }
                
                customerFirstName = rs.getString("first_name");
                if(itemValidator.isStringNotNull(customerFirstName, true)) {
                    rentalItem.setCustomerFirstName(customerFirstName);
                }
                
                customerLastName = rs.getString("last_name");
                if(itemValidator.isStringNotNull(customerLastName, true)) {
                    rentalItem.setCustomerLastName(customerLastName);
                }
                                
                numberOfDays = rentalItem.deriveNumberDays();
                if(itemValidator.isIntegerNotNull(numberOfDays, true)) {
                    rentalItem.setNumberOfDays(numberOfDays);
                }

                if(itemValidator.isValid()) {
                    rentalVideosCollection = new RentalVideos();
                    rentalVideosCollection.loadVideosForOneRentalOnly(rentalId);
                    
                    for(RentalVideo rentalVideo : rentalVideosCollection) {
                        rentalItem.addRentedVideo((RentalVideo)rentalVideo);
                    }           
                }
                
                if(itemValidator.isValid()) {
                    this.addItem(rentalId, rentalItem);
                }
            }
            rs.close();
            stmt.close();
            currentCon.close();
        }
        catch (Exception ex) 
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
    }
    
    /**
     * Creates a new blank item used in making a collection
     * @return 
     */
    @Override
    public Rental createNewItem() {
        return new Rental();
    }
}
