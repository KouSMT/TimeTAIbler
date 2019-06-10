/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PreferenceDTO;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Dennis
 */
@Remote
public interface PreferenceFacadeRemote {

    boolean createRecord(PreferenceDTO preferenceDTO);

    PreferenceDTO getRecord(String preferenceId);

    boolean updateRecord(PreferenceDTO preferenceDTO);

    boolean deleteRecord(String preferenceId);

    ArrayList<PreferenceDTO> getPreferencesByStudentId(String studentId);
    
}
