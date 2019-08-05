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
import Users.Model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public class Routes extends AbstractCollection<Route> 
{
    /**
     * Loads the videos for one rental only 
     * @param routeId 
     */
    public void loadSingleInstance(Integer routeId)
    {
        this.addFilter("r.route_id = " + routeId);
        this.load();
    }
    
    /**
     * Loads all routes belonging to single user
     * @param user 
     */
    public void loadByUser(User user)
    {
        Integer userId = user.getUserId();
        if(user.isIntegerNotNull(userId)) {
            this.addFilter("ur.user_id = " + userId);
            this.load();
        }
    }
    
    /**
     * Loads Rental Videos
     */
    @Override
    public void load() {

        String whereClause = this._renderWhereClause();
        String queryLoadAllPendingRoutesToBeCreated = "SELECT r.route_id, r.route_name, r.route_hub_id, "
                + "r.route_truck_id, r.date_route_started, r.date_route_delivered, r.total_distance, "
                + "r.average_miles_per_leg, r.value_per_mile, r.is_complete, ur.user_id " +
            "FROM route r LEFT JOIN user_route ur ON r.route_id = ur.route_id" + whereClause ;
        
        try ( //connect to DB
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryLoadAllPendingRoutesToBeCreated)) {

            Route routeItem;
            CollectionItemValidator itemValidator;
            Integer routeId;
            String routeName;
            Integer hubId;
            Integer truckId;
            Date dateStarted;
            Date dateDelivered;
            Double totalDistance;
            Double averageMilesPerLeg;
            Integer averageSecondsPerLeg;
            Double valuePerMilePerLeg;
            Integer isComplete;
            
            while(rs.next()) {
                routeItem = (Route)this.createNewItem();
                itemValidator = this.createNewValidator(routeItem);

                routeId   = rs.getInt("route_id");
                if(itemValidator.isIntegerNotNull(routeId, true)) {
                    routeItem.setRouteId(routeId);
                }
                
                routeName   = rs.getString("route_name");
                if(itemValidator.isStringNotNull(routeName, true)) {
                    routeItem.setName(routeName);
                }
                                
                hubId = rs.getInt("route_hub_id");
                if(itemValidator.isIntegerNotNull(hubId, true)) {
                    routeItem.setHubId(hubId);
                }
                
                truckId = rs.getInt("route_truck_id");
                if(itemValidator.isIntegerNotNull(truckId, true)) {
                    routeItem.setTruckId(truckId);
                }
                
                dateStarted = rs.getDate("date_route_started");
                if(itemValidator.isDateNotEmpty(dateStarted, false)) {
                    routeItem.setDateStarted(dateStarted);
                }
                
                dateDelivered = rs.getDate("date_route_delivered");
                if(itemValidator.isDateNotEmpty(dateDelivered, false)) {
                    routeItem.setDateDelivered(dateDelivered);
                }
                
                totalDistance = rs.getDouble("total_distance"); 
                if(itemValidator.isDoubleNotNull(totalDistance, false)) {
                    routeItem.setTotalDistance(totalDistance);
                }
                
                averageMilesPerLeg = rs.getDouble("average_miles_per_leg"); 
                if(itemValidator.isDoubleNotNull(averageMilesPerLeg, false)) {
                    routeItem.setAverageMilesPerLeg(averageMilesPerLeg);
                }
                              
                valuePerMilePerLeg = rs.getDouble("value_per_mile"); 
                if(itemValidator.isDoubleNotNull(valuePerMilePerLeg, false)) {
                    routeItem.setValuePerMile(valuePerMilePerLeg);
                }
                
                isComplete = rs.getInt("is_complete");
                if(itemValidator.isIntegerNotNull(isComplete, true)) {
                    routeItem.setIsComplete(isComplete);
                }
                 
                if(itemValidator.isValid()) {
                    this.addItem(routeId, routeItem);
                    routeItem.loadAssociatedRouteStops();
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
    public Route createNewItem() 
    {    
        return Route.createInstance();
    }     
}
