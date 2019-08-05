/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Model.Generate;

import Catalog.Model.Collection.Videos;
import Catalog.Model.Filter.FilterInStockOnly;
import Catalog.Model.Filter.FilterLimitRecords;
import Catalog.Model.Statistic.NumberVideosOutStock;
import Catalog.Model.Statistic.TotalInStore;
import Catalog.Model.Video;
import Core.Filter.OffsetNumberRecords;
import Users.Model.Customer;

/**
 *
 * @author chris
 */
public class VideoGenerator extends Videos {
    
    private final Customer _customer;
    
    public VideoGenerator(Customer customer)
    {
        this._customer = customer;
        customer.getVideoQtyMin();
    }
    
    /**
     * Returns the customer object
     * @return 
     */
    private Customer getCustomer()
    {
        return this._customer;
    }
    
    /**
     * Loads all the items
     */
    @Override
    public void load()
    {
        
        Video video = new Video();
        Integer numberRecordsInTable = video.getNumberRecordsInTable();
        RandomNumbers randomNumberGenerator1 = new RandomNumbers(1,numberRecordsInTable); //Ensures a record always returned
        Integer offset = randomNumberGenerator1.generateRandom(false);
        
        NumberVideosOutStock numberVideosOutStockInquirer = new NumberVideosOutStock();
        Integer numberVideosOutOfStock = numberVideosOutStockInquirer.getValue();
        
        Customer currentCustomer = this.getCustomer();
        Integer minimum = currentCustomer.getVideoQtyMin();
        Integer maximum = currentCustomer.getVideoQtyMax();
        RandomNumbers randomNumberGenerator2 = new RandomNumbers(minimum,maximum);
        Integer randomLimit = randomNumberGenerator2.generateRandom(false);
        
        this.addResultsFilter(new FilterInStockOnly());
        
        if(numberVideosOutOfStock + offset < numberRecordsInTable) {
            this.addResultsFilter(new OffsetNumberRecords(offset));
        }
        
        this.addResultsFilter(new FilterLimitRecords(randomLimit));
        super.load();
    }
}
