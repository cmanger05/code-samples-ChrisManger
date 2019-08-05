/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalog.Model;

import Core.Model.AbstractModel;

/**
 *
 * @author chris
 */
public class VideoStockItemInStore extends AbstractModel {
    
    private Integer video_id;
    
    private Integer qty_in_store;
    
    @Override
    public Integer getId() 
    {
        return this.getVideoId();
    }

    /**
     * @return the video_id
     */
    public Integer getVideoId() 
    {
        return video_id;
    }

    /**
     * @return the qty_in_store
     */
    public Integer getQtyInStore() 
    {
        return qty_in_store;
    }

    /**
     * @param video_id the video_id to set
     */
    public void setVideoId(Integer video_id) 
    {
        this.video_id = video_id;
    }

    /**
     * @param qty_in_store the qty_in_store to set
     */
    public void setQtyInStore(Integer qty_in_store) 
    {
        this.qty_in_store = qty_in_store;
    }    
}
