/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouteMatrixBuilder.Model.Collection;

import ObjectCreation.Model.Prototypes.ObjectStandard;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author chris
 */
public class RoutePossibilitiesCollection extends ObjectStandard implements Iterable<ArrayList<Integer>> {
   
    private final ArrayList<ArrayList<Integer>> _items;
    
    /**
     * Constructor
     * @param items 
     */
    public RoutePossibilitiesCollection(ArrayList<ArrayList<Integer>> items)
    {
        this._items = items;
    }
    
    /**
     * Returns all items
     * @return 
     */
    public ArrayList<ArrayList<Integer>> getItems()
    {
        return this._items;
    }
    
    /**
     * Returns the iterator
     * @return 
     */
    @Override
    public Iterator<ArrayList<Integer>> iterator() 
    {
        return this.getItems().iterator();
    }
    
    /**
     * Retrieves the next object
     * @return 
     */
    public ArrayList<Integer> next()
    {
        return this.iterator().next();
    }
}
