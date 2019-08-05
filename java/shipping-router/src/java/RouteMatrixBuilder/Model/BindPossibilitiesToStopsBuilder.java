/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouteMatrixBuilder.Model;

import ObjectCreation.InterfacesCreational.InstanceFactoryBaseInterface;
import ObjectCreation.InterfacesCreational.InstanceFactoryBuilderInterface;
import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.Model.Arguments.ArgumentInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class BindPossibilitiesToStopsBuilder implements InstanceFactoryBuilderInterface {

    private static BindPossibilitiesToStops _bindPossibilitiesToStops;
    
    /**
     * Returns the object that binds possibilities to their corresponding stops.
     * @param arguments
     * @return 
     */
    @Override
    public InstanceFactoryBaseInterface buildInstance(ArgumentCollection arguments) 
    {    
        if(BindPossibilitiesToStopsBuilder._bindPossibilitiesToStops == null) {
            try {
                ArgumentInteger argumentHubId = arguments.getIntegerArgumentByName("hub_id");
                Integer hubId = argumentHubId.getValue();
                BindPossibilitiesToStops bindPossibilitiesToStops = new BindPossibilitiesToStops();
                bindPossibilitiesToStops.load(hubId);
                BindPossibilitiesToStopsBuilder._bindPossibilitiesToStops = bindPossibilitiesToStops;
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(BindPossibilitiesToStopsBuilder.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }

        return BindPossibilitiesToStopsBuilder._bindPossibilitiesToStops;
    }
    
    /**
     * Clones current object
     * @return 
     */
    @Override
    public BindPossibilitiesToStopsBuilder cloneObject() 
    {
        return new BindPossibilitiesToStopsBuilder();
    }
}
