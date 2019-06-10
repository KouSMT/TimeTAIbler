/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.StudentDTO;
import javax.ejb.Remote;

/**
 *
 * @author Dennis
 */
@Remote
public interface StudentFacadeRemote {

    boolean createRecord(StudentDTO studentDTO);

    StudentDTO getRecord(String studentId);

    boolean deleteRecord(String studentId);
    
}
