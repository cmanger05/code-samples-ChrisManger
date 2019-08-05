/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Model;

/**
 *
 * @author chris
 * @param <E>
 */
public abstract class AbstractIndividualValue<E> 
{
    private E objectValue;
   
    /**
     * Constructor
     */
    public AbstractIndividualValue()
    {
        this._initializeObject();
    }
    
    /**
     * Initializes the object
     */
    protected void _initializeObject()
    {
        this.load();
    }
    
    /**
     * Sets value of the object
     * @param value 
     */
    public void setValue(E value)
    {
        objectValue = value;
    }
    
    /**
     * Returns the value
     * @return 
     */
    public E getValue()
    {
        return this.objectValue;
    }
    
    public abstract void load();
}
