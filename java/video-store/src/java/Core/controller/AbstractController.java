/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.controller;

/**
 *
 * @author chris
 */
public abstract class AbstractController {

    private Boolean isDebugMode = false;
    
    /**
     * Enables debug mode
     */
    public void enableDebugMode()
    {
        this.isDebugMode = true;
    }
    
    /**
     * Determines if we are in debug mode
     * @return 
     */
    private Boolean _isDebugMode()
    {
        return this.isDebugMode;
    }
        
    /**
     * Prints text to the screen
     * @param textToPrint 
     */
    public void println(String textToPrint)
    {
        System.out.println(textToPrint);
    }
    
    /**
     * Prints an empty line
     */
    public void printBlankLine()
    {
        String newLine = System.getProperty("line.separator");
        System.out.println("");
    }
    
    /**
     * Prints text to screen only if debug mode is on.
     * @param textToPrint 
     */
    public void printDebugText(String textToPrint)
    {
        if(this._isDebugMode()) {
            System.out.println(textToPrint);
        }
    }
}
