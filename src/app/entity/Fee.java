package app.entity;

public class Fee {
    private int id;
    private int category;
    private String nameFee;
    private int money;
    private String otherInformation;

    public Fee(int id, int category, String nameFee, int money, String otherInformation) {
        this.id = id;
        this.category = category;
        this.nameFee = nameFee;
        this.money = money;
        this.otherInformation = otherInformation;
    }

    public Fee(int category, String nameFee, int money, String otherInformation) {
        this.category = category;
        this.nameFee = nameFee;
        this.money = money;
        this.otherInformation = otherInformation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getNameFee() {
        return nameFee;
    }

    public void setNameFee(String nameFee) {
        this.nameFee = nameFee;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public String getDetail() {
        return this.nameFee + ": " + this.money + " đ";
    }
}
