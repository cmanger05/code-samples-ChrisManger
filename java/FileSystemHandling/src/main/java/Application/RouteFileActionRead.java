/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application;
import FileOperations.FileRead;
import Model.DataObjectFileStandard;

/**
 *
 * @author chris
 */
public class RouteFileActionRead implements RouteFileActionInterface {
    @Override
    public void execute() {
        System.out.println("Enter the File Name - ");
        ApplicationLauncher.sc.nextLine();
        String file_name = ApplicationLauncher.sc.nextLine();
        
        DataObjectFileStandard dataObject = new DataObjectFileStandard(file_name, "");
        FileRead operationReadFile = new FileRead();
        operationReadFile.execute(dataObject);
        if(operationReadFile.didOperationSucceed()) {
            System.out.println("***** The file reads as *****");
            System.out.println(dataObject.getDataRead());
        } 
    }
}
