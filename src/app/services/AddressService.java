package app.services;

import app.database.MSSQLDatabase;
import app.entity.Address;
import app.services.common.LogService;

import java.util.ArrayList;
import java.util.List;

public class AddressService {
    private Address address;
    private List<Address> addressList = new ArrayList<>();

    public AddressService() {
        InitAddress();
    }

    public Address createAddress(Address address) {
        try {
            if (address.getId() > 0 && addressList.stream().anyMatch(a -> a.getId() == address.getId())) {
                return address;
            }
            address.setId(addressList.size() + 1);
            MSSQLDatabase.getInstance().insertAddress(address);
            addressList.add(address);
            LogService.info("Tao dia chi thanh cong: " + address.getId());
            return address;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void InitAddress() {
        try {
            addressList = MSSQLDatabase.getInstance().getAllAddress();
            for (Address i : addressList) {
                System.out.println(i.getId() + "\t " + i.getNumberHouse() + ", " + i.getStreet() + ", " + i.getSubDistrict()
                        + ", " + i.getDistrict() + ", " + i.getCity());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Address getAddress(int id) {
        return addressList.stream().filter(x -> x.getId() == id).findFirst().get();
    }

    public Address searchAddress(int id) {
        try {
            Address searchA = addressList.stream().filter(x -> x.getId() == id).findFirst().get();

            return searchA;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
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
        Address address = new Address(5, "hhh", "MgOH2", "C2H2OH", "CH3Cl", "H2O");
        aService.createAddress(address);
        aService.searchAddress(2);
        aService.deleteAddress(1);
        aService.InitAddress();
    }
}