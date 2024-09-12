/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Renderers;
import Model.ProfileDataRepository;
/**
 *
 * @author chris
 */
public class RenderLoadedRecordSingle implements RendererInterface {
    ProfileDataRepository data;
    
    public RenderLoadedRecordSingle(ProfileDataRepository data)
    {
        this.data = data;
        this.execute();
    }
    
    @Override
    public void execute() {
         System.out.println(
           "ID      ->  "+ data.getId() +"\n"+
           "Name    ->  "+ data.getName() +"\n"+
           "Domain  ->  "+ data.getDomain() +"\n"+
           "Address ->  "+ data.getAddress() );
           System.out.println("**********************");
    }
}
