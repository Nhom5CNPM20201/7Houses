package app.entity;

import java.util.Date;

public class temporaryResidence {
    private int IdPeople;
    private Date time;
    private Date start;
    private int duration;
    private int category;
    private String information;

    public temporaryResidence(int idPeople, Date time, Date start, int duration, int category, String information) {
        IdPeople = idPeople;
        this.time = time;
        this.start = start;
        this.duration = duration;
        this.category = category;
        this.information = information;
    }

    public int getIdPeople() {
        return IdPeople;
    }

    public void setIdPeople(int idPeople) {
        IdPeople = idPeople;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
