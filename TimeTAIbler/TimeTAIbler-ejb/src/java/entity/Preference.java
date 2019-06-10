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
@Table(name = "PREFERENCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preference.findAll", query = "SELECT p FROM Preference p")
    , @NamedQuery(name = "Preference.findByPreferenceid", query = "SELECT p FROM Preference p WHERE p.preferenceid = :preferenceid")
    , @NamedQuery(name = "Preference.findByStudentid", query = "SELECT p FROM Preference p WHERE p.studentid = :studentid")
    , @NamedQuery(name = "Preference.findByDay", query = "SELECT p FROM Preference p WHERE p.day = :day")
    , @NamedQuery(name = "Preference.findByStarttime", query = "SELECT p FROM Preference p WHERE p.starttime = :starttime")
    , @NamedQuery(name = "Preference.findByHours", query = "SELECT p FROM Preference p WHERE p.hours = :hours")})
public class Preference implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "PREFERENCEID")
    private String preferenceid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "STUDENTID")
    private String studentid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DAY")
    private short day;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STARTTIME")
    @Temporal(TemporalType.TIME)
    private Date starttime;
    @Basic(optional = false)
    @Column(name = "HOURS")
    private int hours;

    public Preference() {
    }

    public Preference(String preferenceid) {
        this.preferenceid = preferenceid;
    }

    public Preference(String preferenceid, String studentid, short day, Date starttime, int hours) {
        this.preferenceid = preferenceid;
        this.studentid = studentid;
        this.day = day;
        this.starttime = starttime;
        this.hours = hours;
    }

    public String getPreferenceid() {
        return preferenceid;
    }

    public void setPreferenceid(String preferenceid) {
        this.preferenceid = preferenceid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public short getDay() {
        return day;
    }

    public void setDay(short day) {
        this.day = day;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preferenceid != null ? preferenceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preference)) {
            return false;
        }
        Preference other = (Preference) object;
        if ((this.preferenceid == null && other.preferenceid != null) || (this.preferenceid != null && !this.preferenceid.equals(other.preferenceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Preference[ preferenceid=" + preferenceid + " ]";
    }
    
}
