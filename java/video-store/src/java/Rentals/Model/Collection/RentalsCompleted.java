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
public class RentalsCompleted extends AbstractRentalsCollection 
{
    /**
     * Loads only completed rentals
     */
    @Override
    public void load()
    {
        this.addFilter("r.is_active = 0");
        super.load();
    }
}
