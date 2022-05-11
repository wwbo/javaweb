package com.pojo;

import java.sql.Time;
import java.util.Date;

public class AttendanceSetting {
    private Date starttime;
    private Date lateststarttime;
    private Date endtime;
    private String start;
    private String end;
    private String latest;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getLateststarttime() {
        return lateststarttime;
    }

    public void setLateststarttime(Date lateststarttime) {
        this.lateststarttime = lateststarttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}
