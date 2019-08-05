/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Statistic;

import Core.Model.AbstractIndividualValue;
import Core.Model.DBConnectionsUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public class NumberRecordsInTable extends AbstractIndividualValue<Integer> 
{
    private final String _tableName;
    
    /**
     * Ensures table name is set
     * @param tableName 
     */
    public NumberRecordsInTable(String tableName) 
    {
        this._tableName = tableName;
        this.load();
    }
    
        /**
     * Initializes the object
     */
    @Override
    protected void _initializeObject() {}
    
    /**
     * Returns the table name
     * @return 
     */
    private String _getTableName()
    {
        return this._tableName;
    }
    
    /**
     * Loads the value
     */
    @Override
    public final void load() {
        this.setValue(0);
        Integer numberRecords = 0;
        
        String tableName = this._getTableName();
        try 
        {
            String queryNumberRecordsInTable = "SELECT count(*) AS number_records FROM " + tableName;
            //connect to DB 
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryNumberRecordsInTable);	        
            boolean more = rs.next();
	    if(more) { 
                numberRecords = rs.getInt("number_records");            
            }
            
            currentCon.close();
            stmt.close();
            rs.close();
        } 
        catch (Exception ex) 
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
        
        this.setValue(numberRecords);
    }  
}
