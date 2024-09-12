/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author chris
 */
interface ModelInterfaceCrud {
    public abstract boolean hasExecutedSuccessfully();
    
    public abstract ProfileDataRepository execute(ProfileDataRepository data);
}
