/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import Model.ProfileDataRepository;
import Model.ProfileUpdate;

/**
 *
 * @author chris
 */
public class TestProfileUpdateDomainOnly {
    public static void main(String[] args) {
        ProfileDataRepository data = new ProfileDataRepository(2, "","stuyv123","");
        ProfileUpdate modelUpdate = new ProfileUpdate();
        modelUpdate.updateDomainOnly(data);
        if(modelUpdate.hasExecutedSuccessfully()) {
            System.out.println("Record successfully been updated in the database.");
        } else {
            System.out.println("Record failed to update.");
        }
    }
}
