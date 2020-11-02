package app.entity;

public class HouseHold {
    private int Id;
    private int IdAdress;
    private int IdBossHousehold;
    private String HouseholdBook;
    private String nameOfBossHousehold;

    public HouseHold(int id, int idAdress, int idBossHousehold, String householdBook, String nameOfBossHousehold) {
        Id = id;
        IdAdress = idAdress;
        IdBossHousehold = idBossHousehold;
        HouseholdBook = householdBook;
        this.nameOfBossHousehold = nameOfBossHousehold;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdAdress() {
        return IdAdress;
    }

    public void setIdAdress(int idAdress) {
        IdAdress = idAdress;
    }

    public int getIdBossHousehold() {
        return IdBossHousehold;
    }

    public void setIdBossHousehold(int idBossHousehold) {
        IdBossHousehold = idBossHousehold;
    }

    public String getHouseholdBook() {
        return HouseholdBook;
    }

    public void setHouseholdBook(String householdBook) {
        HouseholdBook = householdBook;
    }

    public String getNameOfBossHousehold() {
        return nameOfBossHousehold;
    }

    public void setNameOfBossHousehold(String nameOfBossHousehold) {
        this.nameOfBossHousehold = nameOfBossHousehold;
    }


}