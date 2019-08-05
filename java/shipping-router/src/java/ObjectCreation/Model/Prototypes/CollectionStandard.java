/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectCreation.Model.Prototypes;

import ObjectCreation.Model.Base.AbstractCollectionBase;
import java.util.ArrayList;

/**
 *
 * @author chris
 * @param <H>
 */
public class CollectionStandard<H> extends AbstractCollectionBase<H> 
{    
    /**
     * Constructor
     * @param items 
     */
    public CollectionStandard(ArrayList<H> items) 
    {
        super(items);
    } 
    
    /**
     * Adds an item to the collection
     * @param item 
     */
    public void add(H item)
    {
        this.getItems().add(item);
    }
    
    /**
     * Removes an item from the collection
     * @param item 
     */
    public void remove(H item)
    {
        this.getItems().remove(item);
    }
}
