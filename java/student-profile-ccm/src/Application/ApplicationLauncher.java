/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.sql.SQLException;
import java.util.Scanner;
import Model.ProfileAdd;
import Model.ProfileDataRepository;
import Model.ProfileDelete;
import Model.ProfileLoad;
import Model.ProfileUpdate;
import Renderers.RenderLoadedRecordSingle;
import Renderers.RenderLoadedRecordsAll;
import java.util.HashMap;

/**
 *
 * @author Lenovo
 */
public class ApplicationLauncher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("***** The Student Profile Portal *****");
        
        while(true){
            System.out.println(
            "\n Press 1 for Adding Profile \n"+ 
            "\n Press 2 for Displaying Profiles \n"+
            "\n Press 3 for Displaying Profile with ID \n"+
            "\n Press 4 for Deleting Profile \n"+
            "\n Press 5 for Updating Profile \n"+
            "\n Press 6 for Exiting the portal \n"
            );
            
            System.out.println("Please Provide Input - ");
            int i = sc.nextInt();
            sc.nextLine();
            
            switch(i){
                case 1:
                    System.out.println("Add Profile");
                    System.out.println("Enter Name");
                    String name = sc.nextLine();
                    System.out.println("Enter Domain");
                    String domain = sc.nextLine();
                    System.out.println("Enter Address");
                    String address = sc.nextLine();
                    
                    ProfileAdd modelAdd = new ProfileAdd();
                    ProfileDataRepository dataNewStudent = new ProfileDataRepository(null, name, domain, address);
                    modelAdd.execute(dataNewStudent);
           
                    if(modelAdd.hasExecutedSuccessfully())
                        System.out.println("** Profile Added **");
                    else
                        System.out.println("** Problem Occured **");
                    break;
                case 2:
                    System.out.println("Display");
                    ProfileLoad modelLoadAllProfiles = new ProfileLoad();
                    HashMap<Integer, ProfileDataRepository> profilesList = modelLoadAllProfiles.loadAllRecords();
                    if(modelLoadAllProfiles.hasExecutedSuccessfully()) {
                        new RenderLoadedRecordsAll(profilesList);
                        System.out.println("All records in system are displayed above.");
                    }
                    break;
                case 3:
                    System.out.println("Display by ID");
                    
                    System.out.println("Enter ID ->");
                    int in = sc.nextInt();
                    ProfileDataRepository dataRequestRecord = new ProfileDataRepository(in, "","","");
                    ProfileLoad modelLoadSingleRecord = new ProfileLoad();
                    ProfileDataRepository loadedRecord = modelLoadSingleRecord.loadRecordById(dataRequestRecord);
                    if(modelLoadSingleRecord.hasExecutedSuccessfully()) {
                        if(loadedRecord.getId()!= null) {
                            new RenderLoadedRecordSingle(loadedRecord);
                        } else {
                            System.out.println("****Profile with this ID not avaiable");
                        }
                        
                    }         
                    break;
                case 4:
                    System.out.println("Delete");
                    System.out.println("Enter ID to delete ->");
                    int de = sc.nextInt();
                    ProfileDataRepository data = new ProfileDataRepository(de,"","","");
                    ProfileDelete modelDelete = new ProfileDelete();
                    if(modelDelete.doesRecordExist(de)) {
                       modelDelete.execute(data);
                       if(modelDelete.hasExecutedSuccessfully()) {
                           System.out.println("Record successfully deleted.");
                       } else {
                           System.out.println("failed to delete record");
                       }     
                    } else {
                        //Runs when a user enters an ID that does not exist.
                        System.out.println("Problem occurred.");
                    }
                    break;
                case 5:
                    System.out.println("Update");
                    System.out.println(
                    "Press 1 to update Domain \n"+
                    "Press 2 to update Address"
                    );
                    int se = sc.nextInt();
                    
                    if(se==1){
                        System.out.println("Enter ID ");
                        int idd = sc.nextInt();
                        System.out.println("Enter new Domain");
                        String ddomain = sc.next();
                        
                        ProfileDataRepository dataForUpdate = new ProfileDataRepository(idd, "",ddomain,"");
                        ProfileUpdate modelUpdate = new ProfileUpdate();
                        
                        if(modelUpdate.doesRecordExist(idd)) {
                            modelUpdate.updateDomainOnly(dataForUpdate);
                            if(modelUpdate.hasExecutedSuccessfully()) {
                                System.out.println("Domain updated successfully");
                            } else {
                                System.out.println("Domain updated failed. Error occurred.");
                            }
                        } else {
                            System.out.println("Domain updated failed. An error occurred.");
                        }

                    }
                    else if(se==2){
                        System.out.println("Enter ID ");
                        int idd = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter new Address");
                        String addr = sc.nextLine();
                        
                        ProfileDataRepository dataUpdateDomain = new ProfileDataRepository(idd, "","",addr);
                        ProfileUpdate modelUpdateDomain = new ProfileUpdate();
                        if(modelUpdateDomain.doesRecordExist(idd)) {
                            modelUpdateDomain.updateAddressOnly(dataUpdateDomain);
                            if(modelUpdateDomain.hasExecutedSuccessfully()) {
                                System.out.println("Address updated successfully.");
                            } else {
                                System.out.println("Address failed to update.");
                            }
                        } else {
                            System.out.println("Address failed to update. An error occurred");
                        }
                    }
                    else
                        System.out.println("Invalid Choice *****");
                    break;
                case 6:
                    System.out.println("Exit");
                    System.exit(0);
                    break;   
                default:
                    System.out.println("Invalid");
            }
        }
    }
    
}
