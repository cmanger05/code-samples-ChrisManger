/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testframework.Model;

import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.InterfacesCreational.InstanceFactoryStandardInterface;
import ObjectCreation.Model.Prototypes.ObjectStandard;

/**
 *
 * @author chris
 */
public class DynamicObject extends ObjectStandard implements InstanceFactoryStandardInterface {
    @Override
    public String toString()
    {
        return "Testeroni";
    }

    @Override
    public void construct(ArgumentCollection arguments) {}
    
    /**
     * Clones the current object
     * @return 
     */
    @Override
    public DynamicObject cloneObject()
    {
        return new DynamicObject();
    }
}
