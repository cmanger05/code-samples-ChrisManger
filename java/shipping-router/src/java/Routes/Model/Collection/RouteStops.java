/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routes.Model.Collection;

import Core.Model.AbstractCollection;
import Core.Model.CollectionItemValidator;
import Core.Model.DBConnectionsUtil;
import Routes.Model.Route;
import Routes.Model.RouteStop;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public class RouteStops extends AbstractCollection<RouteStop> {
     
    /**
     * Loads the videos for one rental only 
     * @param routeStopId 
     */
    public void loadSingleInstance(Integer routeStopId)
    {
        this.addFilter("rs.route_stop_id = " + routeStopId);
        this.load();
    }
    
    /**
     * Loads all route stops associated with this route.
     * @param route 
     */
    public void loadByRoute(Route route)
    {
        Integer routeId = route.getRouteId();
        if(route.isIntegerNotNull(routeId)) {
            this.addFilter("rs.route_id = " + routeId);
            this.load();
        }
    }
    
    /**
     * Loads Rental Videos
     */
    @Override
    public void load() {

        String whereClause = this._renderWhereClause();
        String queryLoadAllRouteStops = "SELECT rs.route_stop_id, rs.route_id, rs.stop_id, "
                + "s.stop_name, rs.qty_to_deliver, rs.qty_delivered, rs.date_delivered, rs.total_distance,  "
                + "rs.value_per_mile, rs.is_complete, rs.stop_order_number " +
            "FROM route_stop rs INNER JOIN stop s ON rs.stop_id = s.stop_id " + whereClause
                + " ORDER BY rs.stop_order_number";
        
        try ( //connect to DB
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryLoadAllRouteStops)) {

            RouteStop routeStopItem;
            CollectionItemValidator itemValidator;
            Integer routeStopId;
            Integer routeId;
            Integer stopId;
            String stopName;
            Double qtyToDeliver;
            Double qtyDelivered;
            Date dateDelivered;
            Double totalDistance;
            Double valuePerMile;
            Integer stopOrderNumber;
            Integer isComplete;
            
            while(rs.next()) {
                routeStopItem = (RouteStop)this.createNewItem();
                itemValidator = this.createNewValidator(routeStopItem);

                routeStopId   = rs.getInt("route_stop_id");
                if(itemValidator.isIntegerNotNull(routeStopId, true)) {
                    routeStopItem.setRouteStopId(routeStopId);
                }
                
                routeId   = rs.getInt("route_id");
                if(itemValidator.isIntegerNotNull(routeId, true)) {
                    routeStopItem.setRouteId(routeId);
                }
                
                stopId   = rs.getInt("stop_id");
                if(itemValidator.isIntegerNotNull(stopId, true)) {
                    routeStopItem.setStopId(stopId);
                }
                
                stopName = rs.getString("stop_name"); 
                if(itemValidator.isStringNotNull(stopName, true)) {
                    routeStopItem.setStopName(stopName);
                }                
                
                qtyToDeliver = rs.getDouble("qty_to_deliver"); 
                if(itemValidator.isDoubleNotNull(qtyToDeliver, true)) {
                    routeStopItem.setQtyToDeliver(qtyToDeliver);
                }
                
                qtyDelivered = rs.getDouble("qty_delivered"); 
                if(itemValidator.isDoubleNotNull(qtyDelivered, false)) {
                    routeStopItem.setQtyDelivered(qtyDelivered);
                }
                
                dateDelivered = rs.getDate("date_delivered");
                if(itemValidator.isDateNotEmpty(dateDelivered, false)) {
                    routeStopItem.setDateDelivered(dateDelivered);
                }
                
                totalDistance = rs.getDouble("total_distance"); 
                if(itemValidator.isDoubleNotNull(totalDistance, true)) {
                    routeStopItem.setTotalDistance(totalDistance);
                }
                
                valuePerMile = rs.getDouble("value_per_mile"); 
                if(itemValidator.isDoubleNotNull(valuePerMile, true)) {
                    routeStopItem.setValuePerMile(valuePerMile);
                }                
                               
                stopOrderNumber = rs.getInt("stop_order_number");
                if(itemValidator.isIntegerNotNull(stopOrderNumber, true)) {
                    routeStopItem.setOrderNumber(stopOrderNumber);
                }
                                             
                isComplete = rs.getInt("is_complete");
                if(itemValidator.isIntegerNotNull(isComplete, true)) {
                    routeStopItem.setIsComplete(isComplete);
                }
                 
                if(itemValidator.isValid()) {
                    this.addItem(routeStopId, routeStopItem);
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
     * Returns an instance of object being added to collection
     * @return 
     */
    @Override
    public RouteStop createNewItem() 
    {    
        return RouteStop.createInstance();
    }     
}
