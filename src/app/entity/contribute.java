package app.entity;

import java.util.Date;

public class contribute {
    private int id;
    private int IdFee;
    private int IdHousehold;
    private double amountOfMoney;
    private Date time;
    private String information;

    public contribute(int id, int idFee, int idHousehold, double amountOfMoney, Date time, String information) {
        this.id = id;
        IdFee = idFee;
        IdHousehold = idHousehold;
        this.amountOfMoney = amountOfMoney;
        this.time = time;
        this.information = information;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFee() {
        return IdFee;
    }

    public void setIdFee(int idFee) {
        IdFee = idFee;
    }

    public int getIdHousehold() {
        return IdHousehold;
    }

    public void setIdHousehold(int idHousehold) {
        IdHousehold = idHousehold;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
