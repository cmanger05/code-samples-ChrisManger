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
public class TestProfileUpdateAllFields {
        public static void main(String[] args) {
        ProfileDataRepository data = new ProfileDataRepository(2, "Madelyn McHattertoner","stuyv222",
                "28 Mill Creek Ridge, Bearville, NB");
        ProfileUpdate modelUpdate = new ProfileUpdate();
        modelUpdate.execute(data);
        if(modelUpdate.hasExecutedSuccessfully()) {
            System.out.println("Test to update all fields in record successful.");
        } else {
            System.out.println("Test to update all fields in record failed.");
        }
    }
}
