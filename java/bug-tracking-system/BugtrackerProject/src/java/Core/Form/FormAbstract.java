/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Form;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chris
 */
public abstract class FormAbstract {
    
    private final ArrayList<String> _errors = new ArrayList<>();
    
    private boolean _isValid = true;

    protected abstract void process(HydratorAbstract hydrator, HttpServletRequest request);

    /**
     * @return the _errors
     */
    public ArrayList<String> getErrors() {
        return _errors;
    }

    /**
     * @param error
     */
    public void addError(String error) {
        this._errors.add(error);
        this.setIsValid(false);
    }

    /**
     * @return the _isValid
     */
    public boolean isValid() {
        return _isValid;
    }

    /**
     * @param _isValid the _isValid to set
     */
    public void setIsValid(boolean _isValid) {
        this._isValid = _isValid;
    }
}
