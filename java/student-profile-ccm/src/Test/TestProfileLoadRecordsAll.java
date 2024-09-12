package Test;
import Model.ProfileLoad;
import Model.ProfileDataRepository;
import Renderers.RenderLoadedRecordsAll;
import java.util.HashMap;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author chris
 */
public class TestProfileLoadRecordsAll {
    public static void main(String[] args) {
        ProfileLoad modelLoad = new ProfileLoad();
        HashMap<Integer, ProfileDataRepository> profilesList = modelLoad.loadAllRecords();
        if(modelLoad.hasExecutedSuccessfully()) {
            new RenderLoadedRecordsAll(profilesList);
            System.out.println("Records successfully loaded and rendered.");
        } else {
            System.out.println("failed to load single record.");
        }
    }
}
