/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;


/**
 *
 * @author Dennis
 */
public class PreferenceDTO {

    private final String preferenceid;
    private final String studentid;
    private short day;
    private Date starttime;
    private int hours;

    public PreferenceDTO(String preferenceid, String studentid, short day, Date starttime, int hours) {
        this.preferenceid = preferenceid;
        this.studentid = studentid;
        this.day = day;
        this.starttime = starttime;
        this.hours = hours;
    }

    public String getPreferenceid() {
        return preferenceid;
    }

    public String getStudentid() {
        return studentid;
    }

    public short getDay() {
        return day;
    }

    public Date getStarttime() {
        return starttime;
    }
    
    public int getHours() {
        return hours;
    }

    public void setDay(short day) {
        this.day = day;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }
    
    public void setHours(int hours) {
        this.hours = hours;
    }

}
