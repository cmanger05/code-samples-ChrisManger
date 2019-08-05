/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rentals.Model.Resource;

import Core.Model.AbstractModel;
import Core.Model.DBConnectionsUtil;
import Core.Model.ResourceSaveInterface;
import Rentals.Model.RentalVideo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author chris
 */
public class RentalVideoSave implements ResourceSaveInterface
{
    /**
     * Saves a new record
     * @param model
     * @return 
     */
    @Override
    public boolean saveNew(AbstractModel model)
    {
        boolean didSaveSuccessfully = false;
       
        RentalVideo rentalVideo = (RentalVideo)model;
        try {
            String queryString = "INSERT INTO rental_video(rv_id, rental_id, video_id, qty, price, total_cost)"
                    + "VALUES(?,?,?,?,?,?)";
            Connection connection = DBConnectionsUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(queryString);

            Integer rvId = rentalVideo.getLastInsertedId() + 1;
            Integer rentalId = rentalVideo.getRentalId();
            Integer videoId = rentalVideo.getVideoId();
            Integer qty = rentalVideo.getQty();
            Double price = rentalVideo.getPrice();
            Double totalCost = rentalVideo.getTotalCost();

            if(rentalVideo.isIntegerNotNull(rvId) && rentalVideo.isIntegerNotNull(rentalId) && rentalVideo.isIntegerNotNull(videoId) 
                    && rentalVideo.isIntegerNotNull(qty) && rentalVideo.isDoubleNotNull(price) 
                    && rentalVideo.isDoubleNotNull(totalCost)) {
                statement.setInt(1, rvId);
                statement.setInt(2, rentalId);
                statement.setInt(3, videoId);
                statement.setInt(4, qty);
                statement.setDouble(5, price);
                statement.setDouble(6, totalCost);

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
    
    /**
     * Saves an existing record
     * @param model
     * @return 
     */
    @Override
    public boolean saveExisting(AbstractModel model)
    {
        //TODO: Functionality not required at this time.
        return true;
    }    
}
