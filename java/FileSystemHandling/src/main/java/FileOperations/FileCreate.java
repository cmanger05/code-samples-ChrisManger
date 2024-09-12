/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileOperations;

import Model.DataObjectFileStandard;
import Model.DataObjectInterface;
import java.io.File;

/**
 *
 * @author chris
 */
public class FileCreate extends FileOperationsAbstractStandard implements FileOperationsInterface {	    
    @Override
    public void execute(DataObjectInterface fileDataObject)
    {
        if(fileDataObject instanceof DataObjectFileStandard) {
            String filePath = new File("").getAbsolutePath() + "\\src\\main\\java\\FileStorage\\";
            DataObjectFileStandard fileDataObjectStandard =(DataObjectFileStandard)fileDataObject;
            String fileNameAndPath = filePath + fileDataObjectStandard.getFilename();
            File demo_file = new File(fileNameAndPath);
        
            try{
                if(demo_file.createNewFile()) {
                    this.setDidOperationSucceed(true);
                } 
            } 
            catch(Exception e){
                System.out.println(e);
            }            
        }
    }
}
