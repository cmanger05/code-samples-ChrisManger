/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routes.Model.Statistic;

import Core.Model.AbstractIndividualValue;
import Core.Model.DBConnectionsUtil;
import Core.Model.PlaceholderModel;
import Routes.Model.Stop;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public final class QtyToDeliver extends AbstractIndividualValue<Double> 
{    
    private final String _filter;
    
    /**
     * Constructor sets the filter
     * @param store
     */
    public QtyToDeliver(Stop store)
    {
        PlaceholderModel placeholderModel = new PlaceholderModel();
        Integer storeId = store.getId();
        
        String filterText = "WHERE store_id = 999999";
        
        if(placeholderModel.isIntegerNotNull(storeId)) {
            filterText = "WHERE store_id = " + storeId + " AND is_created = 0";
        }
        
        this._filter = filterText;
        this.load();  
    }
    
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
        Double totalRevenueGenerated = 0.0;
        try 
        {
            String customFilter = this._getFilter();
            String queryQtyToDeliver = "select SUM(qty_requested) AS total_qty_requested FROM pending_route_creation " + customFilter ;
            
            Statement stmt;
            ResultSet rs;
            try ( //connect to DB
                Connection currentCon = DBConnectionsUtil.getConnection()) {
                stmt = currentCon.createStatement();
                rs = stmt.executeQuery(queryQtyToDeliver);
                boolean more = rs.next();                         
                if(more) {
                    totalRevenueGenerated = rs.getDouble("total_qty_requested");
                }
            }
            stmt.close();
            rs.close();
        } 
        catch (Exception ex) 
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
        
        this.setValue(totalRevenueGenerated);
    }
}
