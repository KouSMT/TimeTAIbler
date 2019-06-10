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
public class SubjectDTO {

    private final String subjectid;
    private final String name;

    public SubjectDTO(String subjectid, String name) {
        this.subjectid = subjectid;
        this.name = name;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public String getName() {
        return name;
    }

}
