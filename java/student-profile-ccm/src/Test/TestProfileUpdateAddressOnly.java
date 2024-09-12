package Test;
import Model.ProfileUpdate;
import Model.ProfileDataRepository;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author chris
 */
public class TestProfileUpdateAddressOnly {
    public static void main(String[] args) {
        ProfileDataRepository data = new ProfileDataRepository(2, "","","28 Mill Creek Ridge, Bear City, NB");
        ProfileUpdate modelUpdate = new ProfileUpdate();
        modelUpdate.updateAddressOnly(data);
        if(modelUpdate.hasExecutedSuccessfully()) {
            System.out.println("Record successfully been updated in the database.");
        } else {
            System.out.println("Record failed to update.");
        }
    }
}
