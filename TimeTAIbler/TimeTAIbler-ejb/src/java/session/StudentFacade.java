/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Student;
import entity.StudentDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dennis
 */
@Stateless
public class StudentFacade implements StudentFacadeRemote {

    @PersistenceContext(unitName = "TimeTAIbler-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(Student student) {
        em.persist(student);
    }

    private void remove(Student student) {
        em.remove(em.merge(student));
    }

    private Student find(Object id) {
        return em.find(Student.class, id);
    }

    @Override
    public boolean createRecord(StudentDTO studentDTO) {
        if (find(studentDTO.getStudentid()) != null) {
            return false;
        }
        
        try {
            Student student = this.myDTO2DAO(studentDTO);
            this.create(student);
            return true;
        } catch (Exception ex) {
        return false;
        }
    }
    
    private Student myDTO2DAO(StudentDTO studentDTO) {
        Student student = new Student();
        student.setStudentid(studentDTO.getStudentid());
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        return student;
    }
    
    private StudentDTO myDAO2DTO(Student student) {
        return new StudentDTO(student.getStudentid(), student.getName(), student.getEmail());
    }

    @Override
    public StudentDTO getRecord(String studentId) {
        Student student = find(studentId);
        return this.myDAO2DTO(student);
    }

    @Override
    public boolean deleteRecord(String studentId) {
        if (find(studentId) == null) {
            return false;
        } else {
            try {
                Student student = this.find(studentId);
                this.remove(student);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }
    
    
}
