/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application;
/**
 *
 * @author chris
 */
public class RouteFileActionFactory {
    private int actionNumberSelected;
    
    public RouteFileActionFactory(int candidateActionNumberSelected) {
        this.actionNumberSelected = candidateActionNumberSelected;
    }
    
    private int getActionNumberSelected() {
        return this.actionNumberSelected;
    }
    
    public RouteFileActionInterface execute() {
        int selectedChoice = this.getActionNumberSelected();
        RouteFileActionInterface router;
        
        switch(selectedChoice){
            case 1:
                router = new RouteFileActionCreate();
                break;
            case 2:
                router =  new RouteFileActionWrite();
                break;
            case 3:
                router = new RouteFileActionRead();
                break;
            case 4:
                router = new RouteFileActionAppend();
                break;
            case 5:
                router = new RouteFileActionDelete();
                break;
            case 6:
                router = new RouteFileActionExit();
                break;
            default:
                router = new RouteFileActionNull();
        }
        
        return router;
    }
}
