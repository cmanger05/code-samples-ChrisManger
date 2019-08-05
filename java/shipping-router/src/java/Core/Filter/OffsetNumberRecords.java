/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Filter;

import Core.Model.AbstractCollection;
import Core.Model.AbstractModel;

/**
 *
 * @author chris
 */
public class OffsetNumberRecords implements FilterResultsInterface {
    
    private Integer _numberRecordsToOffset;
    
    private OffsetNumberRecords() {}
    
    /**
     * Filter that limits records
     * @param numberRecordsToOffset 
     */
    public OffsetNumberRecords(Integer numberRecordsToOffset)
    {
        this._numberRecordsToOffset = numberRecordsToOffset;
    }
    
    /**
     * Returns the maximum number of records allowed
     * @return 
     */
    private Integer _getNumberRecordsToOffset()
    {
        return this._numberRecordsToOffset;
    }
    
    /**
     * Removes videos that are out of stock from the collection
     * @param resultsCollection 
     */
    @Override
    public void filter(AbstractCollection resultsCollection) 
    {
        Integer counterNumberRecords = 0;
        Integer numberRecordsToOffset = this._getNumberRecordsToOffset();
        AbstractModel resultItemTransposed;
        for(Object resultItem : resultsCollection) {
            if(resultItem instanceof AbstractModel) {
                resultItemTransposed = (AbstractModel)resultItem;
                counterNumberRecords++;
                if(counterNumberRecords <= numberRecordsToOffset) {
                    resultsCollection.removeItem(resultItemTransposed);
                }
            }
        }
    }  
}
