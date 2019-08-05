/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalog.Model;

import Core.Model.AbstractModel;
import Core.Model.DBConnectionsUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public class Video extends AbstractModel {
    
    private Integer video_id;
    
    private Integer category_id;
    
    private String video_name;
    
    private Integer qty_owned;
    
    private Integer qty_in_stock;
    
    private String category_name;
    
    private Double price;
    
    private Integer qty_requested;
    
    /**
     * Constructor
     */
    public Video() {
        this._defaultFieldName = "video_id";
        this._defaultTableName = "video";
    }
    
    /**
     * Returns the video id
     * @return 
     */
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
     * @return the category_id
     */
    public Integer getCategoryId()
    {
        return category_id;
    }

    /**
     * @return the video_name
     */
    public String getVideoName()
    {
        return video_name;
    }

    /**
     * @return the quantity_owned
     */
    public Integer getQtyOwned()
    {
        return qty_owned;
    }

    /**
     * @return the category_name
     */
    public String getCategoryName()
    {
        return category_name;
    }
    
    /**
     * @return the price
     */
    public Double getPrice()
    {
        return price;
    }
    
    /**
     * Returns the quantity requested
     * @return 
     */
    public Integer getQtyRequested()
    {
        return this.qty_requested;
    }
    
    /**
     * Returns the quantity in stock
     * @return 
     */
    public Integer getQuantityInStock()
    {
        return this.qty_in_stock;
    }
    
    /**
     * @param video_id the video_id to set
     */
    public void setVideoId(Integer video_id)
    {
        this.video_id = video_id;
    }

    /**
     * @param category_id the category_id to set
     */
    public void setCategoryId(Integer category_id)
    {
        this.category_id = category_id;
    }

    /**
     * @param video_name the video_name to set
     */
    public void setVideoName(String video_name)
    {
        this.video_name = video_name;
    }

    /**
     * @param quantity_owned the quantity_owned to set
     */
    public void setQtyOwned(Integer quantity_owned)
    {
        this.qty_owned = quantity_owned;
    }
  
    /**
     * @param category_name the category_name to set
     */
    public void setCategoryName(String category_name)
    {
        this.category_name = category_name;
    }
    
    /**
     * @param price the price to set
     */
    public void setPrice(Double price)
    {
        this.price = price;
    }
    
    /**
     * Sets the quantity in stock
     * @param qtyInStock 
     */
    public void setQuantityInStock(Integer qtyInStock)
    {
        this.qty_in_stock = qtyInStock;
    }
    
    /**
     * Sets the quantity requested
     * @param qtyRequested 
     */
    public void setQtyRequested(Integer qtyRequested)
    {
        this.qty_requested = qtyRequested;
    }
    
    /**
     * Loads by the id field
     * The reason why I'm creating a simple function instead of an abstract function
     * is because there are some models where no data every needs to be loaded.
     * @param idFieldValue
     */
    @Override
    public void loadById(Integer idFieldValue)
    {   
        try 
        {
            String queryLoadIssue = "SELECT * FROM video v "
                    + "INNER JOIN category c ON v.category_id = c.category_id "
                    + "WHERE v.video_id = " + idFieldValue + " AND c.category_id IS NOT NULL";
            
            //connect to DB 
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryLoadIssue);	        
            boolean more = rs.next();
	    if(more) { 
                Integer videoId = rs.getInt("video_id");
                if(this.isIntegerNotNull(videoId)) {
                    this.setVideoId(videoId);
                }
                
                Integer categoryId = rs.getInt("category_id");
                if(this.isIntegerNotNull(categoryId)) {
                    this.setCategoryId(categoryId);
                }              
                
                String videoName = rs.getString("video_name");
                if(this.isStringNotNull(videoName)) {
                    this.setVideoName(videoName);
                }
                
                Integer qtyOwned = rs.getInt("qty_owned");
                if(this.isIntegerNotNull(qtyOwned)) {
                    this.setQtyOwned(qtyOwned);
                }
                
                String categoryName = rs.getString("category_name");
                if(this.isStringNotNull(categoryName)) {
                    this.setCategoryName(categoryName);
                }
                
                Double price = rs.getDouble("price");
                if(this.isDoubleNotNull(price)) {
                    this.setPrice(price);
                }
                
                this.setIsLoaded(true);
                currentCon.close();
                stmt.close();
                rs.close();
            }
        } 
        catch (Exception ex) 
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
    }
}
