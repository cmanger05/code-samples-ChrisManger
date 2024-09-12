/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Renderers;

/**
 *
 * @author chris
 */
public class RendererTextMenu extends RendererTextAbstract {
    @Override
    public void execute()
    {
        System.out.println("\n************** FILE SYSTEM HANDLING **************");
        System.out.println("Press 1 for Creating a File");
        System.out.println("Press 2 for Writing in a File");
        System.out.println("Press 3 for Read from a File");
        System.out.println("Press 4 for Appending in a File");
        System.out.println("Press 5 for Deleting a File");
        System.out.println("Press 6 for Exiting from the system");
        System.out.println("**************************************************");
        System.out.println("CHOOSE FROM THE MENU :-");
    }
}
