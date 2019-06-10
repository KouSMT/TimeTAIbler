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
@Table(name = "STUDENT_CLASS_LINK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentClassLink.findAll", query = "SELECT s FROM StudentClassLink s")
    , @NamedQuery(name = "StudentClassLink.findBySclinkid", query = "SELECT s FROM StudentClassLink s WHERE s.sclinkid = :sclinkid")
    , @NamedQuery(name = "StudentClassLink.findByStudentid", query = "SELECT s FROM StudentClassLink s WHERE s.studentid = :studentid")
    , @NamedQuery(name = "StudentClassLink.findByClassid", query = "SELECT s FROM StudentClassLink s WHERE s.classid = :classid")})
public class StudentClassLink implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "SCLINKID")
    private String sclinkid;
    @Size(max = 6)
    @Column(name = "STUDENTID")
    private String studentid;
    @Size(max = 8)
    @Column(name = "CLASSID")
    private String classid;

    public StudentClassLink() {
    }

    public StudentClassLink(String sclinkid) {
        this.sclinkid = sclinkid;
    }

    public String getSclinkid() {
        return sclinkid;
    }

    public void setSclinkid(String sclinkid) {
        this.sclinkid = sclinkid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sclinkid != null ? sclinkid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentClassLink)) {
            return false;
        }
        StudentClassLink other = (StudentClassLink) object;
        if ((this.sclinkid == null && other.sclinkid != null) || (this.sclinkid != null && !this.sclinkid.equals(other.sclinkid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.StudentClassLink[ sclinkid=" + sclinkid + " ]";
    }
    
}
