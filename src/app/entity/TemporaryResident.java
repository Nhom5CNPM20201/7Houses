package app.entity;

import java.sql.Date;

public class TemporaryResident {
    int idPeople;
    int idAddress;
    Date time;
    Date start;
    int duration;
    int cagetory;
    String information;

    public TemporaryResident(int idPeople, int idAddress, Date time, Date start, int duration, int cagetory, String information) {
        this.idPeople = idPeople;
        this.idAddress = idAddress;
        this.time = time;
        this.start = start;
        this.duration = duration;
        this.cagetory = cagetory;
        this.information = information;
    }

    public TemporaryResident(int idAddress, Date time, Date start, int duration, int cagetory, String information) {
        this.idAddress = idAddress;
        this.time = time;
        this.start = start;
        this.duration = duration;
        this.cagetory = cagetory;
        this.information = information;
    }

    public int getIdPeople() {
        return idPeople;
    }

    public void setIdPeople(int idPeople) {
        this.idPeople = idPeople;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
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

    public int getCagetory() {
        return cagetory;
    }

    public void setCagetory(int cagetory) {
        this.cagetory = cagetory;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}

