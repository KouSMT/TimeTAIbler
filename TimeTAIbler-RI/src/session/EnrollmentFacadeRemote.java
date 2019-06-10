/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EnrollmentDTO;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Dennis
 */
@Remote
public interface EnrollmentFacadeRemote {

    boolean createRecord(EnrollmentDTO enrollmentDTO);

    EnrollmentDTO getRecord(String enrollmentId);

    boolean deleteRecord(String enrollmentId);

    ArrayList<EnrollmentDTO> getEnrolledSubjects(String studentId);
    
}
