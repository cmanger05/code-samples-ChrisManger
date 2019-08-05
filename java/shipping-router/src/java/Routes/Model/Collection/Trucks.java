/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routes.Model.Collection;

import Core.Model.AbstractCollection;
import Core.Model.CollectionItemValidator;
import Core.Model.DBConnectionsUtil;
import Routes.Model.Truck;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public class Trucks extends AbstractCollection<Truck> {
        /**
     * Loads the videos for one rental only 
     * @param truckId
     */
    public void loadSingleInstance(Integer truckId)
    {
        this.addFilter("t.truck_id = " + truckId);
        this.load();
    }
    
    /**
     * Loads Rental Videos
     */
    @Override
    public void load() {

        String whereClause = this._renderWhereClause();
        String queryLoadAllTrucks = "SELECT t.truck_id, t.truck_name, y.truck_type_name, "
                + "y.average_speed, y.holding_capacity, t.hub_id " +
            "FROM truck t INNER JOIN truck_type y ON t.type_id = y.truck_type_id " + whereClause;
        
        try ( //connect to DB
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryLoadAllTrucks)) {

            Truck truckItem;
            CollectionItemValidator itemValidator;
            Integer truckId;
            String truckName;
            String truckTypeName;
            Double averageSpeed;
            Double holdingCapacity;
            Integer hubId;
            
            while(rs.next()) {
                truckItem = (Truck)this.createNewItem();
                itemValidator = this.createNewValidator(truckItem);

                truckId   = rs.getInt("truck_id");
                if(itemValidator.isIntegerNotNull(truckId, true)) {
                    truckItem.setTruckId(truckId);
                }
                
                truckName   = rs.getString("truck_name");
                if(itemValidator.isStringNotNull(truckName, true)) {
                    truckItem.setTruckName(truckName);
                }
                                
                truckTypeName = rs.getString("truck_type_name");
                if(itemValidator.isStringNotNull(truckTypeName, true)) {
                    truckItem.setTruckTypeName(truckTypeName);
                }
                
                averageSpeed = rs.getDouble("average_speed"); 
                if(itemValidator.isDoubleNotNull(averageSpeed, true)) {
                    truckItem.setAvgSpeed(averageSpeed);
                }
                
                holdingCapacity = rs.getDouble("holding_capacity");
                if(itemValidator.isDoubleNotNull(holdingCapacity, true)) {
                    truckItem.setHoldingCapacity(holdingCapacity);
                }
                
                hubId = rs.getInt("hub_id");
                if(itemValidator.isIntegerNotNull(hubId, true)) {
                    truckItem.setHubId(hubId);
                }
                 
                if(itemValidator.isValid()) {
                    this.addItem(truckId, truckItem);
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
    public Truck createNewItem() 
    {    
        return Truck.createInstance();
    } 
}
