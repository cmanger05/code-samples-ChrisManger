/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileOperations;

import Model.DataObjectFileStandard;
import Model.DataObjectInterface;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author chris
 */
public class FileRead extends FileOperationsAbstractStandard implements FileOperationsInterface {
    @Override
    public void execute(DataObjectInterface fileDataObject)
    {
        if(fileDataObject instanceof DataObjectFileStandard) {
            String filePath = new File("").getAbsolutePath() + "\\src\\main\\java\\FileStorage\\";
            DataObjectFileStandard fileDataObjectStandard =(DataObjectFileStandard)fileDataObject;
            String fileNameAndPath = filePath + fileDataObjectStandard.getFilename();
            char[] read = new char[500];
        
            try{
                FileReader demo_file = new FileReader(fileNameAndPath);
                demo_file.read(read);
                this.setDidOperationSucceed(true);
                fileDataObjectStandard.setDataRead(read);
                demo_file.close();
            }
            catch(Exception e){
                System.out.println(e);
            }            
        }  
    }
}
