package app.entity;

public class Address {
    private int id;
    private int numberHouse;
    private String street;
    private String subDistrict;
    private String District;
    private String city;
    private String information;

    public Address(int id, int numberHouse, String street, String subDistrict, String district, String city, String information) {
        this.id = id;
        this.numberHouse = numberHouse;
        this.street = street;
        this.subDistrict = subDistrict;
        District = district;
        this.city = city;
        this.information = information;
    }

    public Address(int numberHouse, String street, String subDistrict, String district, String city, String information) {
        this.numberHouse = numberHouse;
        this.street = street;
        this.subDistrict = subDistrict;
        this.District = district;
        this.city = city;
        this.information = information;
    }
    public String getDetail(){
        return "Số "+numberHouse+", đường "+street+", Phường "+subDistrict+", Quận "+District+", Thành phố "+city;
    }

    public Address() {

    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(int numberHouse) {
        this.numberHouse = numberHouse;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}


