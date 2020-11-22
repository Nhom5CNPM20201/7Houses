package app.services;

import app.database.MSSQLDatabase;
import app.entity.Address;

import java.util.List;

public class AddressService {
    private Address address;

    public AddressService() {

    }


    public void createAddress(Address address) {
        try {
            Address searchA = MSSQLDatabase.getInstance().searchAddress(address.getId());
            if (searchA == null) {
                MSSQLDatabase.getInstance().insertAddress(address);
                System.out.println("Tao dia chi thanh cong!");
            } else {
                System.out.println("Da ton tai dia chi!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllAddress() {
        try {
            List<Address> address = MSSQLDatabase.getInstance().getAllAddress();
            for (Address i : address) {
                System.out.println(i.getId() + "\t " + i.getNumberHouse() + ", " + i.getStreet() + ", " + i.getSubDistrict()
                        + ", " + i.getDistrict() + ", " + i.getCity());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchAddress(int id) {
        try {

            Address searchA = MSSQLDatabase.getInstance().searchAddress(id);
            if (searchA != null)
                System.out.println(searchA.getId() + "\t " + searchA.getNumberHouse() + ", " + searchA.getStreet() + ", " + searchA.getSubDistrict()
                        + ", " + searchA.getDistrict() + ", " + searchA.getCity());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAddress(int id) {
        try {
            Address searchA = MSSQLDatabase.getInstance().searchAddress(id);
            if (searchA != null) {
                MSSQLDatabase.getInstance().removeAddress(id);
                System.out.println("Xoa dia chi thanh cong!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //Test
    public static void main(String[] args) {
        AddressService aService = new AddressService();
        Address address = new Address(6, 5, "MgOH2", "C2H2OH", "CH3Cl", "H2O");
        aService.createAddress(address);
        aService.searchAddress(2);
        aService.deleteAddress(10);
        aService.getAllAddress();
    }
}