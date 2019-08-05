/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectCreation.Model.Arguments;

/**
 *
 * @author chris
 * @param <G>
 */
public class ArgumentStandard<G> extends AbstractArgumentStandard<G> 
{
    private final String TYPE = "standard";
    /**
     * Constructor
     * @param argumentName
     * @param argumentValue 
     */
    public ArgumentStandard(String argumentName, G argumentValue) {
        super(argumentName, argumentValue);
    }
    
    /**
     * Returns the type
     * @return 
     */
    @Override
    public String getType()
    {
        return this.TYPE;
    }
}
