/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rentals.Model.Collection;

import Core.Model.AbstractCollection;
import Core.Model.AbstractModel;
import Core.Model.DBConnectionsUtil;
import Core.Model.CollectionItemValidator;
import Rentals.Model.RentalVideo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public class RentalVideos extends AbstractCollection<RentalVideo> 
{    
    /**
     * Loads the videos for one rental only 
     * @param rentalId 
     */
    public void loadVideosForOneRentalOnly(Integer rentalId)
    {
        this.addFilter("r.rental_id = " + rentalId);
        this.load();
    }
    
    /**
     * Loads Rental Videos
     */
    @Override
    public void load() {

        String whereClause = this._renderWhereClause();
        String queryLoadAllVideos = "SELECT r.rental_id, rv.rv_id, v.video_id, v.video_name, rv.qty, rv.price, rv.total_cost " +
            "FROM rental r " +
            "INNER JOIN rental_video rv ON r.rental_id = rv.rental_id " +
            "INNER JOIN video v ON rv.video_id = v.video_id " +
            "INNER JOIN customer c ON r.customer_id = c.customer_id " + whereClause;
        
        
        try ( //connect to DB
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryLoadAllVideos)) {

            RentalVideo rentalVideoItem;
            CollectionItemValidator itemValidator;
            Integer rvId;
            Integer rentalId;
            Integer videoId;
            String videoName;
            Integer qty;
            Double price;
            Double totalCost;
            
            while(rs.next()) {
                rentalVideoItem = (RentalVideo)this.createNewItem();
                itemValidator = this.createNewValidator(rentalVideoItem);

                rvId   = rs.getInt("rv_id");
                if(itemValidator.isIntegerNotNull(rvId, true)) {
                    rentalVideoItem.setRvId(rvId);
                }
                
                rentalId   = rs.getInt("rental_id");
                if(itemValidator.isIntegerNotNull(rentalId, true)) {
                    rentalVideoItem.setRentalId(rentalId);
                }
                                
                videoName = rs.getString("video_name");
                if(itemValidator.isStringNotNull(videoName, true)) {
                    rentalVideoItem.setVideoName(videoName);
                }
                
                qty = rs.getInt("qty"); 
                if(itemValidator.isIntegerNotNull(qty, true)) {
                    rentalVideoItem.setQty(qty);
                }
                
                price = rs.getDouble("price");
                if(itemValidator.isDoubleNotNull(price, true)) {
                    rentalVideoItem.setPrice(price);
                }
                
                totalCost = rs.getDouble("total_cost");
                if(itemValidator.isDoubleNotNull(totalCost, true)) {
                    rentalVideoItem.setTotalCost(totalCost);
                }
                    
                if(itemValidator.isValid()) {
                    this.addItem(rvId, rentalVideoItem);
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
     * Returns new item
     * @return 
     */
    @Override
    public RentalVideo createNewItem() {
        return new RentalVideo();
    }
    
}
