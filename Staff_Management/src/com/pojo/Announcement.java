package com.pojo;

import java.util.Date;

public class Announcement {
    private int id;
    private String title;
    private Date releaseTime;
    private String roleToSee;
    private String content;
    private String author;
    private String landingPopUp;
    private int eid;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getLandingPopUp() {
        return landingPopUp;
    }

    public void setLandingPopUp(String landingPopUp) {
        this.landingPopUp = landingPopUp;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getRoleToSee() {
        return roleToSee;
    }

    public void setRoleToSee(String roleToSee) {
        this.roleToSee = roleToSee;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseTime=" + releaseTime +
                ", roleToSee='" + roleToSee + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", landingPopUp='" + landingPopUp + '\'' +
                '}';
    }
}
