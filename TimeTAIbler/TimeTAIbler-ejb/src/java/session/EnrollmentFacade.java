/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Enrollment;
import entity.EnrollmentDTO;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dennis
 */
@Stateless
public class EnrollmentFacade implements EnrollmentFacadeRemote {

    @PersistenceContext(unitName = "TimeTAIbler-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(Enrollment enrollment) {
        em.persist(enrollment);
    }
    
    private void remove(Enrollment enrollment) {
        em.remove(em.merge(enrollment));
    }

    private Enrollment find(Object id) {
        return em.find(Enrollment.class, id);
    }

    private Enrollment myDTO2DAO(EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentid(enrollmentDTO.getEnrollmentid());
        enrollment.setStudentid(enrollmentDTO.getStudentid());
        enrollment.setSubjectid(enrollmentDTO.getSubjectid());
        return enrollment;
    }
    
    private EnrollmentDTO myDAO2DTO(Enrollment enrollment) {
        return new EnrollmentDTO(enrollment.getEnrollmentid(), enrollment.getStudentid(), enrollment.getSubjectid());
    }

    @Override
    public boolean createRecord(EnrollmentDTO enrollmentDTO) {
        if (find(enrollmentDTO.getEnrollmentid()) != null) {
            return false;
        }
        
        try {
            Enrollment enrollment = this.myDTO2DAO(enrollmentDTO);
            this.create(enrollment);
            return true;
        } catch (Exception ex) {
        return false;
        }
    }

    @Override
    public EnrollmentDTO getRecord(String enrollmentId) {
        Enrollment enrollment = find(enrollmentId);
        return this.myDAO2DTO(enrollment);
    }

    @Override
    public boolean deleteRecord(String enrollmentId) {
        if (find(enrollmentId) == null) {
            return false;
        } else {
            try {
                Enrollment enrollment = this.find(enrollmentId);
                this.remove(enrollment);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }

    @Override
    public ArrayList<EnrollmentDTO> getEnrolledSubjects(String studentId) {
        javax.persistence.Query query;
        query = em.createNamedQuery("Enrollment.findByStudentid").setParameter("studentid", studentId);
        ArrayList<Enrollment> daoList = (ArrayList<Enrollment>) query.getResultList();
        ArrayList<EnrollmentDTO> dtoList = null;
        for (Enrollment enrollment : daoList)
        {
            dtoList.add(myDAO2DTO(enrollment));
        }
        return dtoList;
    }
    
    
}
