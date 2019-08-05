/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectCreation.InterfacesCreational;

import ObjectCreation.Model.Arguments.ArgumentCollection;

/**
 *
 * @author chris
 */
public interface InstanceFactoryBuilderInterface extends InstanceFactoryBaseInterface
{
    public InstanceFactoryBaseInterface buildInstance(ArgumentCollection arguments);    
}
