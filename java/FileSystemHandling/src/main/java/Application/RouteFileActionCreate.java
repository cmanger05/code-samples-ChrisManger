/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application;
import FileOperations.FileCreate;
import Model.DataObjectFileStandard;
import Renderers.RenderFileCreatedMessage;
import java.util.Scanner;

/**
 *
 * @author chris
 */
public class RouteFileActionCreate implements RouteFileActionInterface {

    
    public void execute()
    {
        System.out.println("Enter the File Name - ");
        ApplicationLauncher.sc.nextLine();
        String file_name = ApplicationLauncher.sc.nextLine();
        
        DataObjectFileStandard dataObject = new DataObjectFileStandard(file_name, "");
        FileCreate operationCreateFile = new FileCreate();
        operationCreateFile.execute(dataObject);
        new RenderFileCreatedMessage(operationCreateFile);   
    }
}
