/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.HashMap;

/**
 *
 * @author chris
 */
public abstract class DataObjectAbstract implements DataObjectInterface {
    HashMap<String, String> dataVariablesStoredByString = new HashMap<>();
    HashMap<String, Object> dataVariablesStoredByObject = new HashMap<>();
    
    public void setDataByKey(String key, String value)
    {
        this.dataVariablesStoredByString.put(key, value);
    }
    
    public String getDataByKey(String key)
    {
        return this.dataVariablesStoredByString.get(key);
    }
    
    public void setDataObjectByKey(String key, String value)
    {
        this.dataVariablesStoredByObject.put(key, value);
    }
    
    public Object getDataObjectByKey(String key)
    {
        return this.dataVariablesStoredByObject.get(key);
    }
}
