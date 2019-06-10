/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dennis
 */
@Entity
@Table(name = "CLASS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Class.findAll", query = "SELECT c FROM Class c")
    , @NamedQuery(name = "Class.findByClassid", query = "SELECT c FROM Class c WHERE c.classid = :classid")
    , @NamedQuery(name = "Class.findBySubjectid", query = "SELECT c FROM Class c WHERE c.subjectid = :subjectid")
    , @NamedQuery(name = "Class.findByClasstype", query = "SELECT c FROM Class c WHERE c.classtype = :classtype")
    , @NamedQuery(name = "Class.findByStarttime", query = "SELECT c FROM Class c WHERE c.starttime = :starttime")
    , @NamedQuery(name = "Class.findByHours", query = "SELECT c FROM Class c WHERE c.hours = :hours")
    , @NamedQuery(name = "Class.findByDay", query = "SELECT c FROM Class c WHERE c.day = :day")
    , @NamedQuery(name = "Class.findByDayAndTime", query = 
            "SELECT c FROM Class c WHERE " + 
                    " c.subjectid = :subjectid AND " +
                    " c.day = :day AND " +
                    " c.starttime <= :starttime AND " +
                    " DATEADD(hour, c.hours, c.starttime) >= DATEADD(hour, :hours, :starttime)")})
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "CLASSID")
    private String classid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "SUBJECTID")
    private String subjectid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASSTYPE")
    private Character classtype;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STARTTIME")
    @Temporal(TemporalType.TIME)
    private Date starttime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HOURS")
    private int hours;
    @Column(name = "DAY")
    private Short day;

    public Class() {
    }

    public Class(String classid) {
        this.classid = classid;
    }

    public Class(String classid, String subjectid, Character classtype, Date starttime, int hours) {
        this.classid = classid;
        this.subjectid = subjectid;
        this.classtype = classtype;
        this.starttime = starttime;
        this.hours = hours;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public Character getClasstype() {
        return classtype;
    }

    public void setClasstype(Character classtype) {
        this.classtype = classtype;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Short getDay() {
        return day;
    }

    public void setDay(Short day) {
        this.day = day;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classid != null ? classid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Class)) {
            return false;
        }
        Class other = (Class) object;
        if ((this.classid == null && other.classid != null) || (this.classid != null && !this.classid.equals(other.classid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Class[ classid=" + classid + " ]";
    }
    
}
