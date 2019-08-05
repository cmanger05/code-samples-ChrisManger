/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalog.Model.Statistic;

import Core.Model.AbstractIndividualValue;
import Core.Model.DBConnectionsUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public class NumberVideosRented extends AbstractIndividualValue<Integer> {
    
    /**
     * Loads the value
     */
    @Override
    public void load()
    {
        this.setValue(0);
        Integer qtyRented = 0;
        try 
        {
            String queryQtyRented = "select SUM(rv.qty) AS qty_rented " +
                "FROM video v " +
                "INNER JOIN rental_video rv ON v.video_id = rv.video_id " +
                "INNER JOIN rental r ON rv.rental_id = r.rental_id " +
                "WHERE r.is_active = 1 ";
            
            //connect to DB 
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryQtyRented);	        
            boolean more = rs.next();
	    if(more) { 
                qtyRented = rs.getInt("qty_rented");                         
                currentCon.close();
                stmt.close();
                rs.close();
            }
        } 
        catch (Exception ex) 
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
        
        this.setValue(qtyRented);
    }
}
