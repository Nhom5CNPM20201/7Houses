package app.entity;

import java.util.Date;

public class Contribute {

    private int id;
    private int idFee;
    private int idHouseHold;
    private int amount;
    private Date createDate;
    private String note;

    private Fee fee;
    private HouseHold houseHold;

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        if (fee == null) return;
        this.fee = fee;
        this.setIdFee(fee.getId());
    }

    public HouseHold getHouseHold() {
        return houseHold;
    }

    public void setHouseHold(HouseHold houseHold) {
        if (houseHold == null) return;
        this.houseHold = houseHold;
        this.setIdHouseHold(houseHold.getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFee() {
        return idFee;
    }

    public void setIdFee(int idFee) {
        this.idFee = idFee;
    }

    public int getIdHouseHold() {
        return idHouseHold;
    }

    public void setIdHouseHold(int idHouseHold) {
        this.idHouseHold = idHouseHold;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Contribute() {

    }

}
