/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectCreation.Model.Arguments;

import ObjectCreation.Model.Prototypes.ObjectStandard;

/**
 *
 * @author chris
 * @param <G>
 */
public class ArgumentObject<G extends ObjectStandard> extends ArgumentStandard<G> 
{
    public static final String TYPE = "object";
        
    /**
     * Constructor
     * @param argumentName
     * @param argumentValue 
     */
    public ArgumentObject(String argumentName, G argumentValue) 
    {
        super(argumentName, argumentValue);
    }
    
    /**
     * Returns the type
     * @return 
     */
    @Override
    public String getType()
    {
        return ArgumentObject.TYPE;
    }
}
