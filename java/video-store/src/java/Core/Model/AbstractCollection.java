/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Model;

import Catalog.Model.Filter.FilterResultsInterface;
import Simulator.Model.Generate.RegenerateInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author chris
 * @param <E>
 */
public abstract class AbstractCollection<E extends AbstractModel> implements Iterable<E>
{
    private final ArrayList<String> _filters = new ArrayList<>();
    
    private final ArrayList<FilterResultsInterface> _filterResults = new ArrayList<>();
    
    private final HashMap<Integer,E> _items = new HashMap<>();
    
    /**
     * Adds a filter to the collection
     * @param whereCondition
     */
    public void addFilter(String whereCondition) {
        this._filters.add(whereCondition);
    }
    
    /**
     * Returns the filters
     * @return 
     */
    public ArrayList<String> getFilters()
    {
        return this._filters;
    }
  
    /**
     * Adds a results filter
     * @param filter 
     */
    public void addResultsFilter(FilterResultsInterface filter)
    {
        this._filterResults.add(filter);
    }
    
    /**
     * Returns the results filters
     * @return 
     */
    public ArrayList<FilterResultsInterface> getResultsFilters()
    {
        return this._filterResults;
    }
   
    /**
     * Applies the results filters
     */
    public void applyResultsFilters()
    {
        this.getResultsFilters().stream().forEach((resultsFilter) -> {
            resultsFilter.filter(this);
        });
    }
    
    public abstract void load();
    
    /**
     * Returns all items
     * @return 
     */
    public HashMap<Integer,E> getCollection()
    {        
        return this._items;
    }
        
    /**
     * Returns the where statement
     * @return 
     */
    protected String _renderWhereClause()
    {
        String whereStatement = "";
        ArrayList<String> filters;
        filters = this.getFilters();
        boolean hasFilters = (filters.size() > 0);
        Integer numberFilters = filters.size();
        if(numberFilters > 0) {
            Integer numberFieldsProcesed = 0;
            for(String whereCondition : filters) {
                if(numberFieldsProcesed != 0) {
                    whereStatement += " AND ";
                } else {
                    whereStatement += " WHERE ";
                }
                whereStatement += whereCondition;
                numberFieldsProcesed++;
            }
        }
        
        return whereStatement;
    }
    
    /**
     * Adds an item to the array
     * @param keyName
     * @param item 
     */
    public void addItem(Integer keyName, E item)
    {
        this._items.put(keyName, item);
    }
   
    /**
     * Removes an item from the collection
     * @param itemToRemove 
     */
    public void removeItem(E itemToRemove)
    {
        this._items.remove(itemToRemove.getId());
        if(this._items.isEmpty() && this instanceof RegenerateInterface) {
            this.load();
        }
    }
    
    /**
     * Returns an item by its key
     * @param keyName
     * @return 
     */
    public E getItemByKey(Integer keyName)
    {
        E item = this.createNewItem();
        if(this._items.containsKey(keyName)) {
            item = this._items.get(keyName);
        }
        
        return item;
    }
    
    /**
     * Creates a new item, used to add new items to a collection
     * @return 
     */
    public abstract E createNewItem();
    
    /**
     * Iterates through the HashMap object
     * @return 
     */
    @Override
    public Iterator<E> iterator() {
        ArrayList<E> allRecords = new ArrayList<>();
        Iterator it = this.getCollection().entrySet().iterator();
        E newItem;
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            newItem = (E)pair.getValue();
            allRecords.add(newItem);
        }
        
        return allRecords.iterator();
    }
    
        /**
     * Returns the next element
     * @return 
     */
    public E next()
    {
        E newItem = this.createNewItem();
        Iterator it = this.getCollection().entrySet().iterator();
        if(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            newItem = (E)pair.getValue();
            it.remove();
            this.removeItem(newItem);
        }
        
        return newItem;
    }
   
    /**
     * Returns the size of the collection
     * @return 
     */
    public Integer getSize()
    {
        return this.getCollection().size();
    }
    
    /**
     * Creates a new validator
     * @param dataModel
     * @return 
     */
    public CollectionItemValidator createNewValidator(E dataModel) 
    {
        return new CollectionItemValidator(dataModel);
    }
}
