/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application;
import FileOperations.FileWrite;
import Model.DataObjectFileStandard;

/**
 *
 * @author chris
 */
public class RouteFileActionWrite implements RouteFileActionInterface {
    @Override
    public void execute()
    {
        System.out.println("Enter the File Name - ");
        ApplicationLauncher.sc.nextLine();
        String file_name = ApplicationLauncher.sc.nextLine();
        
        System.out.println("Provide the input for the file - ");
        String write = ApplicationLauncher.sc.nextLine();
        
        DataObjectFileStandard dataObject = new DataObjectFileStandard(file_name, write);
        FileWrite operationWriteFile = new FileWrite();
        operationWriteFile.execute(dataObject);
        if(operationWriteFile.didOperationSucceed()) {
           System.out.println("***** Successful written file *****"); 
        } 
    }
}
