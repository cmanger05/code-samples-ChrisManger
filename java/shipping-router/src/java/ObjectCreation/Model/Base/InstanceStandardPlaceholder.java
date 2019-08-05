/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectCreation.Model.Base;

import ObjectCreation.Model.Arguments.ArgumentCollection;
import ObjectCreation.InterfacesCreational.InstanceFactoryStandardInterface;
import ObjectCreation.Model.Prototypes.ObjectStandard;

/**
 *
 * @author chris
 */
public class InstanceStandardPlaceholder extends ObjectStandard
    implements InstanceFactoryStandardInterface
{
    @Override
    public void construct(ArgumentCollection arguments) {}

    /**
     * Clones current object
     * @return
     */
    @Override
    public InstanceStandardPlaceholder cloneObject() {
        return new InstanceStandardPlaceholder();
    }
}
