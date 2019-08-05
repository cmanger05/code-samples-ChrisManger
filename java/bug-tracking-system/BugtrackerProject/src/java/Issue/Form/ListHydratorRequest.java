/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Issue.Form;

import Core.Form.HydratorRequestAbstract;
import Core.Model.AbstractModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chris
 */
public class ListHydratorRequest extends HydratorRequestAbstract {

    public ListHydratorRequest(HttpServletRequest request, HttpServlet controller) {
        super(request, controller);
        this._mapDbFieldNameToRequestAttributeName("project_id", "project");
        this._mapDbFieldNameToRequestAttributeName("assignee_id", "assignee");
    }

    /**
     * Nothing needs to be hydrated because ListHydrator already sets the selected options 
     * into form variables.  This only needs to be run if a form uses get to load a row from the database.  This never occurs so
     * therefore this function is blank.
     * @param model 
     */
    @Override
    public void hydrate(AbstractModel model) {}
    
}
