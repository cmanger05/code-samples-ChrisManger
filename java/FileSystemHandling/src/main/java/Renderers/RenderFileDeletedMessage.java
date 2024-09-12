/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Renderers;

import FileOperations.FileCreate;
import FileOperations.FileDelete;

/**
 *
 * @author chris
 */
public class RenderFileDeletedMessage extends RendererTextAbstract {
      FileDelete operationFileDeleteObject = null;
    public RenderFileDeletedMessage(FileDelete operationFileDelete) {
        this.operationFileDeleteObject = operationFileDelete;
        this.execute();
    }
    
    @Override
    public void execute()
    {
        if(this.operationFileDeleteObject.didOperationSucceed()) {
            System.out.println("***** Deletion of file Successful *****");
        } else {
            System.out.println("***** Error in file deletion *****");
        }
    }   
}
