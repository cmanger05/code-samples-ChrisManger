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
import Routes.Model.Collection.Trucks;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class Truck extends AbstractModel implements InstanceFactoryStandardInterface
{   
    private Integer _truck_id;
    
    private String _truck_name;
    
    private String _truck_type_name;
    
    private Double _avg_speed;
    
    private Double _holding_capacity;
    
    private Integer _hub_id;
    
    /**
     * Constructor
     */
    public Truck()
    {
        this._defaultFieldName = "truck_id";
        this._defaultTableName = "truck";    
    }
    
    /**
     * Creates an instance of object using the object factory.
     * @return 
     */
    public static Truck createInstance()
    {
        ArrayList<ArgumentStandard> arrayListArguments;
        arrayListArguments = new ArrayList<>();
        ArgumentString shortDescription = new ArgumentString("placeholder","placeholder");
        arrayListArguments.add(shortDescription);
        
        CollectionStandard<ArgumentStandard> standardCollection = new CollectionStandard(arrayListArguments);
        ArgumentCollection argumentCollection = new ArgumentCollection(standardCollection);
        Truck truck;
        truck = (Truck) ObjectFactory.get("Truck", argumentCollection);
        return truck;
    }
    
    /**
     * Returns the id
     * @return 
     */
    @Override
    public Integer getId() 
    {
        return this.getTruckId();
    }
    
    /**
     * No constructor needed
     * @param arguments 
     */
    @Override
    public void construct(ArgumentCollection arguments) {}
    
    /**
     * @return the _truck_id
     */
    public Integer getTruckId() 
    {
        return _truck_id;
    }

    /**
     * @return the _truck_name
     */
    public String getTruckName() 
    {
        return _truck_name;
    }

    /**
     * @return the _truck_type_name
     */
    public String getTruckTypeName() 
    {
        return _truck_type_name;
    }

    /**
     * @return the _avg_speed
     */
    public Double getAvgSpeed() 
    {
        return _avg_speed;
    }

    /**
     * @return the _holding_capacity
     */
    public Double getHoldingCapacity() 
    {
        return _holding_capacity;
    }

    /**
     * @return the _hub_id
     */
    public Integer getHubId() 
    {
        return _hub_id;
    }

    /**
     * @param _truck_id the _truck_id to set
     */
    public void setTruckId(Integer _truck_id) 
    {
        this._truck_id = _truck_id;
    }

    /**
     * @param _truck_name the _truck_name to set
     */
    public void setTruckName(String _truck_name) 
    {
        this._truck_name = _truck_name;
    }

    /**
     * @param _truck_type_name the _truck_type_name to set
     */
    public void setTruckTypeName(String _truck_type_name) 
    {
        this._truck_type_name = _truck_type_name;
    }

    /**
     * @param _avg_speed the _avg_speed to set
     */
    public void setAvgSpeed(Double _avg_speed) 
    {
        this._avg_speed = _avg_speed;
    }

    /**
     * @param _holding_capacity the _holding_capacity to set
     */
    public void setHoldingCapacity(Double _holding_capacity) 
    {
        this._holding_capacity = _holding_capacity;
    }

    /**
     * @param _hub_id the _hub_id to set
     */
    public void setHubId(Integer _hub_id) 
    {
        this._hub_id = _hub_id;
    }
    
    /**
     * Loads an instance
     * @param idFieldValue 
     */
    @Override
    public void loadById(Integer idFieldValue)
    {  
        /* Do not use a factory to create this object because it uses a generic*/
        Trucks trucksCollection = new Trucks();
        trucksCollection.loadSingleInstance(idFieldValue);
        
        Truck truck = trucksCollection.next();
        if(this.isIntegerNotNull(truck.getId())) {
            this.setTruckId(truck.getTruckId());
            this.setTruckName(truck.getTruckName());
            this.setTruckTypeName(truck.getTruckTypeName());
            this.setAvgSpeed(truck.getAvgSpeed());
            this.setHoldingCapacity(truck.getHoldingCapacity());
            this.setHubId(truck.getHubId());
        }    
    }
    
    /**
     * Clones current object
     * @return 
     */
    @Override
    public Truck cloneObject() {
        return new Truck();
    }
}
