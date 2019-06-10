/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Subject;
import entity.SubjectDTO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dennis
 */
public class SubjectFacade implements SubjectFacadeRemote {

    @PersistenceContext(unitName = "TimeTAIbler-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    private void create(Subject subject) {
        em.persist(subject);
    }

    private void remove(Subject subject) {
        em.remove(em.merge(subject));
    }

    private Subject find(Object id) {
        return em.find(Subject.class, id);
    }
    
    private Subject myDTO2DAO(SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        subject.setSubjectid(subjectDTO.getSubjectid());
        subject.setName(subjectDTO.getName());
        return subject;
    }
    
    private SubjectDTO myDAO2DTO(Subject subject) {
        return new SubjectDTO(subject.getSubjectid(), subject.getName());
    }

    @Override
    public boolean createRecord(SubjectDTO subjectDTO) {
        if (find(subjectDTO.getSubjectid()) != null) {
            return false;
        }
        
        try {
            Subject subject = this.myDTO2DAO(subjectDTO);
            this.create(subject);
            return true;
        } catch (Exception ex) {
        return false;
        }
    }

    @Override
    public SubjectDTO getMethod(String subjectId) {
        Subject subject = find(subjectId);
        return this.myDAO2DTO(subject);
    }


    @Override
    public boolean deleteRecord(String subjectId) {
        if (find(subjectId) == null) {
            return false;
        } else {
            try {
                Subject subject = this.find(subjectId);
                this.remove(subject);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }
    
    
}
