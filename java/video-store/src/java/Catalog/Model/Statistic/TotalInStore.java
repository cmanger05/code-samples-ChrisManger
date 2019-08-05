/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalog.Model.Statistic;

import Core.Model.AbstractIndividualValue;

/**
 *
 * @author chris
 */
public class TotalInStore extends AbstractIndividualValue<Integer> {
    /**
     * Determines the total number videos in the store
     */
    @Override
    public void load() {
        NumberVideosOwned numberVideosOwned = new NumberVideosOwned();
        NumberVideosRented numberVideosRented = new NumberVideosRented();
        Integer totalNumberVideosInStore = numberVideosOwned.getValue() - numberVideosRented.getValue();
        this.setValue(totalNumberVideosInStore);
    }
}
