/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetaiblerappclientuser;

import entity.EnrollmentDTO;
import entity.PreferenceDTO;
import entity.StudentDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import session.EnrollmentFacadeRemote;
import session.PreferenceFacadeRemote;
import session.StudentFacadeRemote;

/**
 *
 * @author Dennis
 */
public class UserAppClient {

    @EJB
    private static PreferenceFacadeRemote preferenceFacade;

    @EJB
    private static EnrollmentFacadeRemote enrollmentFacade;

    @EJB
    private static StudentFacadeRemote studentFacade;

    public static void main(String[] args) {
        UserAppClient client = new UserAppClient();
        client.addStudents();
        client.addPreferences();
        //client.updatePreferences();
        //client.deletePreferences();
    }

    private void addStudents() {
        StudentDTO studentDTO1 = new StudentDTO("STU00001", "Dennis", "dennis@email.com");
        StudentDTO studentDTO2 = new StudentDTO("STU00002", "Steve", "steve@email.com");

        boolean result = studentFacade.createRecord(studentDTO1);
        result = studentFacade.createRecord(studentDTO2);

        EnrollmentDTO enrollmentDTO1 = new EnrollmentDTO("ENR00001", "COS30041", "STU00001");
        EnrollmentDTO enrollmentDTO2 = new EnrollmentDTO("ENR00002", "SWE30001", "STU00001");
        EnrollmentDTO enrollmentDTO3 = new EnrollmentDTO("ENR00003", "SWE30001", "STU00002");
        EnrollmentDTO enrollmentDTO4 = new EnrollmentDTO("ENR00004", "COS30041", "STU00002");

        result = enrollmentFacade.createRecord(enrollmentDTO1);
        result = enrollmentFacade.createRecord(enrollmentDTO2);
        result = enrollmentFacade.createRecord(enrollmentDTO3);
        result = enrollmentFacade.createRecord(enrollmentDTO4);
    }

    private void addPreferences() {
        try {
            SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
            PreferenceDTO prefDTO1 = new PreferenceDTO("PRF00001", "STU00001", (short) 1, displayFormat.parse("10:00"), 8);
            PreferenceDTO prefDTO2 = new PreferenceDTO("PRF00002", "STU00001", (short) 2, displayFormat.parse("10:00"), 2);
            PreferenceDTO prefDTO3 = new PreferenceDTO("PRF00003", "STU00001", (short) 2, displayFormat.parse("14:00"), 3);
            PreferenceDTO prefDTO4 = new PreferenceDTO("PRF00004", "STU00002", (short) 1, displayFormat.parse("10:00"), 5);
            PreferenceDTO prefDTO5 = new PreferenceDTO("PRF00005", "STU00002", (short) 3, displayFormat.parse("10:00"), 8);

            boolean result = preferenceFacade.createRecord(prefDTO1);
            result = preferenceFacade.createRecord(prefDTO2);
            result = preferenceFacade.createRecord(prefDTO3);
            result = preferenceFacade.createRecord(prefDTO4);
            result = preferenceFacade.createRecord(prefDTO5);

        } catch (ParseException pe) {
            System.out.println("Time format is wrong!");
        }
    }
    
    private void updatePreferences() {
        try {
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        PreferenceDTO prefDTO3 = new PreferenceDTO("PRF00003", "STU00001", (short) 3, displayFormat.parse("10:00"), 8);
            
        boolean result = preferenceFacade.updateRecord(prefDTO3);
        if (result) {
            System.out.println("The operation is successful.");
        } else {
            System.out.println("The operation fails!");
        }
        } catch (ParseException pe) {
            System.out.println("Time format is wrong!");
        }
    }
    
    private void deletePreferences() {
        boolean result = preferenceFacade.deleteRecord("PRF00006");
        if (result) {
            System.out.println("The operation is successful.");
        } else {
            System.out.println("The operation fails!");
        }
    }
    
}
