/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routes.Model;

import Core.Model.AbstractModel;
import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.Model.Arguments.ArgumentStandard;
import ObjectCreation.Model.Arguments.ArgumentString;
import ObjectCreation.InterfacesCreational.InstanceFactoryStandardInterface;
import ObjectCreation.Model.ObjectFactory;
import ObjectCreation.Model.Prototypes.CollectionStandard;
import Routes.Model.Collection.PendingRouteCreations;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class PendingRouteCreation extends AbstractModel implements InstanceFactoryStandardInterface
{
    private Integer _pending_route_id;
    
    private Date _date_created;
    
    private Integer _store_id;
    
    private Double _qty_requested;
    
    private Integer _is_created;

    /**
     * Constructor
     */
    public PendingRouteCreation()
    {
        this._defaultFieldName = "pending_route_id";
        this._defaultTableName = "pending_route_creation";
    }
    
    /**
     * Creates an instance of this object
     * @return 
     */
    public static PendingRouteCreation createInstance()
    {
        ArrayList<ArgumentStandard> arrayListArguments;
        arrayListArguments = new ArrayList<>();
        ArgumentString shortDescription = new ArgumentString("placeholder","placeholder");
        arrayListArguments.add(shortDescription);
        
        CollectionStandard<ArgumentStandard> standardCollection = new CollectionStandard(arrayListArguments);
        ArgumentCollection argumentCollection = new ArgumentCollection(standardCollection);
        PendingRouteCreation pendingRouteCreation;
        pendingRouteCreation = (PendingRouteCreation) ObjectFactory.get("PendingRouteCreation", argumentCollection);
        return pendingRouteCreation;
    }
    
    /**
     * Constructor blank because not needed.
     * @param arguments 
     */
    @Override
    public void construct(ArgumentCollection arguments) {}
    
    /**
     * Returns the id
     * @return 
     */
    @Override
    public Integer getId() 
    {
        return this.getPendingRouteId();
    }
    
    /**
     * @return the _pending_route_id
     */
    public Integer getPendingRouteId() 
    {
        return _pending_route_id;
    }

    /**
     * @return the _date_created
     */
    public Date getDateCreated() 
    {
        return _date_created;
    }

    /**
     * @return the _store_id
     */
    public Integer getStoreId() 
    {
        return _store_id;
    }

    /**
     * @return the _qty_requested
     */
    public Double getQtyRequested() 
    {
        return _qty_requested;
    }

    /**
     * @return the _is_created
     */
    public Integer getIsCreated() 
    {
        return _is_created;
    }

    /**
     * @param _pending_route_id the _pending_route_id to set
     */
    public void setPendingRouteId(Integer _pending_route_id) 
    {
        this._pending_route_id = _pending_route_id;
    }

    /**
     * @param _date_created the _date_created to set
     */
    public void setDateCreated(Date _date_created) 
    {
        this._date_created = _date_created;
    }

    /**
     * @param _store_id the _store_id to set
     */
    public void setStoreId(Integer _store_id) 
    {
        this._store_id = _store_id;
    }

    /**
     * @param _qty_requested the _qty_requested to set
     */
    public void setQtyRequested(Double _qty_requested) 
    {
        this._qty_requested = _qty_requested;
    }

    /**
     * @param _is_created the _is_created to set
     */
    public void setIsCreated(Integer _is_created) 
    {
        this._is_created = _is_created;
    }

        /**
     * Loads by the id field
     * It's easier to accomplish this by using an existing collection, this is what is done below
     * @param idFieldValue
     */
    @Override
    public void loadById(Integer idFieldValue)
    {  
        /* Do not use a factory to create this object because it uses a generic*/
        PendingRouteCreations pendingRouteCreationCollection = new PendingRouteCreations();
        pendingRouteCreationCollection.loadSingleInstance(idFieldValue);
        
        PendingRouteCreation pendingRouteCreation = pendingRouteCreationCollection.next();
        if(this.isIntegerNotNull(pendingRouteCreation.getId())) {
            this.setPendingRouteId(pendingRouteCreation.getPendingRouteId());
            this.setStoreId(pendingRouteCreation.getStoreId());
            this.setDateCreated(pendingRouteCreation.getDateCreated());
            this.setQtyRequested(pendingRouteCreation.getQtyRequested());
            this.setIsCreated(pendingRouteCreation.getIsCreated());
        }    
    }
    
    /**
     * Clones the object
     * @return 
     */
    @Override
    public PendingRouteCreation cloneObject() 
    {
        return new PendingRouteCreation();
    }
}
