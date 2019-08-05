/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Model;

import java.util.Date;

/**
 *
 * @author chris
 */
public class CollectionItemValidator {
    private final AbstractModel _dataModel;
    
    private Boolean _isValid = true;
    
    public Boolean isValid()
    {
        return this._isValid;
    }
    
    /**
     * Marks object as invalid.
     */
    public void markObjectAsInvalid()
    {
        this._isValid = false;
    }
    
    
    /**
     * 
     * @param dataModel 
     */
    public CollectionItemValidator(AbstractModel dataModel)
    {
        this._dataModel = dataModel; 
    }
    
    /**
     * Determines if double is not null, and validates
     * @param number
     * @param isRequired
     * @return 
     */
    public boolean isDoubleNotNull(Double number, Boolean isRequired)
    {
        boolean isDoubleNotNull = this._dataModel.isDoubleNotNull(number);
        if(!isDoubleNotNull && isRequired) {
            this.markObjectAsInvalid();
        }
             
        return isDoubleNotNull;
    }
    
    /**
     * Determines if the string is not null
     * @param stringValue
     * @param isRequired
     * @return 
     */
    public boolean isStringNotNull(String stringValue, Boolean isRequired)
    {
        boolean isStringNotNull = this._dataModel.isStringNotNull(stringValue);
        if(!isStringNotNull && isRequired) {
            this.markObjectAsInvalid();
        }
             
        return isStringNotNull;
    }
    
    /**
     * Determines if integer is not null
     * @param integerValue
     * @param isRequired
     * @return 
     */
    public boolean isIntegerNotNull(Integer integerValue, Boolean isRequired)
    {
        boolean isIntegerNotNull = this._dataModel.isIntegerNotNull(integerValue);
        if(!isIntegerNotNull && isRequired) {
            this.markObjectAsInvalid();
        }
             
        return isIntegerNotNull;
    }
    
        /**
     * Determines if integer is not null
     * @param booleanValue
     * @param isRequired
     * @return 
     */
    public boolean isBooleanValid(Boolean booleanValue, Boolean isRequired)
    {
        boolean isBooleanNotNull = this._dataModel.isBooleanValid(booleanValue);
        if(!isBooleanNotNull && isRequired) {
            this.markObjectAsInvalid();
        }
             
        return isBooleanNotNull;
    }
    
    /**
     * Determines if the date is not empty
     * @param dateValue
     * @param isRequired
     * @return 
     */
    public boolean isDateNotEmpty(Date dateValue, Boolean isRequired)
    {
        boolean isDateNotEmpty = this._dataModel.isDateNotEmpty(dateValue);
        if(!isDateNotEmpty && isRequired) {
            this.markObjectAsInvalid();
        }
             
        return isDateNotEmpty;
    }
}
