/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Model.Generate;

import Simulator.Model.Generate.Collection.CustomersRegeneratingCollection;
import Users.Model.Collection.Customers;
import Users.Model.Customer;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class CustomersNewGenerator {
    
    private final CustomersRegeneratingCollection _customerCollection;
    
    private final RandomNumbers _randomNumberGenerator;
    
    /**
     * Sets the customer collection
     */
    public CustomersNewGenerator()
    {
        RandomNumbers randomNumberGenerator = new RandomNumbers(3,6);
        this._randomNumberGenerator = randomNumberGenerator;       
        CustomersRegeneratingCollection customerCollection = new CustomersRegeneratingCollection();
        this._customerCollection = customerCollection;
        customerCollection.load();
    }
    
    /**
     * Returns the customer collection
     * @return 
     */
    private Customers _getCustomerCollection()
    {
        return this._customerCollection;
    }
    
    /**
     * Returns the random number generator
     * @return 
     */
    private RandomNumbers _getRandomNumberGenerator()
    {
        return this._randomNumberGenerator;
    }
    
    /**
     * Retrieves the next customer
     * @return 
     */
    public Customer next()
    {
        return (Customer)this._getCustomerCollection().next();
    }
    
    /**
     * Returns number random customers that need to be generated
     * @return 
     */
    public Integer getRandomNumberCustomersToGenerate()
    {
        RandomNumbers randomNumberGenerator = this._getRandomNumberGenerator();
        return randomNumberGenerator.generateRandom(true);
    }
}
