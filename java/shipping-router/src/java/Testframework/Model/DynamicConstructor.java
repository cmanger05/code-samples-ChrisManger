/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testframework.Model;

import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.Model.Arguments.ArgumentInteger;
import ObjectCreation.Model.Arguments.ArgumentObject;
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
public class DynamicConstructor implements InstanceFactoryStandardInterface
{
    /**
     * Constructor
     * @param arguments 
     */
    @Override
    public void construct(ArgumentCollection arguments) 
    {
        try {
            ArgumentString shortDescription = arguments.getStringArgumentByName("short description");
            ArgumentString longDescription = arguments.getStringArgumentByName("long description");
            ArgumentInteger integerOne = arguments.getIntegerArgumentByName("integerOne");
            ArgumentInteger integerThree = arguments.getIntegerArgumentByName("integerThree");
            ArgumentObject testerministroni = arguments.getObjectArgumentByName("testerministroni");
            ArgumentCollection testCollection = arguments.getCollectionArgumentByName("testCollection");
            ArgumentInteger testOne = testCollection.getIntegerArgumentByName("testOne");
            ArgumentInteger testTwo = testCollection.getIntegerArgumentByName("testTwo");
            ArgumentInteger testThree = testCollection.getIntegerArgumentByName("testThree");
            System.out.println(shortDescription.getValue());
            System.out.println(longDescription.getValue());
            System.out.println(integerOne.getValue());
            System.out.println(integerThree.getValue());
            System.out.println(testerministroni.getValue());
            System.out.println(testOne.getValue());
            System.out.println(testTwo.getValue());
            System.out.println(testThree.getValue());
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(DynamicConstructor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testOutputMethod()
    {
        System.out.println("test output method executed.");
    }

    @Override
    public DynamicConstructor cloneObject() {
        return new DynamicConstructor();
    }
    
    /**
     * Uses the factory to create an instance of this class
     * @param string1
     * @param string2
     * @param int1
     * @param int2
     * @param object1
     * @return 
     */
    public static DynamicConstructor createInstance(String string1, String string2, Integer int1, Integer int2, DynamicObject object1) 
    {
        ArgumentString shortDescription = new ArgumentString("short description",string1);
        ArgumentString longDescription = new ArgumentString("long description",string2);
        ArgumentInteger integerOne = new ArgumentInteger("integerOne",int1);
        ArgumentInteger integerThree = new ArgumentInteger("integerThree",int2);
        ArgumentObject testeroni1 = new ArgumentObject("testerministroni", new DynamicObject());
        
        ArgumentInteger testOne = new ArgumentInteger("testOne",1);
        ArgumentInteger testTwo = new ArgumentInteger("testTwo",2);
        ArgumentInteger testThree = new ArgumentInteger("testThree",3);
        ArgumentInteger testFour = new ArgumentInteger("testFour",4);
        ArrayList<ArgumentStandard> arrayListArgumentsTestCollection;
        arrayListArgumentsTestCollection = new ArrayList<>();
        arrayListArgumentsTestCollection.add(testOne);
        arrayListArgumentsTestCollection.add(testTwo);
        arrayListArgumentsTestCollection.add(testThree);
        arrayListArgumentsTestCollection.add(testFour);
        CollectionStandard<ArgumentStandard> testCollection = new CollectionStandard(arrayListArgumentsTestCollection);
        ArgumentCollection testArgumentCollection = new ArgumentCollection("testCollection",testCollection);
        
        ArrayList<ArgumentStandard> arrayListArguments;
        arrayListArguments = new ArrayList<>();
        arrayListArguments.add(shortDescription);
        arrayListArguments.add(longDescription);
        arrayListArguments.add(integerOne);
        arrayListArguments.add(integerThree);
        arrayListArguments.add(testeroni1);
        arrayListArguments.add(testArgumentCollection);
        CollectionStandard<ArgumentStandard> standardCollection = new CollectionStandard(arrayListArguments);
        ArgumentCollection argumentCollection = new ArgumentCollection(standardCollection);
        
        DynamicConstructor dynamicConstructor = (DynamicConstructor)ObjectFactory.get("DynamicConstructor", argumentCollection);
        return dynamicConstructor;
    }
}
