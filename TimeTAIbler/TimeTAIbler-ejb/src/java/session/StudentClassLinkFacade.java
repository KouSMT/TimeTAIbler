/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.StudentClassLink;
import entity.StudentClassLinkDTO;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dennis
 */
@Stateless
public class StudentClassLinkFacade implements StudentClassLinkFacadeRemote {

    @PersistenceContext(unitName = "TimeTAIbler-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    private void create(StudentClassLink studentClasslink) {
        em.persist(studentClasslink);
    }

    private void remove(StudentClassLink studentClasslink) {
        em.remove(em.merge(studentClasslink));
    }

    private StudentClassLink find(Object id) {
        return em.find(StudentClassLink.class, id);
    }
    
    private StudentClassLink myDTO2DAO(StudentClassLinkDTO studentClassLinkDTO) {
        StudentClassLink studentClasslink = new StudentClassLink();
        studentClasslink.setSclinkid(studentClassLinkDTO.getSclinkid());
        studentClasslink.setClassid(studentClassLinkDTO.getClassid());
        studentClasslink.setStudentid(studentClassLinkDTO.getStudentid());
        return studentClasslink;
    }
    
    private StudentClassLinkDTO myDAO2DTO(StudentClassLink studentClasslink) {
        return new StudentClassLinkDTO(studentClasslink.getSclinkid(), studentClasslink.getStudentid(), studentClasslink.getClassid());
    }

    @Override
    public boolean createRecord(StudentClassLinkDTO studentClassLinkDTO) {
        if (find(studentClassLinkDTO.getSclinkid()) != null) {
            return false;
        }
        
        try {
            StudentClassLink studentClasslink = this.myDTO2DAO(studentClassLinkDTO);
            this.create(studentClasslink);
            return true;
        } catch (Exception ex) {
        return false;
        }
    }

    @Override
    public StudentClassLinkDTO getRecord(String sclinkId) {
        StudentClassLink studentclasslink = find(sclinkId);
        return this.myDAO2DTO(studentclasslink);
    }

    @Override
    public boolean deleteRecord(String sclinkId) {
        if (find(sclinkId) == null) {
            return false;
        } else {
            try {
                StudentClassLink studentclasslink = this.find(sclinkId);
                this.remove(studentclasslink);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }

    @Override
    public ArrayList<StudentClassLinkDTO> findStudentsInClass(String classId) {
        javax.persistence.Query query;
        query = em.createNamedQuery("StudentClassLink.findByClassid").setParameter("classid", classId);
        ArrayList<StudentClassLink> daoList = (ArrayList<StudentClassLink>) query.getResultList();
        ArrayList<StudentClassLinkDTO> dtoList = null;
        for (StudentClassLink scLink : daoList)
        {
            dtoList.add(myDAO2DTO(scLink));
        }
        return dtoList;
    }
    
    
    
}
