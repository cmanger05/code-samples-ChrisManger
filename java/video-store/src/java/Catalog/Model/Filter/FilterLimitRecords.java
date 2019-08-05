/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalog.Model.Filter;
import Core.Model.AbstractCollection;
import Core.Model.AbstractModel;

/**
 *
 * @author chris
 */
public class FilterLimitRecords implements FilterResultsInterface  {
    
    private Integer _maximumNumberRecords;
    
    private FilterLimitRecords() {}
    
    /**
     * Filter that limits records
     * @param maxNumberRecords 
     */
    public FilterLimitRecords(Integer maxNumberRecords)
    {
        this._maximumNumberRecords = maxNumberRecords;
    }
    
    /**
     * Returns the maximum number of records allowed
     * @return 
     */
    private Integer _getMaximumNumberRecords()
    {
        return this._maximumNumberRecords;
    }
    
    /**
     * Removes videos that are out of stock from the collection
     * @param resultsCollection 
     */
    @Override
    public void filter(AbstractCollection resultsCollection) 
    {
        Integer counterNumberItems = 0;
        Integer maximumNumberItems = this._getMaximumNumberRecords();
        AbstractModel resultItemTransformed;
        for(Object resultItem : resultsCollection) {
            if(resultItem instanceof AbstractModel) {
                resultItemTransformed = (AbstractModel)resultItem;
                counterNumberItems++;
                if(counterNumberItems > maximumNumberItems) {
                    resultsCollection.removeItem(resultItemTransformed);
                }
            }
        }
    }   
}
