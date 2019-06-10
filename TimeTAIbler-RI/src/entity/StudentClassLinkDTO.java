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
public class StudentClassLinkDTO {

    private final String sclinkid;
    private final String studentid;
    private final String classid;

    public StudentClassLinkDTO(String sclinkid, String studentid, String classid) {
        this.sclinkid = sclinkid;
        this.studentid = studentid;
        this.classid = classid;
    }

    public String getSclinkid() {
        return sclinkid;
    }

    public String getStudentid() {
        return studentid;
    }

    public String getClassid() {
        return classid;
    }

}
