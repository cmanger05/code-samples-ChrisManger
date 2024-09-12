/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Renderers;

import Model.ProfileDataRepository;
import Renderers.RenderLoadedRecordSingle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author chris
 */
public class RenderLoadedRecordsAll implements RendererInterface {
    HashMap<Integer, ProfileDataRepository> allLoadedRecords = new HashMap<>();
    
    public RenderLoadedRecordsAll(HashMap<Integer, ProfileDataRepository> loadedRecords) {
        this.allLoadedRecords = loadedRecords;
        this.execute();
    }
    
    public void execute() {
        Iterator it = this.allLoadedRecords.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry recordSingleRaw = (Map.Entry)it.next();
            ProfileDataRepository recordSingle = (ProfileDataRepository)recordSingleRaw.getValue();
            if(recordSingle instanceof ProfileDataRepository) {
               new RenderLoadedRecordSingle(recordSingle);
            }

            it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
