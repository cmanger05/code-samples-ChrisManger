/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouteSimulator.Model;

import ObjectCreation.InterfacesCreational.InstanceFactoryStandardInterface;
import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.Model.Arguments.ArgumentInteger;
import ObjectCreation.Model.Arguments.ArgumentStandard;
import ObjectCreation.Model.ObjectFactory;
import ObjectCreation.Model.Prototypes.CollectionStandard;
import ObjectCreation.Model.Prototypes.ObjectStandard;
import Routes.Model.Truck;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class TruckCandidateSample extends ObjectStandard implements InstanceFactoryStandardInterface 
{
    private Truck _truck;
    
    private Double _qtyGoodsInTruck;
    
    private Double _qtyGoodsDelivered;
    
    private Double _defaultQtyAllocationPerStop;
    
    /**
     * Dynamic Constructor
     * @param arguments 
     */
    @Override
    public void construct(ArgumentCollection arguments) 
    {
        try {
            ArgumentInteger argumentTruckId = arguments.getIntegerArgumentByName("truck_id");
            Integer truckId = argumentTruckId.getValue();
            Truck truckObject = Truck.createInstance();
            truckObject.loadById(1);
            this._setTruck(truckObject);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(TruckCandidateSample.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Creates an instance of this object
     * @param truckId
     * @return 
     */
    public static TruckCandidateSample createInstance(Integer truckId)
    {
        ArrayList<ArgumentStandard> arrayListArguments = new ArrayList<>();
        ArgumentInteger argumentTruckId = new ArgumentInteger("truck_id", truckId);
        arrayListArguments.add(argumentTruckId);
        
        CollectionStandard<ArgumentStandard> standardCollection = new CollectionStandard(arrayListArguments);
        ArgumentCollection argumentCollection = new ArgumentCollection(standardCollection);
        TruckCandidateSample truckCandidateSample = (TruckCandidateSample) ObjectFactory.get("TruckCandidateSample", argumentCollection);
        return truckCandidateSample;
    }
    
    /**
     * Clones current object
     * @return 
     */
    @Override
    public TruckCandidateSample cloneObject() 
    {
        return new TruckCandidateSample();
    } 

    /**
     * @return the _truck
     */
    public Truck getTruck() 
    {
        return _truck;
    }

    /**
     * @param _truck the _truck to set
     */
    private void _setTruck(Truck _truck) 
    {
        this._truck = _truck;
    }

    /**
     * @return the _qtyGoodsInTruck
     */
    public Double getQtyGoodsInTruck() 
    {
        return _qtyGoodsInTruck;
    }

    /**
     * @return the _qtyGoodsDelivered
     */
    public Double getQtyGoodsDelivered() 
    {
        return _qtyGoodsDelivered;
    }

    /**
     * @return the _defaultQtyAllocationPerStop
     */
    public Double getDefaultQtyAllocationPerStop() 
    {
        return _defaultQtyAllocationPerStop;
    }

    /**
     * @param _qtyGoodsInTruck the _qtyGoodsInTruck to set
     */
    public void setQtyGoodsInTruck(Double _qtyGoodsInTruck) 
    {
        this._qtyGoodsInTruck = _qtyGoodsInTruck;
    }

    /**
     * @param _qtyGoodsDelivered the _qtyGoodsDelivered to set
     */
    public void setQtyGoodsDelivered(Double _qtyGoodsDelivered) 
    {
        this._qtyGoodsDelivered = _qtyGoodsDelivered;
    }

    /**
     * @param _defaultQtyAllocationPerStop the _defaultQtyAllocationPerStop to set
     */
    public void setDefaultQtyAllocationPerStop(Double _defaultQtyAllocationPerStop) 
    {
        this._defaultQtyAllocationPerStop = _defaultQtyAllocationPerStop;
    }
    
    /**
     * Marks specified quantity as delivered
     * @param qtyToMarkAsDelivered 
     */
    public void markQuantityAsDelivered(Double qtyToMarkAsDelivered)
    {
        this._qtyGoodsInTruck -= qtyToMarkAsDelivered;
        this._qtyGoodsDelivered += qtyToMarkAsDelivered;
    }
}
