/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ClassDTO;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dennis
 */
@Stateless
public class ClassFacade implements ClassFacadeRemote {

    @PersistenceContext(unitName = "TimeTAIbler-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(entity.Class classObj) {
        em.persist(classObj);
    }

    private void edit(entity.Class classObj) {
        em.merge(classObj);
    }

    private void remove(entity.Class classObj) {
        em.remove(em.merge(classObj));
    }

    private entity.Class find(Object id) {
        return em.find(entity.Class.class, id);
    }

    private entity.Class myDTO2DAO(ClassDTO classDTO) {
        entity.Class classObj = new entity.Class();
        classObj.setClassid(classDTO.getClassid());
        classObj.setSubjectid(classDTO.getSubjectid());
        classObj.setClasstype(classDTO.getClasstype());
        classObj.setStarttime(classDTO.getStartTime());
        classObj.setHours(classDTO.getHours());
        classObj.setDay(classDTO.getDay());
        return classObj;
    }
    
    private ClassDTO myDAO2DTO(entity.Class classObj) {
        return new ClassDTO(classObj.getClassid(), classObj.getSubjectid(), classObj.getClasstype(),
            classObj.getStarttime(), classObj.getHours(), classObj.getDay());
    }

    @Override
    public boolean createRecord(ClassDTO classDTO) {
        if (find(classDTO.getClassid()) != null) {
            return false;
        }
        
        try {
            entity.Class classObj = this.myDTO2DAO(classDTO);
            this.create(classObj);
            return true;
        } catch (Exception ex) {
        return false;
        }
    }

    @Override
    public ClassDTO getRecord(String classId) {
        entity.Class classObj = find(classId);
        return this.myDAO2DTO(classObj);
    }

    @Override
    public boolean updateRecord(ClassDTO classDTO) {
        if (find(classDTO.getClassid()) == null) {
            return false;
        } else {
            try {
                entity.Class classObj = this.myDTO2DAO(classDTO);
                this.edit(classObj);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }

    @Override
    public boolean deleteRecord(String classId) {
        if (find(classId) == null) {
            return false;
        } else {
            try {
                entity.Class classObj = this.find(classId);
                this.remove(classObj);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }

    @Override
    public ArrayList<ClassDTO> getClassByTimeDay(String subjectid, short day, String time, int hours) {
        javax.persistence.Query query;
        query = em.createNamedQuery("Class.findByDayAndTime");
        query.setParameter("subjectid", subjectid);
        query.setParameter("day", day);
        query.setParameter("starttime", time);
        query.setParameter("hours", hours);
        
        ArrayList<entity.Class> daoList = (ArrayList<entity.Class>) query.getResultList();
        ArrayList<ClassDTO> dtoList = null;
        for (entity.Class classObj : daoList)
        {
            dtoList.add(myDAO2DTO(classObj));
        }
        
        return dtoList;
    }
    
    
}
