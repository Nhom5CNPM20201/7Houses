package app.entity;

public class accountFee {
    private int Id;
    private int category;
    private String tenFee;
    private double money;
    private String information;

    public accountFee(int id, int category, String tenFee, double money, String information) {
        Id = id;
        this.category = category;
        this.tenFee = tenFee;
        this.money = money;
        this.information = information;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTenFee() {
        return tenFee;
    }

    public void setTenFee(String tenFee) {
        this.tenFee = tenFee;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}

