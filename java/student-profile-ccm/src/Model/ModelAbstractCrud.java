/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author chris
 */
public abstract class ModelAbstractCrud implements ModelInterfaceCrud {
    private boolean hasExecutedSucessfully = false;
    
    protected void setHasExecutedSucessfully(boolean candidateHasExecutedSuccessfully) {
        this.hasExecutedSucessfully = candidateHasExecutedSuccessfully;
    }
    
    public boolean hasExecutedSuccessfully()
    {
        return this.hasExecutedSucessfully;
    }
}
