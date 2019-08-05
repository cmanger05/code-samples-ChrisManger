/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testframework.Model;

import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.InterfacesCreational.InstanceFactoryBaseInterface;
import ObjectCreation.InterfacesCreational.InstanceFactoryBuilderInterface;

/**
 *
 * @author chris
 */
public class DynamicObjectComplexBuilder implements InstanceFactoryBuilderInterface {


    @Override
    public InstanceFactoryBaseInterface buildInstance(ArgumentCollection arguments) {
        DynamicObjectComplex dynamicObjectComplex = new DynamicObjectComplex();
        dynamicObjectComplex.construct(arguments);
        return dynamicObjectComplex;
    }

    /**
     * Clones Current object
     * @return 
     */
    @Override
    public DynamicObjectComplexBuilder cloneObject() {
       return new DynamicObjectComplexBuilder();
    }
}
