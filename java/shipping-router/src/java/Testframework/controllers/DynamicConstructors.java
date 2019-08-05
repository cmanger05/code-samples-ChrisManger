/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testframework.controllers;

import Core.controller.AbstractController;
import Testframework.Model.DynamicConstructor;
import Testframework.Model.DynamicObject;
import Testframework.Model.DynamicObjectComplex;


/**
 *
 * @author chris
 */
public class DynamicConstructors extends AbstractController {

    /**
     * Runs the program
     * @param args 
     */
    public static void main(String args[])
    { 
        DynamicConstructors currentController = new DynamicConstructors();
        currentController._testNewObjectCreation();
    }
    
    /**
     * Creates new object using factory
     */
    private void _testNewObjectCreation()
    {
        DynamicConstructor dynamicConstructor = DynamicConstructor.createInstance("test short desc uno", " test long description", 11, 33, new DynamicObject());
        dynamicConstructor.testOutputMethod();
        DynamicObjectComplex dynamicObjectComplex = DynamicObjectComplex.createInstance("test mr string 1", "test mr string 2");
        dynamicObjectComplex.testOutput();
    }
}
