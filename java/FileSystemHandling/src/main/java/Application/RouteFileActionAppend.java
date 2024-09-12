/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application;

import static Application.ApplicationLauncher.sc;
import FileOperations.FileAppend;
import Model.DataObjectFileStandard;

/**
 *
 * @author chris
 */
public class RouteFileActionAppend implements RouteFileActionInterface {

    @Override
    public void execute() {
        System.out.println("Enter the File Name - ");
        ApplicationLauncher.sc.nextLine();
        String file_name = ApplicationLauncher.sc.nextLine();
        
        System.out.println("Provide the input to append in the file - ");
        String append = ApplicationLauncher.sc.nextLine();
          
        DataObjectFileStandard dataObject = new DataObjectFileStandard(file_name, append);
        FileAppend operationAppendFile = new FileAppend();
        operationAppendFile.execute(dataObject);
        if(operationAppendFile.didOperationSucceed()) {
            System.out.println("***** String appended successfully *****");
        } 
    }
    
}
