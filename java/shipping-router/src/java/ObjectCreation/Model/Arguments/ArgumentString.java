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
public class ArgumentString extends ArgumentStandard<String> 
{
    public static final String TYPE = "string";
        
    /**
     * Constructor
     * @param argumentName
     * @param argumentValue 
     */
    public ArgumentString(String argumentName, String argumentValue) 
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
        return ArgumentString.TYPE;
    }
}
