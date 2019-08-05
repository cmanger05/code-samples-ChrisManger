/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Form;

import Core.Controller.AbstractFormController;
import Core.Model.AbstractModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chris
 */
public abstract class HydratorAbstract {
    
    private FormAbstract _form;
    
    private AbstractFormController _controller;
    
    private AbstractModel _model;
    
    private HttpServletRequest _request;
    
    private HydratorRequestAbstract _requestHydrator;
    
    /**
     * Making it so that class cannot be instantiated using default constructor. 
     */
    private HydratorAbstract() {}
    
    /**
     * These are required arguments that must be passed in to the constructor
     * @param form
     * @param model
     * @param controller 
     * @param request 
     * @param requestHydrator 
     */
    public HydratorAbstract(FormAbstract form, AbstractModel model, 
            AbstractFormController controller, HttpServletRequest request, HydratorRequestAbstract requestHydrator)
    {
        this._form = form;
        this._model = model;
        this._controller = controller;
        this._request = request;
        this._requestHydrator = requestHydrator;
    }
    
    /**
     * Hydrates the model
     * @return 
     */
    public abstract AbstractModel hydrate();
 
    /**
     * Determines if a value is an integer
     * @param attributeName
     * @param isRequired
     * @return 
     */
    public boolean isAttributeInteger(String attributeName, boolean isRequired)
    {
        boolean isInteger = false;
        String attributeValueRaw;
        attributeValueRaw = this.getPostedStringValue(attributeName);
        String formFieldLabel = this._getRequestHydrator().getFormFieldLabelByFormFieldName(attributeName);

        try {
            if(isRequired && attributeValueRaw.isEmpty()) {
                this.getForm().addError("The field called \"" + formFieldLabel + " is required");
            } else if((isRequired && !attributeValueRaw.isEmpty()) || (!isRequired && !attributeValueRaw.isEmpty())) {
                Integer attributeValue = Integer.parseInt(attributeValueRaw);
                isInteger = true;
            }
        } catch(NumberFormatException e) {
            this.getForm().addError("The field called \"" + formFieldLabel + " is invalid");
        }
        
        return isInteger;
    }
  
    /**
     * Determines if a string value can be converted to an integer
     * @param valueCandidate
     * @return 
     */
    public boolean isValueInteger(String valueCandidate)
    {
        boolean isInteger = false;

        try {
            Integer attributeValue = Integer.parseInt(valueCandidate);
            isInteger = true;
        } catch(NumberFormatException e) {
            
        }
        
        return isInteger;
    }
    
    /**
     * Determines if the string is empty
     * @param attributeName
     * @param isRequired
     * @return 
     */
    public boolean isAttributeEmptyString(String attributeName, boolean isRequired)
    {
        boolean isEmptyString = false;
        
        try {
            String attributeValueRaw = this.getPostedStringValue(attributeName);
            if(attributeValueRaw.isEmpty()) {
                isEmptyString = true;
                if(isRequired) {
                    String formFieldLabel = this._getRequestHydrator().getFormFieldLabelByFormFieldName(attributeName);
                    this.getForm().addError("The field called \"" + formFieldLabel + "\" is required");
                }
            }
        } catch(Exception e) {
            String exception = e.getMessage();
        }
        
        return isEmptyString;
    }
    
    /**
     * Returns the posted value
     * @param attributeName
     * @return 
     */
    public String getPostedStringValue(String attributeName)
    {
        String postedStringValue = "";
        
        try {
            String postedStringCandidate1 = this.getRequest().getParameter(attributeName);
            if(postedStringCandidate1 != null & !postedStringCandidate1.isEmpty()) {
                postedStringValue = postedStringCandidate1;
            }
        } catch(Exception e) {
            String exception = e.getMessage();
        }
        
        return postedStringValue;
    }
    
    /**
     * Determines if the date is valid
     * @param dateAttributeName
     * @param isRequired
     * @return 
     */
    public boolean isAttributeValidDate(String dateAttributeName, boolean isRequired)
    {
        boolean isValidDate = true;
        
        String dateCandidate = this.getPostedStringValue(dateAttributeName);
        if(!dateCandidate.isEmpty() && !dateCandidate.equals("0000-00-00 00:00:00")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(dateCandidate.trim());
            } catch(ParseException e) {
                isValidDate = false;
                String formFieldLabel = this._getRequestHydrator().getFormFieldLabelByFormFieldName(dateAttributeName);
                this.getForm().addError("The field called \"" + formFieldLabel + "\" is invalid.  Please use the specified date format.");
            }
        } else if(isRequired) {
            String formFieldLabel = this._getRequestHydrator().getFormFieldLabelByFormFieldName(dateAttributeName);    
            this.getForm().addError("The field called \"" + formFieldLabel + "\" is required.");
        }
        
        return isValidDate;
    }
    
    /**
     * Converts a string to an integer
     * @param valueToConvert
     * @return 
     */
    public Integer convertToInteger(String valueToConvert)
    {
        Integer convertedValue = 0;

        try {
            convertedValue = Integer.parseInt(valueToConvert);
        } catch(NumberFormatException e) {

        }
        
        return convertedValue;      
    }
    
    /**
     * Returns the attributes value
     * @param attributeName
     * @param defaultValue
     * @return 
     */
    public String getAttributeValue(String attributeName, String defaultValue)
    {
        String attributeValue;
        attributeValue= this.getPostedStringValue(attributeName);
        if(attributeValue.isEmpty() && !defaultValue.isEmpty()) {
            attributeValue = defaultValue;
        }
        
        return attributeValue;
    }
  
    /**
     * Hydrates the request object, that way when form is posted, posted values appear.
     * @param attributeName
     * @param attributeValue 
     */
    public void hydrateRequestAttribute(String attributeName, Object attributeValue)
    {
        this.getRequest().setAttribute(attributeName, attributeValue);
        this.getController().getServletContext().setAttribute(attributeName, attributeValue);
    }
    
    /**
     * @return the _form
     */
    public FormAbstract getForm() {
        return _form;
    }

    /**
     * @return the _controller
     */
    public AbstractFormController getController() {
        return _controller;
    }

    /**
     * @return the _model
     */
    protected AbstractModel _getModel() {
        return _model;
    }

    /**
     * @return the _request
     */
    public HttpServletRequest getRequest() {
        return _request;
    }
    
    /**
     * Returns the request hydrator
     * @return 
     */
    protected HydratorRequestAbstract _getRequestHydrator()
    {
        return this._requestHydrator;
    }

    /**
     * @param _request the _request to set
     */
    public void setRequest(HttpServletRequest _request) {
        this._request = _request;
    }
}
