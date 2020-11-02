package app.entity;

import java.util.Date;

public class People {
    private int id;
    private int idHouseHold;
    private String name;
    private String aliases;
    private String placeOfBirth;
    private String trade;
    private Date dateOfBirth;
    private Date dateRegister;
    private int publicRelationship;
    private String nationc;
    private String domicile;
    private int sex;
    private String wordkplace;
    private String CMND;
    private Date dateCMND;
    private String placeCMND;

    public People(int id, int idHouseHold, String name,
                     String aliases, String placeOfBirth,
                     String trade, Date dateOfBirth, Date dateRegister,
                     int publicRelationship, String nationc,
                     String domicile, int sex, String wordkplace,
                     String CMND, Date dateCMND, String placeCMND) {
        this.id = id;
        this.idHouseHold = idHouseHold;
        this.name = name;
        this.aliases = aliases;
        this.placeOfBirth = placeOfBirth;
        this.trade = trade;
        this.dateOfBirth = dateOfBirth;
        this.dateRegister = dateRegister;
        this.publicRelationship = publicRelationship;
        this.nationc = nationc;
        this.domicile = domicile;
        this.sex = sex;
        this.wordkplace = wordkplace;
        this.CMND = CMND;
        this.dateCMND = dateCMND;
        this.placeCMND = placeCMND;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHouseHold() {
        return idHouseHold;
    }

    public void setIdHouseHold(int idHouseHold) {
        this.idHouseHold = idHouseHold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public int getPublicRelationship() {
        return publicRelationship;
    }

    public void setPublicRelationship(int publicRelationship) {
        this.publicRelationship = publicRelationship;
    }

    public String getNationc() {
        return nationc;
    }

    public void setNationc(String nationc) {
        this.nationc = nationc;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getWordkplace() {
        return wordkplace;
    }

    public void setWordkplace(String wordkplace) {
        this.wordkplace = wordkplace;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public Date getDateCMND() {
        return dateCMND;
    }

    public void setDateCMND(Date dateCMND) {
        this.dateCMND = dateCMND;
    }

    public String getPlaceCMND() {
        return placeCMND;
    }

    public void setPlaceCMND(String placeCMND) {
        this.placeCMND = placeCMND;
    }
}
