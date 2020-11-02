package app.entity;

import java.util.Date;

public class transfer {
    private int id;
    private int IdHousehold;
    private int IdPeople;
    private int IdOldAdress;
    private int IdNewAdress;
    private Date dateTransfer;
    private int category;

    public transfer(int id, int idHousehold, int idPeople, int idOldAdress, int idNewAdress, Date dateTransfer, int category) {
        this.id = id;
        IdHousehold = idHousehold;
        IdPeople = idPeople;
        IdOldAdress = idOldAdress;
        IdNewAdress = idNewAdress;
        this.dateTransfer = dateTransfer;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHousehold() {
        return IdHousehold;
    }

    public void setIdHousehold(int idHousehold) {
        IdHousehold = idHousehold;
    }

    public int getIdPeople() {
        return IdPeople;
    }

    public void setIdPeople(int idPeople) {
        IdPeople = idPeople;
    }

    public int getIdOldAdress() {
        return IdOldAdress;
    }

    public void setIdOldAdress(int idOldAdress) {
        IdOldAdress = idOldAdress;
    }

    public int getIdNewAdress() {
        return IdNewAdress;
    }

    public void setIdNewAdress(int idNewAdress) {
        IdNewAdress = idNewAdress;
    }

    public Date getDateTransfer() {
        return dateTransfer;
    }

    public void setDateTransfer(Date dateTransfer) {
        this.dateTransfer = dateTransfer;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
