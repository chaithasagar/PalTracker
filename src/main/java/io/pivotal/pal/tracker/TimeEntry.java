package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.Objects;

public class TimeEntry {

    long projectId;
    long userId;
    LocalDate date;
    long id;
    int hours;

    public TimeEntry(){

    }

    public TimeEntry(long projectId, long userId, LocalDate parse, int i) {

        this.projectId = projectId;
        this.userId = userId;
        this.date = parse;
        this.hours = i;
    }

    public TimeEntry(long timeEntryId, long projectId, long userId, LocalDate parse, int i) {

        this.projectId = projectId;
        this.userId = userId;
        this.date = parse;
        this.id = timeEntryId;
        this.hours = i;

    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals(Object obj) {
        TimeEntry time = (TimeEntry) obj;
        if(this.id == time.getId() && this.projectId == time.getProjectId() && this.hours == time.getHours()
        && this.userId == time.getUserId()){
            return true;
        }
       return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, hours,userId);
    }

    @Override
    public String toString() {
        return "TimeEntry{" +
                "projectId=" + projectId +
                ", userId=" + userId +
                ", date=" + date +
                ", id=" + id +
                ", hours=" + hours +
                '}';
    }
}
