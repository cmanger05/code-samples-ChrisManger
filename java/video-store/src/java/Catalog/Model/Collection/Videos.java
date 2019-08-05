/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalog.Model.Collection;

import Catalog.Model.Collection.VideoCollections.InventoryInStore;
import Catalog.Model.Video;
import Catalog.Model.VideoStockItemInStore;
import Core.Model.AbstractCollection;
import Core.Model.CollectionItemValidator;
import Core.Model.DBConnectionsUtil;
import Core.Model.PlaceholderModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

/**
 *
 * @author chris
 */
public class Videos extends AbstractCollection<Video> {
        

    @Override
    public void load() 
    {
        String whereClause = this._renderWhereClause();
        String queryLoadAllVideos = "SELECT * FROM video v "
                    + "INNER JOIN category c ON v.category_id = c.category_id "
                    + "WHERE c.category_id IS NOT NULL " + whereClause;
        
        InventoryInStore inventoryCollection = new InventoryInStore();
        inventoryCollection.load();
        PlaceholderModel placeholderModel = new PlaceholderModel();
        
        try ( //connect to DB
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryLoadAllVideos)) {

            Video videoItem;
            Integer videoId;
            Integer categoryId;
            String videoName;
            Integer quantityOwned;
            Integer quantityInStock;
            String categoryName;
            Double price;
            HashMap inventoryRecord;
            CollectionItemValidator itemValidator;
            VideoStockItemInStore inventoryItem;

            while(rs.next()) {
                videoItem = this.createNewItem();
                itemValidator = this.createNewValidator(videoItem);

                videoId   = rs.getInt("video_id");
                if(itemValidator.isIntegerNotNull(videoId, true)) {
                    videoItem.setVideoId(videoId);
                }

                categoryId = rs.getInt("category_id");
                if(itemValidator.isIntegerNotNull(categoryId, true)) {
                    videoItem.setCategoryId(categoryId);
                }

                videoName = rs.getString("video_name"); 
                if(itemValidator.isStringNotNull(videoName, true)) {
                    videoItem.setVideoName(videoName);
                }

                quantityOwned = rs.getInt("qty_owned");
                if(itemValidator.isIntegerNotNull(quantityOwned, true)) {
                    videoItem.setQtyOwned(quantityOwned);
                }

                categoryName = rs.getString("category_name");
                if(itemValidator.isStringNotNull(categoryName, true)) {
                    videoItem.setCategoryName(categoryName);
                }

                price = rs.getDouble("price");
                if(itemValidator.isDoubleNotNull(price, true)) {
                    videoItem.setPrice(price);
                }

                quantityInStock = 0;

                inventoryItem = (VideoStockItemInStore)inventoryCollection.getItemByKey(videoId);
                if(itemValidator.isIntegerNotNull(inventoryItem.getId(), false)) {
                    videoItem.setQuantityInStock(inventoryItem.getQtyInStore());
                } else {
                    videoItem.setQuantityInStock(videoItem.getQtyOwned());
                }

                this.addItem(videoId, videoItem);
            }
            rs.close();
            stmt.close();
            currentCon.close();
        }
        catch (Exception ex) 
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }    

        this.applyResultsFilters();
    }

    @Override
    public Video createNewItem() {
        return new Video();
    }
}
