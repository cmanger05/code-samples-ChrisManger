/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users.Model.Collection;

import Catalog.Model.Collection.VideoCollections.InventoryInStore;
import Catalog.Model.VideoStockItemInStore;
import Core.Model.AbstractCollection;
import Core.Model.CollectionItemValidator;
import Core.Model.DBConnectionsUtil;
import Core.Model.PlaceholderModel;
import Users.Model.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public class Customers extends AbstractCollection<Customer> {
    
        @Override
    public void load() 
    {
        String whereClause = this._renderWhereClause();
        String queryLoadAllVideos = "SELECT * FROM customer c "
                    + "INNER JOIN customer_type t ON c.customer_type_id = t.type_id "
                    + "WHERE t.type_id IS NOT NULL" + whereClause;
        
        InventoryInStore inventoryCollection = new InventoryInStore();
        inventoryCollection.load();
        PlaceholderModel placeholderModel = new PlaceholderModel();
        
        try ( //connect to DB
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryLoadAllVideos)) {

            Customer customerItem;
            Integer customerId;
            String firstName;
            String lastName;
            Integer customerTypeId;
            String typeName;

            CollectionItemValidator itemValidator;
            VideoStockItemInStore inventoryItem;

            while(rs.next()) {
                customerItem = (Customer)this.createNewItem();
                itemValidator = (CollectionItemValidator)this.createNewValidator(customerItem);

                customerId = rs.getInt("customer_id");
                if(itemValidator.isIntegerNotNull(customerId, true)) {
                    customerItem.setCustomerId(customerId);
                }
                
                firstName = rs.getString("first_name");
                if(itemValidator.isStringNotNull(firstName, true)) {
                    customerItem.setFirstName(firstName);
                }
                
                lastName = rs.getString("last_name");
                if(itemValidator.isStringNotNull(lastName, true)) {
                    customerItem.setLastName(lastName);
                }
                          
                customerTypeId = rs.getInt("customer_type_id");
                if(itemValidator.isIntegerNotNull(customerTypeId, true)) {
                    customerItem.setCustomerTypeId(customerTypeId);
                }              
                  
                typeName = rs.getString("type_name");
                if(itemValidator.isStringNotNull(typeName, true)) {
                    customerItem.setCustomerTypeName(typeName);
                }
                
                Integer videoQtyMin = rs.getInt("video_qty_min");
                if(itemValidator.isIntegerNotNull(videoQtyMin, true)) {
                    customerItem.setVideoQtyMin(videoQtyMin);
                }
                
                Integer videoQtyMax = rs.getInt("video_qty_max");
                if(itemValidator.isIntegerNotNull(videoQtyMax, true)) {
                    customerItem.setVideoQtyMax(videoQtyMax);
                }
                
                Integer numberNights = rs.getInt("video_num_nights");
                if(itemValidator.isIntegerNotNull(numberNights, true)) {
                    customerItem.setVideoNumberNights(numberNights);
                }
                
                if(itemValidator.isValid()) {
                    this.addItem(customerId, customerItem);
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

        this.applyResultsFilters();
    }
    
    @Override
    public Customer createNewItem() {
        return new Customer();
    }
}
