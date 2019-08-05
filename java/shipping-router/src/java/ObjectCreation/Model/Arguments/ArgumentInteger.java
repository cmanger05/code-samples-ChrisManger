/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectCreation.Model.Arguments;

/**
 *
 * @author chris
 */
public class ArgumentInteger extends ArgumentStandard<Integer> 
{
    public static final String TYPE = "integer";
        
    /**
     * Constructor
     * @param argumentName
     * @param argumentValue 
     */
    public ArgumentInteger(String argumentName, Integer argumentValue) 
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
        return ArgumentInteger.TYPE;
    }
}
