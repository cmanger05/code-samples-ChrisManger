/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Model;

/**
 *
 * @author chris
 */
public interface ResourceSaveInterface 
{
    public boolean saveNew(AbstractModel model);
    
    public boolean saveExisting(AbstractModel model);
}
