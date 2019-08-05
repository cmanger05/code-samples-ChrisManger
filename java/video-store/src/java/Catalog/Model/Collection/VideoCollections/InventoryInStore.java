/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalog.Model.Collection.VideoCollections;

import Catalog.Model.VideoStockItemInStore;
import Core.Model.AbstractCollection;
import Core.Model.CollectionItemValidator;
import Core.Model.DBConnectionsUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public class InventoryInStore extends AbstractCollection<VideoStockItemInStore> {

    @Override
    public void load() {

        String whereClause = this._renderWhereClause();
        String queryLoadIssue = "select v.video_id, MAX(v.qty_owned) - SUM(rv.qty) AS qty_instore\n" +
            "FROM video v " +
            "INNER JOIN rental_video rv ON v.video_id = rv.video_id " +
            "INNER JOIN rental r ON rv.rental_id = r.rental_id " +
            "WHERE r.is_active = 1 " +
            "GROUP BY v.video_id";
        
            try ( //connect to DB
                Connection currentCon = DBConnectionsUtil.getConnection();
                Statement stmt = currentCon.createStatement();
                ResultSet rs = stmt.executeQuery(queryLoadIssue)) {

                VideoStockItemInStore videoStockItem;
                Integer videoId;
                Integer qtyInStore;
                CollectionItemValidator itemValidator;
            
                while(rs.next()) {
                    videoStockItem = (VideoStockItemInStore)this.createNewItem();
                    itemValidator = this.createNewValidator(videoStockItem);
                    
                    videoId   = rs.getInt("video_id");
                    if(itemValidator.isIntegerNotNull(videoId, true)) {
                        videoStockItem.setVideoId(videoId);
                    } 
                        
                    qtyInStore = rs.getInt("qty_instore");
                    if(itemValidator.isIntegerNotNull(qtyInStore, false)) {
                        videoStockItem.setQtyInStore(qtyInStore);
                    } 
                    
                    if(itemValidator.isValid()) {
                        this.addItem(videoId, videoStockItem);
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

    @Override
    public VideoStockItemInStore createNewItem() {
        return new VideoStockItemInStore();
    }
}
