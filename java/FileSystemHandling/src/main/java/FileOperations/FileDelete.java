/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileOperations;

import Model.DataObjectFileStandard;
import Model.DataObjectInterface;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author chris
 */
public class FileDelete extends FileOperationsAbstractStandard implements FileOperationsInterface {
    @Override
    public void execute(DataObjectInterface fileDataObject)
    {
        if(fileDataObject instanceof DataObjectFileStandard) {
            String filePath = new File("").getAbsolutePath() + "\\src\\main\\java\\FileStorage\\";
            DataObjectFileStandard fileDataObjectStandard =(DataObjectFileStandard)fileDataObject;
            String fileNameAndPath = filePath + fileDataObjectStandard.getFilename();
        
            try{
                File demo_file = new File(fileNameAndPath);

                if(demo_file.delete()) {
                    this.setDidOperationSucceed(true);
                }
            }
            catch(Exception e){
                System.out.println(e);
            }            
        }
    }
}
