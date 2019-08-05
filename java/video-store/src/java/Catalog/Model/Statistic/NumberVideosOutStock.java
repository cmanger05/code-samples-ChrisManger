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
public class NumberVideosOutStock extends AbstractIndividualValue<Integer> {

    /**
     * Loads the value
     */
    @Override
    public void load() {
        this.setValue(0);
        Integer numberVideosOutStock = 0;

        String queryQtyOwned = "select v.video_id\n" +
            "FROM video v \n" +
            "INNER JOIN rental_video rv ON v.video_id = rv.video_id\n" +
            "INNER JOIN rental r ON rv.rental_id = r.rental_id\n" +
            "WHERE r.is_active = 1 \n" +
            "GROUP BY v.video_id\n" +
            "HAVING MAX(v.qty_owned) - SUM(rv.qty) <= 0";
            
        try 
        {       
            Connection currentCon;
            Statement stmt;
            
            currentCon = DBConnectionsUtil.getConnection();
            stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryQtyOwned);
            boolean more = rs.next();
            while(rs.next()) {
                numberVideosOutStock++;
            }            
                
            currentCon.close();
            stmt.close();         
        } 
        catch (Exception ex) 
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
        
        this.setValue(numberVideosOutStock);
    }  
}
