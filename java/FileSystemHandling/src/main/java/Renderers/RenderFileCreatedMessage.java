/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Renderers;

import FileOperations.FileCreate;

/**
 *
 * @author chris
 */
public class RenderFileCreatedMessage extends RendererTextAbstract {
    FileCreate operationFileCreateObject = null;
    public RenderFileCreatedMessage(FileCreate operationFileCreate) {
        this.operationFileCreateObject = operationFileCreate;
        this.execute();
    }
    
    @Override
    public void execute()
    {
        if(this.operationFileCreateObject.didOperationSucceed()) {
            System.out.println("***** File Creation Successuful *****");
        } else {
            System.out.println("***** Error in file creation *****");
        }
    }  
}
