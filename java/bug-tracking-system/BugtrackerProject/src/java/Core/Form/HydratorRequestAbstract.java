/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Form;

import Core.Model.AbstractModel;
import java.util.HashMap;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chris
 */
public abstract class HydratorRequestAbstract {
    private HttpServletRequest _request;
    
    private HttpServlet _controller;
   
    protected HashMap<String,String> mapDbFieldNameToRequestAttributeName = new HashMap();
    
    protected HashMap<String,String> mapDbFieldNameToLabel = new HashMap();
    
    /**
     * Constructor
     * @param request 
     * @param controller 
     */
    public HydratorRequestAbstract(HttpServletRequest request, HttpServlet controller)
    {
        this._request = request;
        this._controller = controller;
    }
    
    /**
     * Adds a database field name to request attribute name mapping
     * @param fieldName
     * @param attributeName 
     */
    protected void _mapDbFieldNameToRequestAttributeName(String fieldName, String attributeName)
    {
        this.mapDbFieldNameToRequestAttributeName.put(fieldName, attributeName);
    }
   
    /**
     * Returns the db field names to request attribute array
     * @return 
     */
    protected HashMap<String,String> _getFieldNameToLabelCollection()
    {
        return this.mapDbFieldNameToLabel;
    }
    
    /**
     * Returns the request attributes name
     * @param dbFieldName
     * @return 
     */
    protected String _getRequestAttributeNameByDbFieldName(String dbFieldName)
    {
        String requestAttributeName = "";
        
        Object requestAttributeNameAsObject = this.mapDbFieldNameToRequestAttributeName.get(dbFieldName);
        if(requestAttributeNameAsObject != null) {
            String requestAttributeNameCandidate = requestAttributeNameAsObject.toString();
            if(requestAttributeNameCandidate != null) {
                requestAttributeName = requestAttributeNameCandidate;
            }
        }
        
        return requestAttributeName;
    }
 
    /**
     * Maps the database field name to a label
     * @param fieldName
     * @param labelField 
     */
    protected void _mapFormFieldNameToFormFieldLabel(String fieldName, String labelField)
    {
        this.mapDbFieldNameToLabel.put(fieldName, labelField);
        String formFieldLabelName = fieldName + "Label";
        this._getController().getServletContext().setAttribute(formFieldLabelName, labelField);
    }
    
    /**
     * Returns the form field label based on the db field name provided.
     * @param formFieldName
     * @return 
     */
    public String getFormFieldLabelByFormFieldName(String formFieldName)
    {
        String formFieldLabel = "";
        
        Object requestAttributeNameAsObject = this.mapDbFieldNameToLabel.get(formFieldName);
        if(requestAttributeNameAsObject != null) {
            String requestAttributeNameCandidate = requestAttributeNameAsObject.toString();
            if(requestAttributeNameCandidate != null) {
                formFieldLabel = requestAttributeNameCandidate;
            }
        }
        
        return formFieldLabel; 
    }
            
    /**
     * Used to hydrate request object
     * @param model 
     */
    public abstract void hydrate(AbstractModel model);

    /**
     * @return the _request
     */
    protected HttpServletRequest _getRequest() {
        return _request;
    }

    /**
     * @param _request the _request to set
     */
    protected void _setRequest(HttpServletRequest _request) {
        this._request = _request;
    }

    /**
     * @return the _controller
     */
    protected HttpServlet _getController() {
        return _controller;
    }

    /**
     * @param _controller the _controller to set
     */
    protected void _setController(HttpServlet _controller) {
        this._controller = _controller;
    }
    
    /**
     * Flushes all the request variables
     */
    public void flushAllRequestVars()
    {
        HashMap<String,String> dbFieldToRequestMap = this._getFieldNameToLabelCollection();
        for(String requestAttributeName : dbFieldToRequestMap.keySet()) {
            this._getController().getServletContext().setAttribute(requestAttributeName, "");
        }  
    }
}
