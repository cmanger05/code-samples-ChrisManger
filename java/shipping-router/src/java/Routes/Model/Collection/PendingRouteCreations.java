/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routes.Model.Collection;

import Core.Model.AbstractCollection;
import Core.Model.DBConnectionsUtil;
import Core.Model.CollectionItemValidator;
import Routes.Model.PendingRouteCreation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public class PendingRouteCreations extends AbstractCollection<PendingRouteCreation>
{
    /**
     * Loads the videos for one rental only 
     * @param pendingRouteId 
     */
    public void loadSingleInstance(Integer pendingRouteId)
    {
        this.addFilter("p.pending_route_id = " + pendingRouteId);
        this.load();
    }
    
    /**
     * Loads Rental Videos
     */
    @Override
    public void load() {

        String whereClause = this._renderWhereClause();
        String queryLoadAllPendingRoutesToBeCreated = "SELECT p.pending_route_id, "
                + "p.store_id, p.date_created, p.qty_requested, p.is_created " +
            "FROM pending_route_creation p " + whereClause;
        
        try ( //connect to DB
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryLoadAllPendingRoutesToBeCreated)) {

            PendingRouteCreation pendingRouteCreationItem;
            CollectionItemValidator itemValidator;
            Integer pendingRouteId;
            Integer storeId;
            Date dateCreated;
            Double qtyRequested;
            Integer isCreated;
            
            while(rs.next()) {
                pendingRouteCreationItem = (PendingRouteCreation)this.createNewItem();
                itemValidator = this.createNewValidator(pendingRouteCreationItem);

                pendingRouteId   = rs.getInt("pending_route_id");
                if(itemValidator.isIntegerNotNull(pendingRouteId, true)) {
                    pendingRouteCreationItem.setPendingRouteId(pendingRouteId);
                }
                
                storeId   = rs.getInt("store_id");
                if(itemValidator.isIntegerNotNull(storeId, true)) {
                    pendingRouteCreationItem.setStoreId(storeId);
                }
                                
                dateCreated = rs.getDate("date_created");
                if(itemValidator.isDateNotEmpty(dateCreated, false)) {
                    pendingRouteCreationItem.setDateCreated(dateCreated);
                }
                
                qtyRequested = rs.getDouble("qty_requested"); 
                if(itemValidator.isDoubleNotNull(qtyRequested, true)) {
                    pendingRouteCreationItem.setQtyRequested(qtyRequested);
                }
                
                isCreated = rs.getInt("is_created");
                if(itemValidator.isIntegerNotNull(isCreated, true)) {
                    pendingRouteCreationItem.setIsCreated(isCreated);
                }
                 
                if(itemValidator.isValid()) {
                    this.addItem(pendingRouteId, pendingRouteCreationItem);
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
     * Returns an instance of PendingRouteCreation
     * @return 
     */
    @Override
    public PendingRouteCreation createNewItem() 
    {    
        return PendingRouteCreation.createInstance();
    } 
}
