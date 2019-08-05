/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Model;

import Core.Util.DBConnectionsUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author chris
 */
public abstract class AbstractModel {
    
    protected String _defaultFieldName;
    
    protected String _defaultTableName;
    
    private boolean _isLoaded = false;
   
    public abstract Integer getId();
    
    /**
     * Sets the model as loaded
     * @param isLoaded 
     */
    public void setIsLoaded(boolean isLoaded)
    {
        this._isLoaded = isLoaded;
    }
    
    /**
     * Returns if the model is loaded
     * @return 
     */
    public boolean isLoaded()
    {
        return this._isLoaded;
    }
    
    /**
     * Loads by the id field
     * The reason why I'm creating a simple function instead of an abstract function
     * is because there are some models where no data every needs to be loaded.
     * @param idFieldValue
     */
    public void loadById(Integer idFieldValue) {}
    
    /**
     * The reason why I'm creating a simple function instead of an abstract function
     * is because there are some models where no data every needs to be loaded.
     * @return 
     */
    public boolean save()
    {
        return true;
    }
    
    /**
     * Returns all records in a table
     * The reason why I'm creating a simple function instead of an abstract function
     * is because there are some models where no data every needs to be loaded.
     * @return 
     */
    public ArrayList<HashMap> getCollection()
    {
        ArrayList<HashMap> collection = new ArrayList<>();
        return collection;
    }
    
    /**
     * Determines if an integer is not null
     * @param number
     * @return 
     */
    public boolean isIntegerNotNull(Integer number)
    {
        boolean isIntegerNotNull = true;
        if(number == null) {
            isIntegerNotNull = false;
        }
        
        return isIntegerNotNull;
    }
    
    /**
     * Determines if the string is not null
     * @param stringValue
     * @return 
     */
    public boolean isStringNotNull(String stringValue)
    {
        boolean isStringNotNull = true;
        if(stringValue == null) {
            isStringNotNull = false;
        }
        
        return isStringNotNull;
    }
    
    /**
     * 
     * @return 
     */
    public boolean doesRecordAlreadyExist()
    {
        boolean doesRecordAlreadyExist = false;
        
        String defaultFieldName = this.getDefaultFieldName();
        String defaultTableName = this.getDefaultTableName();
        
        if(!defaultFieldName.isEmpty() && !defaultTableName.isEmpty() && this.isIntegerNotNull(this.getId())) {
        
            String queryLoadIssue = "SELECT * FROM " + defaultTableName + " WHERE " + defaultFieldName + " = " + this.getId();
            try ( //connect to DB
                Connection currentCon = DBConnectionsUtil.getConnection();
                Statement stmt = currentCon.createStatement();
                ResultSet rs = stmt.executeQuery(queryLoadIssue)) {
                boolean more = rs.next();   
                if(more) {
                    doesRecordAlreadyExist = true;
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
        
        return doesRecordAlreadyExist;
    }
    
    /**
     * Returns the last id inserted into the table
     * @return 
     */
    public Integer getLastInsertedId()
    {
        Integer lastInsertedId = 0;
        
        String defaultFieldName = this.getDefaultFieldName();
        String defaultTableName = this.getDefaultTableName();
        
        boolean isDefaultTableNameEmpty = defaultTableName.isEmpty();
        boolean isDefaultFieldNameEmpty = defaultFieldName.isEmpty();
        
        if(!isDefaultTableNameEmpty && !isDefaultFieldNameEmpty) {
            String queryLoadIssue = "SELECT " + defaultFieldName + " FROM " + defaultTableName + " ORDER BY " + defaultFieldName + " DESC";
            try ( 
                Connection currentCon = DBConnectionsUtil.getConnection();
                Statement stmt = currentCon.createStatement();
                ResultSet rs = stmt.executeQuery(queryLoadIssue)) {
                boolean more = rs.next();   
                if(more) {
                    lastInsertedId = rs.getInt(defaultFieldName);
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

        return lastInsertedId;
    }
    /**
     * @return the _defaultFieldName
     */
    public String getDefaultFieldName() {
        return _defaultFieldName;
    }

    /**
     * @return the _defaultTableName
     */
    public String getDefaultTableName() {
        return _defaultTableName;
    }

    /**
     * @param _defaultFieldName the _defaultFieldName to set
     */
    public void setDefaultFieldName(String _defaultFieldName) {
        this._defaultFieldName = _defaultFieldName;
    }

    /**
     * @param _defaultTableName the _defaultTableName to set
     */
    public void setDefaultTableName(String _defaultTableName) {
        this._defaultTableName = _defaultTableName;
    }
}
