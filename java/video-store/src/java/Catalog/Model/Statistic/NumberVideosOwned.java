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
public class NumberVideosOwned extends AbstractIndividualValue<Integer> {

    /**
     * Loads the value
     */
    @Override
    public void load() {
        this.setValue(0);
        Integer qtyOwned = 0;
        try 
        {
            String queryQtyOwned = "SELECT SUM(v.qty_owned) as qty_owned FROM video v";
            //connect to DB 
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryQtyOwned);	        
            boolean more = rs.next();
	    if(more) { 
                qtyOwned = rs.getInt("qty_owned");            
                currentCon.close();
                stmt.close();
                rs.close();
            }
        } 
        catch (Exception ex) 
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
        
        this.setValue(qtyOwned);
    }  
}
