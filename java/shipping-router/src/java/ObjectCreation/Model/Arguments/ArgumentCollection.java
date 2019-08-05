/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectCreation.Model.Arguments;

import ObjectCreation.Model.Prototypes.CollectionStandard;
import ObjectCreation.Model.Prototypes.ObjectStandard;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author chris
 * @param <H>
 */
public class ArgumentCollection<H extends ArgumentStandard> extends ArgumentStandard<CollectionStandard<H>>
    implements Iterable, Cloneable
{
    public static final String TYPE = "collection";
        
    /**
     * Adds the argument collection
     * @param argumentName
     * @param argumentValue 
     */
    public ArgumentCollection(String argumentName, CollectionStandard argumentValue) {
        super(argumentName, argumentValue);
    }

    /**
     * Adds the argument collection
     * @param argumentValue 
     */
    public ArgumentCollection(CollectionStandard argumentValue) {
        super("allArguments", argumentValue);
    }
    
    /**
     * Returns the type
     * @return 
     */
    @Override
    public String getType()
    {
        return ArgumentCollection.TYPE;
    }
    
    /**
     * Clones the current object
     * @return 
     * @throws java.lang.CloneNotSupportedException 
     */
    public ArgumentCollection cloneCurrent() throws CloneNotSupportedException
    {
        ArrayList<H> currentItems = this.getValue().getItems();
        ArrayList<H> currentItemsCloned;
        currentItemsCloned = new ArrayList<>();
        for(H currentItem : currentItems) {
            currentItemsCloned.add(currentItem);
        }
        
        CollectionStandard<H> newCollectionStandard = new CollectionStandard<>(currentItemsCloned);
        ArgumentCollection newArgumentCollection = new ArgumentCollection(newCollectionStandard);
        
        return newArgumentCollection;
    }
    
        /**
     * Returns the iterator
     * @return 
     */
    @Override
    public Iterator<H> iterator() 
    {
        return (Iterator<H>)this.getValue().iterator();
    }
 
        /**
     * Retrieves the next object
     * @return 
     */
    public H next()
    {
        return this.iterator().next();
    }
    
    /**
     * Returns the matching argument
     * @param argumentName
     * @return 
     * @throws java.lang.CloneNotSupportedException 
     */
    public ArgumentString getStringArgumentByName(String argumentName) throws CloneNotSupportedException
    {
        ArgumentString argumentMatching =  new ArgumentString("placeholder","NONE");
        FilterByArgumentType collectionArgumentStrings;
        collectionArgumentStrings = new FilterByArgumentType(ArgumentString.TYPE, this);
        
        for(Object argument : collectionArgumentStrings.getArgumentsFiltered()) {
            if(argument instanceof ArgumentString) {
                ArgumentString argumentCastAsString = (ArgumentString) argument;
                if(argumentCastAsString.getName().equals(argumentName)) {
                    argumentMatching = argumentCastAsString;   
                }
            }
        }
        
        return argumentMatching;
    }
   
    /**
     * Returns the matching argument
     * @param argumentName
     * @return 
     * @throws java.lang.CloneNotSupportedException 
     */
    public ArgumentInteger getIntegerArgumentByName(String argumentName) throws CloneNotSupportedException
    {
        ArgumentInteger argumentMatching =  new ArgumentInteger("placeholder", 0);
        FilterByArgumentType collectionArgumentIntegers;
        collectionArgumentIntegers = new FilterByArgumentType(ArgumentInteger.TYPE, this);
        
        for(Object argument : collectionArgumentIntegers.getArgumentsFiltered()) {
            if(argument instanceof ArgumentInteger) {
                ArgumentInteger argumentCastAsInteger = (ArgumentInteger) argument;
                if(argumentCastAsInteger.getName().equals(argumentName)) {
                    argumentMatching = argumentCastAsInteger;   
                }
            }
        }
        
        return argumentMatching;
    }
    
    /**
     * Returns the matching argument
     * @param argumentName
     * @return 
     * @throws java.lang.CloneNotSupportedException 
     */
    public ArgumentObject getObjectArgumentByName(String argumentName) throws CloneNotSupportedException
    {
        ArgumentObject argumentMatching =  new ArgumentObject("placeholder", new ObjectStandard());
        FilterByArgumentType collectionArgumentObjects;
        collectionArgumentObjects = new FilterByArgumentType(ArgumentObject.TYPE, this);
        
        for(Object argument : collectionArgumentObjects.getArgumentsFiltered()) {
            if(argument instanceof ArgumentObject) {
                ArgumentObject argumentCastAsObject = (ArgumentObject) argument;
                if(argumentCastAsObject.getName().equals(argumentName)) {
                    argumentMatching = argumentCastAsObject;   
                }
            }
        }
        
        return argumentMatching;
    }
    
    /**
     * Returns the matching argument
     * @param argumentName
     * @return 
     * @throws java.lang.CloneNotSupportedException 
     */
    public ArgumentCollection getCollectionArgumentByName(String argumentName) throws CloneNotSupportedException
    {
        ArgumentCollection argumentMatching =  new ArgumentCollection("placeholder", this.getValue());
        FilterByArgumentType collectionArguments;
        collectionArguments = new FilterByArgumentType(ArgumentCollection.TYPE, this);
        
        for(Object argument : collectionArguments.getArgumentsFiltered()) {
            if(argument instanceof ArgumentCollection) {
                ArgumentCollection argumentCastAsCollection = (ArgumentCollection) argument;
                if(argumentCastAsCollection.getName().equals(argumentName)) {
                    argumentMatching = argumentCastAsCollection;   
                }
            }
        }
        
        return argumentMatching;
    }
    
    /**
     * Removes an item
     * @param item
     */
    public void removeItem(H item)
    {
        this.getValue().remove(item);
    }
}
