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
public abstract class AbstractArgumentStandard<G> extends ObjectStandard
{
    private String _argumentName;
    
    private G _argumentValue;
    
    private AbstractArgumentStandard() {}
    
    /**
     * Constructor forces user to specify argument name and value
     * @param argumentName
     * @param argumentValue 
     */
    public AbstractArgumentStandard(String argumentName, G argumentValue)
    {
        this._setName(argumentName);
        this._setValue(argumentValue);
    }

    /**
     * @return the _argumentName
     */
    public String getName() 
    {
        return this._argumentName;
    }

    /**
     * @return the _argumentValue
     */
    public G getValue() 
    {
        return this._argumentValue;
    }

    /**
     * @param _argumentName the _argumentName to set
     */
    private void _setName(String _argumentName) 
    {
        this._argumentName = _argumentName;
    }

    /**
     * @param _argumentValue the _argumentValue to set
     */
    private void _setValue(G _argumentValue) 
    {
        this._argumentValue = _argumentValue;
    }
    
    public abstract String getType();
}
