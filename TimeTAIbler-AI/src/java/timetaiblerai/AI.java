/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetaiblerai;

import entity.EnrollmentDTO;
import entity.PreferenceDTO;
import entity.StudentClassLinkDTO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import session.StudentClassLinkFacadeRemote;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import oracle.jdbc.dcn.DatabaseChangeEvent;
import oracle.jdbc.dcn.DatabaseChangeListener;
import session.EnrollmentFacadeRemote;
import session.PreferenceFacadeRemote;

/**
 *
 * @author Dennis
 */
public class AI implements DatabaseChangeListener {
    
    @EJB
    private static StudentClassLinkFacadeRemote scLinkFacade;

    @EJB
    private static PreferenceFacadeRemote prefFacade;
    
    @EJB
    private static EnrollmentFacadeRemote enrollFacade;

    Main demo;

    AI(Main dem) {
        demo = dem;
    }

    @Override
    public void onDatabaseChangeNotification(DatabaseChangeEvent dce) {
        Thread t = Thread.currentThread();
        //handle class changes first

        if (dce.getTableChangeDescription()[0].getTableName().equals("CLASS")) {
            String classId = dce.getTableChangeDescription()[0].getRowChangeDescription()[0].getRowid().toString();
            //we are not worried about new classes being added; just classes that are updated or deleted
            ArrayList<StudentClassLinkDTO> sclDTOList = scLinkFacade.findStudentsInClass(classId);
            for (StudentClassLinkDTO sclDTO : sclDTOList) {
                //redo timetable
            }
        } else if (dce.getTableChangeDescription()[0].getTableName().equals("PREFERENCE")) {
            
        }
        
        synchronized (demo) {
            demo.notify();
        }
    }

    private StudentClassLinkFacadeRemote lookupStudentClassLinkFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (StudentClassLinkFacadeRemote) c.lookup("java:comp/env/StudentClassLinkFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);

        }
    }

    private PreferenceFacadeRemote lookupPreferenceFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (PreferenceFacadeRemote) c.lookup("java:comp/env/PreferenceFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void createTimetable(String studentId) {
        //get preferences
        ArrayList<PreferenceDTO> prefDTOList = prefFacade.getPreferencesByStudentId(studentId);
        
        //get enrollment
        ArrayList<EnrollmentDTO> enrollDTOList = enrollFacade.getEnrolledSubjects(studentId);
        
        //TODO: get unique class types from Class table (e.g. SELECT UNIQUE ClassType FROM CLASS WHERE SubjectId = ?)
        //assume 2 class types, C and T.
        
        //TODO: better heuristic function for class selection (e.g. within time period = 3 points,
        //same day but starts/ends just outside of time period = 2 points, same day but outside of time period = 1 point
        //not the same day = 0 points
        
    }

    private EnrollmentFacadeRemote lookupEnrollmentFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (EnrollmentFacadeRemote) c.lookup("java:comp/env/EnrollmentFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
