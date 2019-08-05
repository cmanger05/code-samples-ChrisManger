/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testframework.Model;

import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.Model.Arguments.ArgumentStandard;
import ObjectCreation.Model.Arguments.ArgumentString;
import ObjectCreation.InterfacesCreational.InstanceFactoryStandardInterface;
import ObjectCreation.Model.ObjectFactory;
import ObjectCreation.Model.Prototypes.CollectionStandard;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class DynamicObjectComplex implements InstanceFactoryStandardInterface 
{
    /**
     * Constructor
     * @param arguments 
     */
    public void construct(ArgumentCollection arguments) {
        try {
            ArgumentString string1 = arguments.getStringArgumentByName("string1");
            ArgumentString string2 = arguments.getStringArgumentByName("string2");
            System.out.println(string1.getValue());
            System.out.println(string2.getValue());
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(DynamicObjectComplexBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public InstanceFactoryStandardInterface cloneObject() {
        return new DynamicObjectComplex();
    }
    
        /**
     * Uses the factory to create an instance of this class
     * @param string1
     * @param string2
     * @return 
     */
    public static DynamicObjectComplex createInstance(String string1, String string2) 
    {
        ArgumentString string1asArgument = new ArgumentString("string1",string1);
        ArgumentString string2asArgument = new ArgumentString("string2",string2);
   
        ArrayList<ArgumentStandard> arrayListArguments;
        arrayListArguments = new ArrayList<>();
        arrayListArguments.add(string1asArgument);
        arrayListArguments.add(string2asArgument);
        CollectionStandard<ArgumentStandard> standardCollection = new CollectionStandard(arrayListArguments);
        ArgumentCollection argumentCollection = new ArgumentCollection(standardCollection);
        
        DynamicObjectComplex dynamicObjectComplex = (DynamicObjectComplex)ObjectFactory.get("DynamicObjectComplex", argumentCollection);
        return dynamicObjectComplex;
    }
    
    /**
     * Tests output
     */
    public void testOutput()
    {
        System.out.println("testing dynamicObjectComplex sendoutput.");
    }
}
