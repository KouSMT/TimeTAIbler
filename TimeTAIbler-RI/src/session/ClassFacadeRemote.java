/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ClassDTO;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Dennis
 */
@Remote
public interface ClassFacadeRemote {

    boolean createRecord(ClassDTO classDTO);

    ClassDTO getRecord(String classId);

    boolean updateRecord(ClassDTO classDTO);

    boolean deleteRecord(String classId);

    ArrayList<ClassDTO> getClassByTimeDay(String subjectid, short day, String time, int hours);
    
}
