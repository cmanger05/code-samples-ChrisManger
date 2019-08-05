/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routes.Model.Collection;

import Core.Model.AbstractCollection;
import Core.Model.CollectionItemValidator;
import Core.Model.DBConnectionsUtil;
import Routes.Model.Stop;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public class Stops extends AbstractCollection<Stop> {
    
    /**
     * Loads one stop only 
     * @param stopId 
     */
    public void loadSingleInstance(Integer stopId)
    {
        this.addFilter("s.stop_id = " + stopId);
        this.load();
    }
    
    /**
     * Loads Rental Videos
     */
    @Override
    public void load() {

        String whereClause = this._renderWhereClause();
        String queryLoadAllPendingRoutesToBeCreated = "SELECT s.stop_id, s.type_id, "
                + "t.stop_type_name, s.stop_name, s.street_address, s.street_address2, s.city, s.region, s.postcode " +
            "FROM stop s INNER JOIN stop_type t ON s.type_id = t.stop_type_id " + whereClause;
        
        try ( //connect to DB
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryLoadAllPendingRoutesToBeCreated)) {

            Stop stopItem;
            CollectionItemValidator itemValidator;
            Integer stopId;
            Integer typeId;
            String stopTypeName;
            String stopName;
            String streetAddress;
            String streetAddress2;
            String city;
            String region;
            String postcode;
            
            while(rs.next()) {
                stopItem = (Stop)this.createNewItem();
                itemValidator = this.createNewValidator(stopItem);

                stopId   = rs.getInt("stop_id");
                if(itemValidator.isIntegerNotNull(stopId, true)) {
                    stopItem.setStopId(stopId);
                }
                
                typeId   = rs.getInt("type_id");
                if(itemValidator.isIntegerNotNull(typeId, true)) {
                    stopItem.setTypeId(typeId);
                }
                                
                stopTypeName = rs.getString("stop_type_name");
                if(itemValidator.isStringNotNull(stopTypeName, true)) {
                    stopItem.setStopTypeName(stopTypeName);
                }
                
                stopName = rs.getString("stop_name"); 
                if(itemValidator.isStringNotNull(stopName, true)) {
                    stopItem.setStopName(stopName);
                }
                
                streetAddress = rs.getString("street_address"); 
                if(itemValidator.isStringNotNull(streetAddress, true)) {
                    stopItem.setStreetAddress(streetAddress);
                }
                
                streetAddress2 = rs.getString("street_address2"); 
                if(itemValidator.isStringNotNull(streetAddress2, false)) {
                    stopItem.setStreetAddress2(streetAddress2);
                }
                
                city = rs.getString("city"); 
                if(itemValidator.isStringNotNull(city, true)) {
                    stopItem.setCity(city);
                }
                
                region = rs.getString("region"); 
                if(itemValidator.isStringNotNull(region, true)) {
                    stopItem.setRegion(region);
                }
                
                postcode = rs.getString("postcode"); 
                if(itemValidator.isStringNotNull(postcode, true)) {
                    stopItem.setPostcode(postcode);
                }
                
                if(itemValidator.isValid()) {
                    this.addItem(stopId, stopItem);
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
     * Returns an instance of Stop
     * @return 
     */
    @Override
    public Stop createNewItem() 
    {    
        return Stop.createInstance();
    } 
}
