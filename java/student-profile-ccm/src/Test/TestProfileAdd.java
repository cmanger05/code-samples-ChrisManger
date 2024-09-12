package Test;
import Model.ProfileAdd;
import Model.ProfileDataRepository;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author chris
 */
public class TestProfileAdd {
    public static void main(String[] args) {
        ProfileDataRepository data = new ProfileDataRepository(null, "Charles Pasqua","domain22","38 Mountain Hedge Drive");
        ProfileAdd modelAdd = new ProfileAdd();
        modelAdd.execute(data);
        if(modelAdd.hasExecutedSuccessfully()) {
            System.out.println("Record successfully added to database.");
        } else {
            System.out.println("failed to add record");
        }
    }
}
