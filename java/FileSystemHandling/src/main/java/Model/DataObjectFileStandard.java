/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author chris
 */
public class DataObjectFileStandard extends DataObjectAbstract {
    char[] dataRead;
    public DataObjectFileStandard(String fileName, String textToWrite) {
       this.setDataByKey("fileName", fileName);
       this.setDataByKey("textToWrite", textToWrite);
    }
    
    public String getFilename() {
        return this.getDataByKey("fileName");
    }
    
    public String getTextToWrite() {
        return this.getDataByKey("textToWrite");
    }
    
    public void setDataRead(char[] dataRead) {
        this.dataRead = dataRead;
    }
    
    public char[] getDataRead() {
        return this.dataRead;
    }
}
