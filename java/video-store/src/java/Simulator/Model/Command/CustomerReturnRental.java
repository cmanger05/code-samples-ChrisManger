/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Model.Command;

import Core.Model.AbstractModel;
import Core.Model.CommandInterface;
import Rentals.Model.Rental;
import Users.Model.Customer;

/**
 *
 * @author chris
 */
public class CustomerReturnRental extends AbstractModel implements CommandInterface 
{
    private final Customer _customer;
    
    private final Integer _rentalIdToReturn;
    
    /**
     * 
     * @param customer
     * @param rentalIdToReturn 
     */
    public CustomerReturnRental(Customer customer, Integer rentalIdToReturn)
    {
        this._customer = customer;
        this._rentalIdToReturn = rentalIdToReturn;
    }
    
    /**
     * Retrieves the customer object
     * @return 
     */
    private Customer _getCustomer()
    {
        return this._customer;
    }

    /**
     * Returns the rental id that needs returning
     * @return 
     */
    private Integer _getRentalIdToReturn()
    {
        return this._rentalIdToReturn;
    }
    
    /**
     * Retrieves the id
     * @return 
     */
    @Override
    public Integer getId() {
        return this._getCustomer().getId();
    }

    /**
     * Returns the first name
     * @return 
     */
    public String getFirstName()
    {
        return this._getCustomer().getFirstName();
    }
    
    /**
     * Returns the last name
     * @return 
     */
    public String getLastName()
    {
        return this._getCustomer().getLastName();
    }
    
    /**
     * Returns the rental
     * @return 
     */
    @Override
    public boolean execute() {
        boolean rentalSuccessfullyReturned = false;
        
        Rental rental = new Rental();
        rental.loadById(this._getRentalIdToReturn());
        if(rental.isIntegerNotNull(rental.getId()) && rental.isBooleanValid(rental.getIsActive())) {
            rentalSuccessfullyReturned = rental.returnRental();
        }
        
        return rentalSuccessfullyReturned;
    }
}
