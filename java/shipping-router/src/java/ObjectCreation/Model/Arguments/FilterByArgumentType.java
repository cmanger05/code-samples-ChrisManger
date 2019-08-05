/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectCreation.Model.Arguments;

import java.util.ArrayList;


/**
 *
 * @author chris
 */
public class FilterByArgumentType 
{
    private String _argumentTypeFilterBy;
    
    private ArgumentCollection _arguments;
    
    private ArgumentCollection _argumentsFiltered;
    
    private FilterByArgumentType() {}
    
    /**
     * Constructor
     * @param argumentTypeFilterBy 
     * @param arguments 
     * @throws java.lang.CloneNotSupportedException 
     */
    public FilterByArgumentType(String argumentTypeFilterBy, ArgumentCollection arguments) throws CloneNotSupportedException
    {
        this._argumentTypeFilterBy = argumentTypeFilterBy;
        this._arguments = arguments;
        this._filter();
    }
    
    /**
     * Filters arguments and matches only those with same type as requested filter
     * @throws CloneNotSupportedException 
     */
    private void _filter() throws CloneNotSupportedException
    {
        ArgumentCollection argumentsOriginalCollection = this._getArguments();
        ArgumentCollection argumentCollectionCopy = (ArgumentCollection) argumentsOriginalCollection.cloneCurrent();
        String filterType = this._getArgumentTypeFilterBy();
        ArrayList<ArgumentStandard> argumentsToRemove = new ArrayList<>();
        
        for(Object argumentOriginal: argumentsOriginalCollection) {
            if(argumentOriginal instanceof ArgumentStandard) {
                ArgumentStandard argumentOriginalCasted = (ArgumentStandard) argumentOriginal;
                if(!filterType.equals(argumentOriginalCasted.getType())) {
                   argumentsToRemove.add(argumentOriginalCasted);
                }
            }
        }
        
        for(ArgumentStandard argumentToRemove : argumentsToRemove) {
           argumentCollectionCopy.removeItem(argumentToRemove);
        }

        this._setArgumentsFiltered(argumentCollectionCopy);
    }

    /**
     * @return the _argumentTypeFilterBy
     */
    private String _getArgumentTypeFilterBy() 
    {
        return _argumentTypeFilterBy;
    }

    /**
     * @return the _arguments
     */
    private ArgumentCollection _getArguments() 
    {
        return _arguments;
    }

    /**
     * @return the _argumentsFiltered
     */
    public ArgumentCollection getArgumentsFiltered() 
    {
        return _argumentsFiltered;
    }

    /**
     * @param _argumentsFiltered the _argumentsFiltered to set
     */
    private void _setArgumentsFiltered(ArgumentCollection _argumentsFiltered) 
    {
        this._argumentsFiltered = _argumentsFiltered;
    }
}
