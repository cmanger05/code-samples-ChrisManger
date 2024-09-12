/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application;

import static Application.ApplicationLauncher.sc;
import FileOperations.FileDelete;
import Model.DataObjectFileStandard;
import Renderers.RenderFileDeletedMessage;

/**
 *
 * @author chris
 */
public class RouteFileActionDelete implements RouteFileActionInterface {

    @Override
    public void execute() {
        System.out.println("Enter the File Name - ");
        ApplicationLauncher.sc.nextLine();
        String file_name = ApplicationLauncher.sc.nextLine();
        
        DataObjectFileStandard dataObject = new DataObjectFileStandard(file_name, "");
        FileDelete operationDeleteFile = new FileDelete();
        operationDeleteFile.execute(dataObject);
        new RenderFileDeletedMessage(operationDeleteFile);
    }
    
}
