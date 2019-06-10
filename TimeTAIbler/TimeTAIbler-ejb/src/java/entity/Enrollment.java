/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dennis
 */
@Entity
@Table(name = "ENROLLMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enrollment.findAll", query = "SELECT e FROM Enrollment e")
    , @NamedQuery(name = "Enrollment.findByEnrollmentid", query = "SELECT e FROM Enrollment e WHERE e.enrollmentid = :enrollmentid")
    , @NamedQuery(name = "Enrollment.findByStudentid", query = "SELECT e FROM Enrollment e WHERE e.studentid = :studentid")
    , @NamedQuery(name = "Enrollment.findBySubjectid", query = "SELECT e FROM Enrollment e WHERE e.subjectid = :subjectid")})
public class Enrollment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ENROLLMENTID")
    private String enrollmentid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STUDENTID")
    private String studentid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "SUBJECTID")
    private String subjectid;

    public Enrollment() {
    }

    public Enrollment(String enrollmentid) {
        this.enrollmentid = enrollmentid;
    }

    public Enrollment(String enrollmentid, String studentid, String subjectid) {
        this.enrollmentid = enrollmentid;
        this.studentid = studentid;
        this.subjectid = subjectid;
    }

    public String getEnrollmentid() {
        return enrollmentid;
    }

    public void setEnrollmentid(String enrollmentid) {
        this.enrollmentid = enrollmentid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enrollmentid != null ? enrollmentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enrollment)) {
            return false;
        }
        Enrollment other = (Enrollment) object;
        if ((this.enrollmentid == null && other.enrollmentid != null) || (this.enrollmentid != null && !this.enrollmentid.equals(other.enrollmentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Enrollment[ enrollmentid=" + enrollmentid + " ]";
    }
    
}
