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
import Routes.Model.Collection.Stops;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class Stop extends AbstractModel implements InstanceFactoryStandardInterface
{
    private Integer _stop_id;
    
    private Integer _type_id;
    
    private String _stop_type_name;
    
    private String _stop_name;
    
    private String _street_address;
    
    private String _street_address2;
    
    private String _city;
    
    private String _region;
    
    private String _postcode;
    
    /**
     * Constructor
     */
    public Stop()
    {
        this._defaultFieldName = "stop_id";
        this._defaultTableName = "stop";
    }
    
    /**
     * No constructor needed
     * @param arguments 
     */
    @Override
    public void construct(ArgumentCollection arguments) {}
    
        /**
     * Returns the id
     * @return 
     */
    @Override
    public Integer getId() {
        return this.getStopId();
    }
    
    /**
     * Creates an instance of current object
     * @return 
     */
    public static Stop createInstance()
    {
        ArrayList<ArgumentStandard> arrayListArguments;
        arrayListArguments = new ArrayList<>();
        ArgumentString shortDescription = new ArgumentString("placeholder","placeholder");
        arrayListArguments.add(shortDescription);
        
        CollectionStandard<ArgumentStandard> standardCollection = new CollectionStandard(arrayListArguments);
        ArgumentCollection argumentCollection = new ArgumentCollection(standardCollection);
        Stop stop;
        stop = (Stop) ObjectFactory.get("Stop", argumentCollection);
        return stop;
    }
    
    /**
     * @return the _stop_id
     */
    public Integer getStopId() 
    {
        return _stop_id;
    }

    /**
     * @return the _type_id
     */
    public Integer getTypeId() 
    {
        return _type_id;
    }

    /**
     * @return the _stop_name
     */
    public String getStopName() 
    {
        return _stop_name;
    }
    
    /**
     * @return the _stop_type_name
     */
    public String getStopTypeName() 
    {
        return _stop_type_name;
    }
 
        /**
     * @return the _street_address
     */
    public String getStreetAddress() 
    {
        return _street_address;
    }

    /**
     * @return the _street_address2
     */
    public String getStreetAddress2() 
    {
        return _street_address2;
    }

    /**
     * @return the _city
     */
    public String getCity() 
    {
        return _city;
    }

    /**
     * @return the _region
     */
    public String getRegion() 
    {
        return _region;
    }

    /**
     * @return the _postcode
     */
    public String getPostcode() 
    {
        return _postcode;
    }
    
    /**
     * @param _stop_id the _stop_id to set
     */
    public void setStopId(Integer _stop_id) 
    {
        this._stop_id = _stop_id;
    }

    /**
     * @param _type_id the _type_id to set
     */
    public void setTypeId(Integer _type_id) 
    {
        this._type_id = _type_id;
    }

    /**
     * @param _stop_name the _stop_name to set
     */
    public void setStopName(String _stop_name) 
    {
        this._stop_name = _stop_name;
    }

    /**
     * @param _stop_type_name the _stop_type_name to set
     */
    public void setStopTypeName(String _stop_type_name) 
    {
        this._stop_type_name = _stop_type_name;
    }
   
        /**
     * @param _street_address the _street_address to set
     */
    public void setStreetAddress(String _street_address) 
    {
        this._street_address = _street_address;
    }

    /**
     * @param _street_address2 the _street_address2 to set
     */
    public void setStreetAddress2(String _street_address2) 
    {
        this._street_address2 = _street_address2;
    }

    /**
     * @param _city the _city to set
     */
    public void setCity(String _city) 
    {
        this._city = _city;
    }

    /**
     * @param _region the _region to set
     */
    public void setRegion(String _region) 
    {
        this._region = _region;
    }

    /**
     * @param _postcode the _postcode to set
     */
    public void setPostcode(String _postcode) 
    {
        this._postcode = _postcode;
    }
    
    /**
     * Loads an instance
     * @param idFieldValue 
     */
    @Override
    public void loadById(Integer idFieldValue)
    {  
        /* Do not use a factory to create this object because it uses a generic*/
        Stops stopsCollection = new Stops();
        stopsCollection.loadSingleInstance(idFieldValue);
        
        Stop stop = stopsCollection.next();
        if(this.isIntegerNotNull(stop.getId())) {
            this.setStopId(stop.getStopId());
            this.setTypeId(stop.getTypeId());
            this.setStopTypeName(stop.getStopTypeName());
            this.setStopName(stop.getStopName());
            this.setStreetAddress(stop.getStreetAddress());
            this.setStreetAddress2(stop.getStreetAddress2());
            this.setCity(stop.getCity());
            this.setRegion(stop.getRegion());
            this.setPostcode(stop.getPostcode());
        }    
    }
    
    /**
     * Performs clone
     * @return 
     */
    @Override
    public Stop cloneObject() 
    {
        return new Stop();
    }
}
