/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Model;

import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.InterfacesCreational.InstanceFactoryStandardInterface;

/**
 *
 * @author chris
 */
public class PlaceholderModel extends AbstractModel {

    @Override
    public Integer getId() 
    {
        return 1;
    }

    public void construct(ArgumentCollection arguments) {}

    /**
     *
     * @return
     */
    @Override
    public InstanceFactoryStandardInterface cloneObject() {
        return new PlaceholderModel();
    }
}
