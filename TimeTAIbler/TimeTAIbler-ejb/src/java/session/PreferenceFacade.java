/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Preference;
import entity.PreferenceDTO;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dennis
 */
@Stateless
public class PreferenceFacade implements PreferenceFacadeRemote {

    @PersistenceContext(unitName = "TimeTAIbler-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    private void create(Preference preference) {
        em.persist(preference);
    }

    private void edit(Preference preference) {
        em.merge(preference);
    }

    private void remove(Preference preference) {
        em.remove(em.merge(preference));
    }

    private Preference find(Object id) {
        return em.find(Preference.class, id);
    }
    
    private Preference myDTO2DAO(PreferenceDTO preferenceDTO) {
        Preference preference = new Preference();
        preference.setPreferenceid(preferenceDTO.getPreferenceid());
        preference.setStudentid(preferenceDTO.getStudentid());
        preference.setDay(preferenceDTO.getDay());
        preference.setStarttime(preferenceDTO.getStarttime());
        preference.setHours(preferenceDTO.getHours());
        return preference;
    }
    
    private PreferenceDTO myDAO2DTO(Preference preference) {
        return new PreferenceDTO(preference.getPreferenceid(), preference.getStudentid(), preference.getDay(), preference.getStarttime(), preference.getHours());
    }

    @Override
    public boolean createRecord(PreferenceDTO preferenceDTO) {
        if (find(preferenceDTO.getPreferenceid()) != null) {
            return false;
        }
        
        try {
            Preference preference = this.myDTO2DAO(preferenceDTO);
            this.create(preference);
            return true;
        } catch (Exception ex) {
        return false;
        }
    }

    @Override
    public PreferenceDTO getRecord(String preferenceId) {
        Preference preference = find(preferenceId);
        return this.myDAO2DTO(preference);
    }

    @Override
    public boolean updateRecord(PreferenceDTO preferenceDTO) {
        if (find(preferenceDTO.getPreferenceid()) == null) {
            return false;
        } else {
            try {
                Preference preference = this.myDTO2DAO(preferenceDTO);
                this.edit(preference);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }

    @Override
    public boolean deleteRecord(String preferenceId) {
        if (find(preferenceId) == null) {
            return false;
        } else {
            try {
                Preference preference = this.find(preferenceId);
                this.remove(preference);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }

    @Override
    public ArrayList<PreferenceDTO> getPreferencesByStudentId(String studentId) {
        javax.persistence.Query query;
        query = em.createNamedQuery("Preference.findByStudentid").setParameter("studentid", studentId);
        ArrayList<Preference> daoList = (ArrayList<Preference>) query.getResultList();
        ArrayList<PreferenceDTO> dtoList = null;
        for (Preference preference : daoList)
        {
            dtoList.add(myDAO2DTO(preference));
        }
        return dtoList;
    }
    
    
}
