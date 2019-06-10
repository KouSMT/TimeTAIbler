/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.SubjectDTO;
import javax.ejb.Remote;

/**
 *
 * @author Dennis
 */
@Remote
public interface SubjectFacadeRemote {

    boolean createRecord(SubjectDTO subjectDTO);

    SubjectDTO getMethod(String subjectId);

    boolean deleteRecord(String subjectId);
    
}
