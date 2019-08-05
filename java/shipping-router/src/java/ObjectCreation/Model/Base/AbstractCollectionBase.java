/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectCreation.Model.Base;

import ObjectCreation.Model.Prototypes.ObjectStandard;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author chris
 * @param <H>
 */
public abstract class AbstractCollectionBase<H> extends ObjectStandard implements Iterable<H>
{
    private final ArrayList<H> _items;
    
    /**
     * Constructor
     * @param items 
     */
    public AbstractCollectionBase(ArrayList<H> items)
    {
        this._items = items;
    }
    
    /**
     * Returns all items
     * @return 
     */
    public ArrayList<H> getItems()
    {
        return this._items;
    }
    
    /**
     * Returns the iterator
     * @return 
     */
    @Override
    public Iterator<H> iterator() 
    {
        return this.getItems().iterator();
    }
    
    /**
     * Retrieves the next object
     * @return 
     */
    public H next()
    {
        return this.iterator().next();
    }
}
