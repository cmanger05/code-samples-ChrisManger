/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rentals.Model.Collection;

/**
 *
 * @author chris
 */
public class RentalsActive extends AbstractRentalsCollection 
{    
    /**
     * Loads only active rentals
     */
    @Override
    public void load()
    {
        this.addFilter("r.is_active = 1");
        super.load();
    }
}
