/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routes.Model.Resource;

import Core.Model.AbstractResourceSave;
import Core.Model.AbstractModel;
import Core.Model.DBConnectionsUtil;
import Routes.Model.Route;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
/**
 *
 * @author chris
 */
public class RouteSave extends AbstractResourceSave 
{
    /**
     * Saves a new record
     * @param model
     * @return 
     */
    @Override
    public boolean saveNew(AbstractModel model)
    {
        String queryString = "INSERT INTO route(route_id, route_name, route_hub_id, "
                + "route_truck_id, date_route_started, date_route_delivered, total_distance, "
                + "average_miles_per_leg, value_per_mile, is_complete)"
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
        String queryString = "UPDATE route SET route_id = ?, route_name = ?, "
                                + "route_hub_id = ?, route_truck_id = ?, date_route_started = ?, date_route_delivered = ?, "
                                + "total_distance = ?, average_miles_per_leg = ?, value_per_mile = ?, is_complete = ? "
                                + "WHERE route_id = ?";        

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
       
        Route route = (Route)model;
        try {
            Connection connection = DBConnectionsUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(queryString);

            Integer routeId = route.getId();
            
            if(isNew) {
                routeId = route.getLastInsertedId() + 1;
                route.setRouteId(routeId);
            }
            
            String routeName = route.getName();
            Integer routeHubId = route.getHubId();
            Integer routeTruckId = route.getTruckId();
            Date dateRouteStarted = route.getDateStarted();
            Date dateRouteDelivered = route.getDateDelivered();
            Double totalDistance = route.getTotalDistance();
            Double averageMilesPerLeg = route.getAverageMilesPerLeg();
            Double valuePerMile = route.getValuePerMile();
            Integer isComplete = route.getIsComplete();

            if(route.isIntegerNotNull(routeId) && route.isStringNotNull(routeName) 
                    && route.isIntegerNotNull(routeHubId) && route.isIntegerNotNull(routeTruckId) 
                    && route.isDoubleNotNull(totalDistance) && route.isDoubleNotNull(averageMilesPerLeg) 
                    && route.isDoubleNotNull(valuePerMile) && route.isIntegerNotNull(isComplete)) {
                statement.setInt(1, routeId);
                statement.setString(2, routeName);
                statement.setInt(3, routeHubId);
                statement.setInt(4, routeTruckId);
                
                if(dateRouteStarted == null) {
                    statement.setNull(5, java.sql.Types.DATE);
                } else {
                    statement.setTimestamp(5, new java.sql.Timestamp(dateRouteStarted.getTime()));
                }
                
                if(dateRouteDelivered == null) {
                    statement.setNull(6, java.sql.Types.DATE);
                } else {
                    statement.setTimestamp(6, new java.sql.Timestamp(dateRouteDelivered.getTime()));
                }
                
                statement.setDouble(7, totalDistance);
                statement.setDouble(8, averageMilesPerLeg);
                statement.setDouble(9, valuePerMile);
                statement.setInt(10, isComplete);
                
                if(!isNew) {
                    statement.setInt(11, routeId);
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
