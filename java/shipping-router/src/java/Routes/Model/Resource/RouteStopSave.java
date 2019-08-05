/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routes.Model.Resource;

import Core.Model.AbstractResourceSave;
import Core.Model.AbstractModel;
import Core.Model.DBConnectionsUtil;
import Routes.Model.RouteStop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
/**
 *
 * @author chris
 */
public class RouteStopSave extends AbstractResourceSave 
{
    /**
     * Saves a new record
     * @param model
     * @return 
     */
    @Override
    public boolean saveNew(AbstractModel model)
    {
        String queryString = "INSERT INTO route_stop(route_stop_id, route_id, stop_id, "
                + "qty_to_deliver, qty_delivered, date_delivered, total_distance, "
                + "value_per_mile, stop_order_number, is_complete)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        return this.saveStandard(model, queryString, true);
    }
    
    /**
     * Saves an existing record
     * @param model
     * @return 
     */
    @Override
    public boolean saveExisting(AbstractModel model)
    {
        String queryString = "UPDATE route_stop SET route_stop_id = ?, route_id = ?, "
                                + "stop_id = ?, qty_to_deliver = ?, qty_delivered = ?, date_delivered = ?, "
                                + "total_distance = ?, value_per_mile = ?, stop_order_number = ?, is_complete = ? "
                                + "WHERE route_stop_id = ?";        

        return this.saveStandard(model, queryString, false);
    }
    
    /**
     * The standard save operation
     * @param model
     * @param queryString
     * @param isNew
     * @return 
     */
    public boolean saveStandard(AbstractModel model, String queryString, Boolean isNew)
    {
       boolean didSaveSuccessfully = false;
       
        RouteStop routeStop = (RouteStop)model;
        try {
            Connection connection = DBConnectionsUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(queryString);

            Integer routeStopId = routeStop.getId();
            
            if(isNew) {
                routeStopId = routeStop.getLastInsertedId() + 1;
                routeStop.setRouteStopId(routeStopId);
            }
            
            Integer routeId = routeStop.getRouteId();
            Integer stopId = routeStop.getStopId();
            Double qtyToDeliver = routeStop.getQtyToDeliver();
            Double qtyDelivered = routeStop.getQtyDelivered();
            Date dateDelivered = routeStop.getDateDelivered();
            Double totalDistance = routeStop.getTotalDistance();
            Double valuePerMile = routeStop.getValuePerMile();
            Integer stopOrderNumber = routeStop.getOrderNumber();
            Integer isComplete = routeStop.getIsComplete();
            
            if(routeStop.isIntegerNotNull(routeStopId) && routeStop.isIntegerNotNull(routeId) && routeStop.isIntegerNotNull(stopId) 
                    && routeStop.isDoubleNotNull(qtyToDeliver) && routeStop.isDoubleNotNull(qtyDelivered) 
                    && routeStop.isDoubleNotNull(totalDistance) && routeStop.isDoubleNotNull(valuePerMile) 
                    && routeStop.isIntegerNotNull(stopOrderNumber) && routeStop.isIntegerNotNull(isComplete)) {
                statement.setInt(1, routeStopId);
                statement.setInt(2, routeId);
                statement.setInt(3, stopId);
                statement.setDouble(4, qtyToDeliver);
                statement.setDouble(5, qtyDelivered);
                
                if(dateDelivered == null) {
                    statement.setNull(6, java.sql.Types.DATE);
                } else {
                    statement.setTimestamp(6, new java.sql.Timestamp(dateDelivered.getTime()));
                }
   
                statement.setDouble(7, totalDistance);
                statement.setDouble(8, valuePerMile);
                statement.setDouble(9, stopOrderNumber);
                statement.setInt(10, isComplete);
                
                if(!isNew) {
                    statement.setInt(11, routeStopId);
                }

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
}
