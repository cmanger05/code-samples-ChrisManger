/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rentals.Model.Resource;

import Core.Model.AbstractModel;
import Core.Model.AbstractResourceSave;
import Core.Model.DBConnectionsUtil;
import Rentals.Model.Rental;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

/**
 *
 * @author chris
 */
public class RentalSave extends AbstractResourceSave
{
    /**
     * Saves a new record
     * @param model
     * @return 
     */
    @Override
    public boolean saveNew(AbstractModel model)
    {
        String queryString = "INSERT INTO rental(rental_id, customer_id, rental_date_start, rental_date_end, grand_total, is_active)"
                + "VALUES(?,?,?,?,?,?)";
        return this.saveStandard(model, queryString, true);
    }
    
    /**
     * Saves an existing record
     * @param model
     * @return 
     */
    @Override
    public boolean saveExisting(AbstractModel model)
    {
        String queryString = "UPDATE rental SET rental_id = ?, customer_id = ?, "
                                + "rental_date_start = ?, rental_date_end = ?, grand_total = ?, is_active = ? "
                                + "WHERE rental_id = ?";        

        return this.saveStandard(model, queryString, false);
    }
    
    /**
     * The standard save operation
     * @param model
     * @param queryString
     * @param isNew
     * @return 
     */
    public boolean saveStandard(AbstractModel model, String queryString, Boolean isNew)
    {
       boolean didSaveSuccessfully = false;
       
        Rental rental = (Rental)model;
        try {
            Connection connection = DBConnectionsUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(queryString);

            Integer rentalId = rental.getId();
            
            if(isNew) {
                rentalId = rental.getLastInsertedId() + 1;
                rental.setRentalId(rentalId);
            }
            
            Integer customerId = rental.getCustomerId();
            Date rentalDateStart = rental.getRentalDateStart();
            Date rentalDateEnd = rental.getRentalDateEnd();
            Double grandTotal = rental.getGrandTotal();
            Boolean isActive = rental.getIsActive();

            if(rental.isIntegerNotNull(rentalId) && rental.isIntegerNotNull(customerId) 
                    && rental.isDateNotEmpty(rentalDateStart) && rental.isDateNotEmpty(rentalDateEnd) && rental.isBooleanValid(isActive)) {
                statement.setInt(1, rentalId);
                statement.setInt(2, customerId);
                statement.setTimestamp(3, new java.sql.Timestamp(rentalDateStart.getTime()));
                statement.setTimestamp(4, new java.sql.Timestamp(rentalDateEnd.getTime()));
                statement.setDouble(5, grandTotal);
                statement.setBoolean(6, isActive);
                
                if(!isNew) {
                    statement.setInt(7, rentalId);
                }

                int executedUpdate = statement.executeUpdate();
                statement.close();
                connection.close();
                didSaveSuccessfully = true;
            }     
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return didSaveSuccessfully;
    }
   
}
