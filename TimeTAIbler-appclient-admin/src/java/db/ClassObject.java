/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Date;

/**
 *
 * @author Dennis
 */
public class ClassObject {
    
    private String classid;
    private String subjectid;
    private char classtype;
    private Date startTime;
    private int hours;
    private short day;

    public ClassObject(String classid, String subjectid, char classtype, Date startTime, int hours, short day) {
        this.classid = classid;
        this.subjectid = subjectid;
        this.classtype = classtype;
        this.startTime = startTime;
        this.hours = hours;
        this.day = day;
    }

    public String getClassid() {
        return classid;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public char getClasstype() {
        return classtype;
    }

    public Date getStartTime() {
        return startTime;
    }

    public int getHours() {
        return hours;
    }

    public short getDay() {
        return day;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setDay(short day) {
        this.day = day;
    }
}
