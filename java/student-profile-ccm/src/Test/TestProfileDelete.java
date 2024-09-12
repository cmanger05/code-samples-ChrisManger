package Test;
import Model.ProfileDelete;
import Model.ProfileDataRepository;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author chris
 */
public class TestProfileDelete {
    public static void main(String[] args) {
        ProfileDataRepository data = new ProfileDataRepository(4,"","","");
        ProfileDelete modelDelete = new ProfileDelete();
        modelDelete.execute(data);
        if(modelDelete.hasExecutedSuccessfully()) {
            System.out.println("Record successfully deleted.");
        } else {
            System.out.println("failed to delete record");
        }
    }
}
