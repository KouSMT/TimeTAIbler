/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetaiblerappclientadmin;

import entity.ClassDTO;
import entity.SubjectDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import session.SubjectFacadeRemote;
import session.ClassFacadeRemote;

/**
 *
 * @author Dennis
 */
public class AdminAppClient {

    @EJB
    private static SubjectFacadeRemote subjectFacade;

    @EJB
    private static ClassFacadeRemote classFacade;

    public static void main(String[] args) {
        AdminAppClient client = new AdminAppClient();
        client.addSubjectAndClasses();
        //client.updateClasses();
        //client.deleteClasses();
    }

    private void addSubjectAndClasses() {
        SubjectDTO subjectDTO1 = new SubjectDTO("COS30041", "Enterprise Java");
        SubjectDTO subjectDTO2 = new SubjectDTO("SWE30001", "OOP");

        //debugging
        boolean result = subjectFacade.createRecord(subjectDTO1);
        if (result) {
            System.out.println("The operation is successful.");
        } else {
            System.out.println("The operation fails!");
        }

        result = subjectFacade.createRecord(subjectDTO2);
        if (result) {
            System.out.println("The operation is successful.");
        } else {
            System.out.println("The operation fails!");
        }

        try {
            SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
            ClassDTO classDTO1 = new ClassDTO("00000001", "COS30041", 'L', displayFormat.parse("10:00"), 1, (short) 1);
            ClassDTO classDTO2 = new ClassDTO("00000002", "COS30041", 'T', displayFormat.parse("11:00"), 1, (short) 1);
            ClassDTO classDTO3 = new ClassDTO("00000003", "COS30041", 'T', displayFormat.parse("11:00"), 1, (short) 2);
            ClassDTO classDTO4 = new ClassDTO("00000004", "SWE30001", 'L', displayFormat.parse("10:00"), 2, (short) 2);
            ClassDTO classDTO5 = new ClassDTO("00000005", "SWE30001", 'T', displayFormat.parse("14:00"), 2, (short) 2);
            ClassDTO classDTO6 = new ClassDTO("00000006", "SWE30001", 'T', displayFormat.parse("11:00"), 2, (short) 1);

            result = classFacade.createRecord(classDTO1);
            if (result) {
                System.out.println("The operation is successful.");
            } else {
                System.out.println("The operation fails!");
            }
            result = classFacade.createRecord(classDTO2);
            if (result) {
                System.out.println("The operation is successful.");
            } else {
                System.out.println("The operation fails!");
            }
            result = classFacade.createRecord(classDTO3);
            if (result) {
                System.out.println("The operation is successful.");
            } else {
                System.out.println("The operation fails!");
            }
            result = classFacade.createRecord(classDTO4);
            if (result) {
                System.out.println("The operation is successful.");
            } else {
                System.out.println("The operation fails!");
            }
            result = classFacade.createRecord(classDTO5);
            if (result) {
                System.out.println("The operation is successful.");
            } else {
                System.out.println("The operation fails!");
            }
            result = classFacade.createRecord(classDTO6);
            if (result) {
                System.out.println("The operation is successful.");
            } else {
                System.out.println("The operation fails!");
            }

        } catch (ParseException pe) {
            System.out.println("Time format is wrong!");
        }
    }

    private void updateClasses() {
        try {
            SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
            ClassDTO classDTO1 = new ClassDTO("00000001", "COS30041", 'L', displayFormat.parse("10:00"), 1, (short) 3);
            boolean result = classFacade.updateRecord(classDTO1);
            if (result) {
                System.out.println("The operation is successful.");
            } else {
                System.out.println("The operation fails!");
            }
        } catch (ParseException pe) {
            System.out.println("Time format is wrong!");
        }
    }

    private void deleteClasses() {
        boolean result = classFacade.deleteRecord("00000006");
        if (result) {
            System.out.println("The operation is successful.");
        } else {
            System.out.println("The operation fails!");
        }
    }
}
