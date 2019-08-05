/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routes.Model.Statistic;

import Core.Model.AbstractIndividualValue;
import Core.Model.PlaceholderModel;
import Routes.Model.Stop;
import Core.Model.DBConnectionsUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author chris
 */
public final class DistanceBetweenStops extends AbstractIndividualValue<Double> {
        private final String _filter;
    
    /**
     * Constructor sets the filter
     * @param beginningStop
     * @param endingStop
     */
    public DistanceBetweenStops(Stop beginningStop, Stop endingStop)
    {
        PlaceholderModel placeholderModel = new PlaceholderModel();
        Integer beggingingStopId = beginningStop.getId();
        Integer endingStopId = endingStop.getId();
        
        String filterText = "WHERE beginning_stop_id = 999999";
        
        if(placeholderModel.isIntegerNotNull(beggingingStopId) && placeholderModel.isIntegerNotNull(endingStopId)) {
            filterText = "WHERE beginning_stop_id = " + beggingingStopId + " AND ending_stop_id = " + endingStopId;
  
        }
        
        this._filter = filterText;
        this.load();  
    }
    
    /**
     * Initializes the object
     */
    @Override
    protected void _initializeObject() {}
    
    /**
     * Returns the filter
     * @return 
     */
    private String _getFilter()
    {
        return this._filter;
    }
    
    /**
     * Loads the value
     */
    @Override
    public void load()
    {
        this.setValue(0.0);
        Double totalDistanceBetweenStops = 0.0;
        try 
        {
            String customFilter = this._getFilter();
            String queryDistanceBetweenStops = "SELECT distance FROM stop_distances " + customFilter ;
            
            Statement stmt;
            ResultSet rs;
            try ( //connect to DB
                Connection currentCon = DBConnectionsUtil.getConnection()) {
                stmt = currentCon.createStatement();
                rs = stmt.executeQuery(queryDistanceBetweenStops);
                boolean more = rs.next();
                if(more) {
                    totalDistanceBetweenStops = rs.getDouble("distance");     
                }
            }
            stmt.close();
            rs.close();
        } 
        catch (Exception ex) 
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
        
        this.setValue(totalDistanceBetweenStops);
    }
}
