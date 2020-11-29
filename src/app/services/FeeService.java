package app.services;

import app.database.MSSQLDatabase;
import app.entity.Fee;

import java.util.List;

public class FeeService {
    private Fee fee;
    public FeeService() {

    }

    public void createFee(Fee fee) {
        try {
            Fee searchA = MSSQLDatabase.getInstance().searchFee(fee.getId());
            if (searchA == null) {
                MSSQLDatabase.getInstance().insertFee(fee);
                System.out.println("Them phi thanh cong!");
            } else {
                System.out.println("Da ton tai khoan phi!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllFee() {
        try {
            List<Fee> fee = MSSQLDatabase.getInstance().getAllFee();
            for (Fee i : fee) {
                System.out.println(i.getId() + "\t " + i.getCategory() + ", " + i.getNameFee() + ", " + i.getMoney()
                        + ", " + i.getOtherInformation());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchFee(int id) {
        try {

            Fee searchF = MSSQLDatabase.getInstance().searchFee(id);
            if (searchF != null)
                System.out.println(searchF.getId() + "\t " + searchF.getCategory() + ", " + searchF.getNameFee() + ", " + searchF.getMoney()
                        + ", " + searchF.getOtherInformation());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFee(int id) {
        try {
            Fee searchF = MSSQLDatabase.getInstance().searchFee(id);
            if (searchF != null) {
                MSSQLDatabase.getInstance().removeFee(id);
                System.out.println("Xoa khoan phi thanh cong!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //Test
    public static void main(String[] args) {
        FeeService f = new FeeService();
        Fee fee = new Fee(2000, 1, "20/11", 10,"ngay nha giao VN");
        f.searchFee(82);
        f.createFee(fee);
        f.getAllFee();
    }
}
