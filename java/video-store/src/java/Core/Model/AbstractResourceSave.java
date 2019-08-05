/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Model;

import Rentals.Model.Resource.RentalSave;

/**
 *
 * @author chris
 */
public abstract class AbstractResourceSave implements ResourceSaveInterface 
{
    /**
     * Saves the object
     * @param model
     * @return 
     */
    public boolean save(AbstractModel model)
    {
        boolean didRecordSaveCorrectly = false;
        
        if(!model.doesRecordAlreadyExist()) { 
            didRecordSaveCorrectly = this.saveNew(model);
        } else {
            didRecordSaveCorrectly = this.saveExisting(model);
        }

        return didRecordSaveCorrectly;
    }
}
