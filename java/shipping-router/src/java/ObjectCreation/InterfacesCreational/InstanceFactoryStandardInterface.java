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
public interface InstanceFactoryStandardInterface extends InstanceFactoryBaseInterface 
{
    public void construct(ArgumentCollection arguments);
}
