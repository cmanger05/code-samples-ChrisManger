/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectCreation.Model;

import ObjectCreation.InterfacesCreational.InstanceFactoryBaseInterface;
import ObjectCreation.InterfacesCreational.InstanceFactoryBuilderInterface;
import ObjectCreation.InterfacesCreational.InstanceFactoryStandardInterface;
import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.Model.Base.InstanceStandardPlaceholder;
import java.util.HashMap;

/**
 *
 * @author chris
 */
public class ObjectFactory 
{
    public static ObjectFactory _objectFactory = new ObjectFactory();
    
    public static HashMap<String, InstanceFactoryBaseInterface> _objectPrototypes;
    
    private Boolean _isInitialized = false;
    
    /**
     * Returns the object factory
     * @return 
     */
    public static ObjectFactory getObjectFactory()
    {
        return ObjectFactory._objectFactory;
    }
    
    /**
     * Returns the requested object instance
     * @param objectAbbreviation
     * @param arguments
     * @return 
     */
    public static InstanceFactoryBaseInterface get(String objectAbbreviation, ArgumentCollection arguments)
    {
        InstanceFactoryBaseInterface objectInstance = new InstanceStandardPlaceholder();
        ObjectFactory objectFactory = ObjectFactory.getObjectFactory();
        
        if(!objectFactory.isInitialized()) {
            ObjectMappings objectMappings = new ObjectMappings();
            objectFactory.setCreationalPrototypes(objectMappings.getObjectMappings());
            objectFactory.setIsInitialized(true);
        }
        
        if(objectFactory.factoryContainsObject(objectAbbreviation)) {
            InstanceFactoryBaseInterface objectInstanceRaw = objectFactory.getObjectByAbbreviation(objectAbbreviation);
            if(objectInstanceRaw instanceof InstanceFactoryBaseInterface) {
                objectInstance = objectInstanceRaw.cloneObject();
                if(objectInstance instanceof InstanceFactoryBuilderInterface) {
                    InstanceFactoryBuilderInterface objectInstanceCastAsBuilder = (InstanceFactoryBuilderInterface)objectInstance;
                    objectInstance = objectInstanceCastAsBuilder.buildInstance(arguments);
                } else if(objectInstance instanceof InstanceFactoryStandardInterface) {
                    InstanceFactoryStandardInterface objectInstanceCastStandardInterface = (InstanceFactoryStandardInterface)objectInstance;
                    objectInstanceCastStandardInterface.construct(arguments);
                }                 
            }
        }
        
        return objectInstance;
    }
    
    /**
     * Returns object by abbreviation
     * @param abbreviation
     * @return 
     */
    public InstanceFactoryBaseInterface getObjectByAbbreviation(String abbreviation)
    {
        return ObjectFactory._objectPrototypes.get(abbreviation);
    }
    
    /**
     * Determines if a requested object exists in factory.
     * @param abbreviation
     * @return 
     */
    public Boolean factoryContainsObject(String abbreviation)
    {
        return ObjectFactory._objectPrototypes.containsKey(abbreviation);
    }

    /**
     * @return the _isInitialized
     */
    public Boolean isInitialized() 
    {
        return _isInitialized;
    }

    /**
     * Sets the object prototypes
     * @param prototypes 
     */
    public void setCreationalPrototypes(HashMap<String, InstanceFactoryBaseInterface> prototypes)
    {
        ObjectFactory._objectPrototypes = prototypes;
    }
    
    /**
     * @param _isInitialized the _isInitialized to set
     */
    public void setIsInitialized(Boolean _isInitialized) 
    {
        this._isInitialized = _isInitialized;
    }
}
