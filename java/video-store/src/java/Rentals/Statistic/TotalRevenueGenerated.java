/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rentals.Statistic;

import Core.Model.AbstractIndividualValue;
import Core.Model.DBConnectionsUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public class TotalRevenueGenerated extends AbstractIndividualValue<Double>
{
    private final String _filter;
    
    /**
     * Constructor sets the filter
     * @param startDateText
     * @param endDateText 
     */
    public TotalRevenueGenerated(String startDateText, String endDateText)
    {
        this._filter = "WHERE rental_date_start >= '" + startDateText + "' AND rental_date_start <= '" + endDateText + "'";
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
            Double testDouble = 5.05;
            String customFilter = this._getFilter();
            String queryQtyRented = "select SUM(grand_total) AS grand_total FROM RENTAL " + customFilter ;
            
            //connect to DB 
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryQtyRented);	        
            boolean more = rs.next();
	    if(more) { 
                totalRevenueGenerated = rs.getDouble("grand_total");                         
                currentCon.close();
                stmt.close();
                rs.close();
            }
        } 
        catch (Exception ex) 
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
        
        this.setValue(totalRevenueGenerated);
    }
    
}
