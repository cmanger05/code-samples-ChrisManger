/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Model.Generate;

import Core.Model.AbstractCollection;
import Core.Model.DBConnectionsUtil;
import Core.Model.PlaceholderModel;
import Simulator.Model.Command.CustomerReturnRental;
import Users.Model.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public final class CustomersReturningGenerator extends AbstractCollection<CustomerReturnRental> {
    
    private String dateFilter;

    private CustomersReturningGenerator() {}

    /**
     * Forces user to pass date filter into constructor
     * @param dateFilter 
     */
    public CustomersReturningGenerator(String dateFilter)
    {
        this._setDateFilter(dateFilter);
    }

    /**
     * Sets the date filter
     * @param dateFilter 
     */
    private void _setDateFilter(String dateFilter)
    {
        this.dateFilter = dateFilter;
    }

    /**
     * Returns the date filter
     * @return 
     */
    private String _getDateFIlter()
    {
        return this.dateFilter;
    }
        
    /**
     * Loads the returning customer objects
     */    
    @Override
    public void load() 
    {
        String queryLoadAllVideos = "SELECT c.customer_id, MIN(r.rental_id) AS rental_id, MIN(r.rental_date_end) AS rental_date_end " +
        "FROM rental r " +
        "INNER JOIN customer c ON r.customer_id = c.customer_id " +
        "WHERE r.is_active = 1 " +
        "GROUP BY c.customer_id " +
        "HAVING MIN(r.rental_date_end) >= '" + this._getDateFIlter() + "'";
           
        try ( //connect to DB
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryLoadAllVideos)) {

            Integer customerId;
            Integer rentalId;
            PlaceholderModel dummyModel = new PlaceholderModel();
            Customer customerItem;
            CustomerReturnRental customerReturningRental;
            
            while(rs.next()) {
                customerId = rs.getInt("customer_id");
                rentalId = rs.getInt("rental_id");
                if(dummyModel.isIntegerNotNull(customerId) && dummyModel.isIntegerNotNull(rentalId)) {
                    customerItem = (Customer)this.createNewCustomerItem();
                    customerItem.loadById(customerId);
                    if(dummyModel.isIntegerNotNull(customerItem.getId())) {
                        customerReturningRental = (CustomerReturnRental)this.createNewItem(customerItem, rentalId);
                        this.addItem(customerId, customerReturningRental);
                    }    
                }
            }
            
            rs.close();
            stmt.close();
            currentCon.close();
        }
        catch (Exception ex) 
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
    }
    
    /**
     * Returns new item object
     * @param customer
     * @param rentalId
     * @return 
     */
    public CustomerReturnRental createNewItem(Customer customer, Integer rentalId) 
    {
        return new CustomerReturnRental(customer, rentalId);
    }
    
    /**
     * Creates a new customer object
     * @return 
     */
    public Customer createNewCustomerItem()
    {
        return new Customer();
    }

    @Override
    public CustomerReturnRental createNewItem() {
        throw new UnsupportedOperationException("Not supported."); 
    }
}
