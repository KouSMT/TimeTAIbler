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
public class StudentDTO {

    private final String studentid;
    private final String name;
    private final String email;

    public StudentDTO(String studentid, String name, String email) {
        this.studentid = studentid;
        this.name = name;
        this.email = email;
    }

    public String getStudentid() {
        return studentid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
