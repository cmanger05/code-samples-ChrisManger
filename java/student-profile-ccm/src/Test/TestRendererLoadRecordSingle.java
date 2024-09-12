/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;
import Renderers.RenderLoadedRecordSingle;
import Model.ProfileDataRepository;
/**
 *
 * @author chris
 */
public class TestRendererLoadRecordSingle {
    public static void main(String[] args){
        ProfileDataRepository data = new ProfileDataRepository(99,"Demetrius Melroys",
                "domain44","28 Coactus Road, Mesa, AZ 28755");
        new RenderLoadedRecordSingle(data);
    }
}
