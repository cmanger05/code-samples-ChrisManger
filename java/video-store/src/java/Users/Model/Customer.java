/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users.Model;

import Core.Model.AbstractModel;
import Core.Model.DBConnectionsUtil;
import Rentals.Model.Quote;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author chris
 */
public class Customer extends AbstractModel
{
    private Integer customer_id;
    
    private String first_name;
    
    private String last_name;
    
    private Date created_at;
    
    private Integer customer_type_id;
    
    private String customer_type_name;
    
    private Integer video_qty_min;
    
    private Integer video_qty_max;
    
    private Integer video_number_nights;
    
    private Quote _quote;
    
    /**
     * Constructor
     */
    public Customer()
    {
        this._defaultFieldName = "customer_id";
        this._defaultTableName = "customer";
    }
    
    /**
     * Returns the video id
     * @return 
     */
    @Override
    public Integer getId()
    {
        return this.getCustomerId();
    }

    /**
     * @return the customer_id
     */
    public Integer getCustomerId()
    {
        return customer_id;
    }

    /**
     * @return the first_name
     */
    public String getFirstName()
    {
        return first_name;
    }

    /**
     * @return the last_name
     */
    public String getLastName()
    {
        return last_name;
    }

    /**
     * @return the created_at
     */
    public Date getCreatedAt()
    {
        return created_at;
    }

    /**
     * @return the customer_type_id
     */
    public Integer getCustomerTypeId()
    {
        return this.customer_type_id;
    }

        /**
     * @return the customer_type_name
     */
    public String getCustomerTypeName()
    {
        return customer_type_name;
    }

    /**
     * @return the video_qty_min
     */
    public Integer getVideoQtyMin()
    {
        return video_qty_min;
    }

    /**
     * @return the video_qty_max
     */
    public Integer getVideoQtyMax() 
    {
        return video_qty_max;
    }

    /**
     * @return the number_nights
     */
    public Integer getVideoNumberNights() 
    {
        return this.video_number_nights;
    }
    
    /**
     * @param customer_id the customer_id to set
     */
    public void setCustomerId(Integer customer_id)
    {
        this.customer_id = customer_id;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setFirstName(String first_name)
    {
        this.first_name = first_name;
    }

    /**
     * @param last_name the last_name to set
     */
    public void setLastName(String last_name)
    {
        this.last_name = last_name;
    }

    /**
     * @param created_at the created_at to set
     */
    public void setCreatedAt(Date created_at)
    {
        this.created_at = created_at;
    }

    /**
     * @param customer_type_id the customer_type_id to set
     */
    public void setCustomerTypeId(Integer customer_type_id)
    {
        this.customer_type_id = customer_type_id;
    }
  
        /**
     * @param customer_type_name the customer_type_name to set
     */
    public void setCustomerTypeName(String customer_type_name)
    {
        this.customer_type_name = customer_type_name;
    }

    /**
     * @param video_qty_min the video_qty_min to set
     */
    public void setVideoQtyMin(Integer video_qty_min)
    {
        this.video_qty_min = video_qty_min;
    }

    /**
     * @param video_qty_max the video_qty_max to set
     */
    public void setVideoQtyMax(Integer video_qty_max)
    {
        this.video_qty_max = video_qty_max;
    }

    /**
     * @param number_nights the number_nights to set
     */
    public void setVideoNumberNights(Integer number_nights)
    {
        this.video_number_nights = number_nights;
    }
    
    /**
     * Loads by the id field
     * The reason why I'm creating a simple function instead of an abstract function
     * is because there are some models where no data every needs to be loaded.
     * @param idFieldValue
     */
    @Override
    public void loadById(Integer idFieldValue)
    {   
        try 
        {
            String queryLoadIssue = "SELECT * FROM customer c "
                    + "INNER JOIN customer_type t ON c.customer_type_id = t.type_id "
                    + "WHERE c.customer_id = " + idFieldValue + " AND t.type_id IS NOT NULL";
            
            //connect to DB 
            Connection currentCon = DBConnectionsUtil.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(queryLoadIssue);	        
            boolean more = rs.next();
	    if(more) { 
                Integer customerId = rs.getInt("customer_id");
                if(this.isIntegerNotNull(customerId)) {
                    this.setCustomerId(customerId);
                }
                
                String firstName = rs.getString("first_name");
                if(this.isStringNotNull(firstName)) {
                    this.setFirstName(firstName);
                }
                
                String lastName = rs.getString("last_name");
                if(this.isStringNotNull(lastName)) {
                    this.setLastName(lastName);
                }
                          
                Integer customerTypeId = rs.getInt("customer_type_id");
                if(this.isIntegerNotNull(customerTypeId)) {
                    this.setCustomerTypeId(customerTypeId);
                }              
                  
                String typeName = rs.getString("type_name");
                if(this.isStringNotNull(typeName)) {
                    this.setCustomerTypeName(typeName);
                }
                
                Integer videoQtyMin = rs.getInt("video_qty_min");
                if(this.isIntegerNotNull(videoQtyMin)) {
                    this.setVideoQtyMin(videoQtyMin);
                }
                
                Integer videoQtyMax = rs.getInt("video_qty_max");
                if(this.isIntegerNotNull(videoQtyMax)) {
                    this.setVideoQtyMax(videoQtyMax);
                }
                
                Integer numberNights = rs.getInt("video_num_nights");
                if(this.isIntegerNotNull(numberNights)) {
                    this.setVideoNumberNights(numberNights);
                }
                
                this.setIsLoaded(true);
                currentCon.close();
                stmt.close();
                rs.close();
            }
        } 
        catch (Exception ex) 
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
    }
    
    /**
     * Returns the quote object
     * @return 
     */
    public Quote getQuote()
    {
        if(this._quote == null) {
            this._quote = new Quote(this);
        }
        
        return this._quote;
    }
}
