/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.StudentClassLinkDTO;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Dennis
 */
@Remote
public interface StudentClassLinkFacadeRemote {

    boolean createRecord(StudentClassLinkDTO studentClassLinkDTO);

    StudentClassLinkDTO getRecord(String sclinkId);

    boolean deleteRecord(String sclinkId);

    ArrayList<StudentClassLinkDTO> findStudentsInClass(String classId);
    
}
