/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application;

/**
 *
 * @author chris
 */
public class RouteFileActionExit implements RouteFileActionInterface {

    @Override
    public void execute() {
         System.out.println("**** THANKS FOR USING THE SYSTEM ********");
         System.exit(0);
    }
    
}
