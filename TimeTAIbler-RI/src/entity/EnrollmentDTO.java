/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


/**
 *
 * @author Dennis
 */
public class EnrollmentDTO {
    private final String enrollmentid;
    private final String studentid;
    private final String subjectid;

    public EnrollmentDTO(String enrollmentid, String studentid, String subjectid) {
        this.enrollmentid = enrollmentid;
        this.studentid = studentid;
        this.subjectid = subjectid;
    }

    public String getEnrollmentid() {
        return enrollmentid;
    }

    public String getStudentid() {
        return studentid;
    }

    public String getSubjectid() {
        return subjectid;
    }

}
