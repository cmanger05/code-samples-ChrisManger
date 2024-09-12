/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;


import Renderers.RendererTextMenu;
import java.util.Scanner;

/**
 *
 * @author Chris Manger
 */
public class ApplicationLauncher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        launchApplication();        
    }
    
    static Scanner sc= new Scanner(System.in);
    
    public static void launchApplication(){
        new RendererTextMenu().execute();
        int choice=sc.nextInt();
        
        RouteFileActionInterface router = new RouteFileActionFactory(choice).execute();
        router.execute();
        launchApplication();
    }
    

    

    

    
    

    
}
