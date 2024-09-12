package Test;
import Model.ProfileLoad;
import Model.ProfileDataRepository;
import Renderers.RenderLoadedRecordSingle;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author chris
 */
public class TestProfileLoadSingleRecord {
    public static void main(String[] args) {
        ProfileDataRepository data = new ProfileDataRepository(1, "","","");
        ProfileLoad modelLoad = new ProfileLoad();
        ProfileDataRepository loadedRecord = modelLoad.loadRecordById(data);
        if(modelLoad.hasExecutedSuccessfully()) {
            new RenderLoadedRecordSingle(loadedRecord);
            System.out.println("Record successfully loaded and rendered.");
        } else {
            System.out.println("failed to load single record.");
        }
    }
}
