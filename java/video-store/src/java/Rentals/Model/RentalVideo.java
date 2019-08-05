/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rentals.Model;

import Core.Model.AbstractModel;
import Rentals.Model.Resource.RentalVideoSave;

/**
 *
 * @author chris
 */
public class RentalVideo extends AbstractModel {

    private Integer rv_id;
    
    private Integer rental_id;
    
    private Integer video_id;
    
    private String video_name;
    
    private Integer qty;
    
    private Double price;
    
    private Double total_cost;
    
    /**
     * Constructor
     */
    public RentalVideo()
    {
        this._defaultFieldName = "rv_id";
        this._defaultTableName = "rental_video";
    }
    
    @Override
    public Integer getId() {
        return this.getRvId();
    }

    /**
     * @return the rv_id
     */
    public Integer getRvId() 
    {
        return rv_id;
    }

    /**
     * @return the rental_id
     */
    public Integer getRentalId() 
    {
        return rental_id;
    }

    /**
     * @return the video_id
     */
    public Integer getVideoId() 
    {
        return video_id;
    }

    /**
     * @return the qty
     */
    public Integer getQty() 
    {
        return qty;
    }

    /**
     * @return the price
     */
    public Double getPrice() 
    {
        return price;
    }

    /**
     * @return the total_cost
     */
    public Double getTotalCost() 
    {
        return total_cost;
    }

    /**
     * Returns the video name
     * @return 
     */
    public String getVideoName()
    {
        return this.video_name;
    }
    
    /**
     * @param rv_id the rv_id to set
     */
    public void setRvId(Integer rv_id) 
    {
        this.rv_id = rv_id;
    }

    /**
     * @param rental_id the rental_id to set
     */
    public void setRentalId(Integer rental_id) 
    {
        this.rental_id = rental_id;
    }

    /**
     * @param video_id the video_id to set
     */
    public void setVideoId(Integer video_id) 
    {
        this.video_id = video_id;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price)
    {
        this.price = price;
    }

    /**
     * @param total_cost the total_cost to set
     */
    public void setTotalCost(Double total_cost)
    {
        this.total_cost = total_cost;
    } 
    
    /**
     * Sets the video name
     * @param videoName 
     */
    public void setVideoName(String videoName)
    {
        this.video_name = videoName;
    }
    
        /**
     * Saves the rental
     * @return 
     */
    @Override
    public boolean save()
    {
        boolean didRecordSaveCorrectly = false;
        RentalVideoSave resourceSaveRentalVideo = new RentalVideoSave();
        
        if(!this.doesRecordAlreadyExist()) { 
            didRecordSaveCorrectly = resourceSaveRentalVideo.saveNew(this);
        }

        return didRecordSaveCorrectly;
    }
}
