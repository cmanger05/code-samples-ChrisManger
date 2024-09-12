/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileOperations;

/**
 *
 * @author chris
 */
public abstract class FileOperationsAbstractStandard implements FileOperationsInterface {
    boolean didOperationSucceed = false;
    
    public void setDidOperationSucceed(boolean successValue) {
        this.didOperationSucceed = successValue;
    }
    
    public boolean didOperationSucceed()
    {
        return this.didOperationSucceed;
    }
}
