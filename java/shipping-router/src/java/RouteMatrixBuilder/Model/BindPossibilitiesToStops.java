/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouteMatrixBuilder.Model;

import Core.Model.AbstractCollection;
import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.InterfacesCreational.InstanceFactoryBaseInterface;
import ObjectCreation.Model.Arguments.ArgumentInteger;
import ObjectCreation.Model.Arguments.ArgumentStandard;
import ObjectCreation.Model.ObjectFactory;
import ObjectCreation.Model.Prototypes.CollectionStandard;
import Routes.Model.Collection.Stops;
import Routes.Model.Stop;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public final class BindPossibilitiesToStops extends AbstractCollection<Stop> 
    implements InstanceFactoryBaseInterface
{        
    /**
     * Loads all stops into this class and assigns them to a key that equals their position
     * @param hubId
     */
    public void load(Integer hubId) 
    {
        Stops stopsCollection = new Stops();
        stopsCollection.load();
        
        Stop hub = stopsCollection.getItemByKey(hubId);
        this.addItem(5, hub);
        stopsCollection.removeItem(hub);
        
        Integer stopItemCounter = 1;
        for(Stop stop : stopsCollection) {
            if(stopItemCounter > 4) {
                continue;
            }
            this.addItem(stopItemCounter, stop);
            stopItemCounter++;
        }
    }
 
    /**
     * Traditional means of loading not available for this object, because Hub Id MUST be supplied to get a correct output!
     */
    @Override
    public void load() {}
    
    /**
     * Returns an instance of the object
     * @param hubId
     * @return 
     */
    public static BindPossibilitiesToStops createInstance(Integer hubId)
    {
        ArrayList<ArgumentStandard> arrayListArguments = new ArrayList<>();
        ArgumentInteger argumentHubId = new ArgumentInteger("hub_id", hubId);
        arrayListArguments.add(argumentHubId);
        CollectionStandard<ArgumentStandard> standardCollection = new CollectionStandard(arrayListArguments);
        ArgumentCollection argumentCollection = new ArgumentCollection(standardCollection);
        BindPossibilitiesToStops bindPossibilitiesToStops = (BindPossibilitiesToStops) ObjectFactory.get("BindPossibilitiesToStops", argumentCollection);
        return bindPossibilitiesToStops;
    }
    
    /**
     * Will not be used since its a singleton
     * @return 
     */
    @Override
    public BindPossibilitiesToStops cloneObject() 
    {
        ArrayList<Stop> arrayListStops = new ArrayList<>();
        return new BindPossibilitiesToStops();
    }

    /**
     * Returns a new stop object
     * @return 
     */
    @Override
    public Stop createNewItem() 
    {
        return Stop.createInstance();
    }
}
