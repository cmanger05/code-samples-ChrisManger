/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalog.Model.Filter;

import Catalog.Model.Collection.Videos;
import Catalog.Model.Video;
import Core.Model.AbstractCollection;

/**
 *
 * @author chris
 */
public class FilterInStockOnly implements FilterResultsInterface 
{    
    /**
     * Removes videos that are out of stock from the collection
     * @param resultsCollection 
     */
    @Override
    public void filter(AbstractCollection resultsCollection) {
        if(resultsCollection instanceof Videos) {
            Videos videosCollection = (Videos)resultsCollection;

            for(Video video : videosCollection) {
                if(video.getQuantityInStock() == 0) {
                    resultsCollection.removeItem(video);
                }      
            }
        }
    }   
}
