package app.services;

import app.database.MSSQLDatabase;
import app.entity.Fee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeeService {
    private Fee fee;
    private List<Fee> feeList = new ArrayList<>();
    public FeeService() {
        InitAllFee();
    }

    public Fee createFee(Fee fee) {
        try {
            MSSQLDatabase.getInstance().insertFee(fee);
            fee.setId(feeList.toArray().length);
            feeList.add(fee);
            return fee;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void InitAllFee() {
        try {
            feeList = MSSQLDatabase.getInstance().getAllFee();
            for (Fee i : feeList) {
                System.out.println(i.getId() + "\t " + i.getCategory() + ", " + i.getNameFee() + ", " + i.getMoney()
                        + ", " + i.getOtherInformation());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Fee searchFee(int id) {
        try {

            Fee searchF = MSSQLDatabase.getInstance().searchFee(id);
            if (searchF != null)
                System.out.println(searchF.getId() + "\t " + searchF.getCategory() + ", " + searchF.getNameFee() + ", " + searchF.getMoney()
                        + ", " + searchF.getOtherInformation());
            return searchF;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Fee> getAllFee() {
        return feeList;
    }

    public List<String> getAllFeeDetail() {
        List<String> feeDetails = new ArrayList<>();
        feeList.forEach(x -> {
            feeDetails.add(x.getDetail());
        });

        return feeDetails;
    }

    public Fee getFee(int id) {
        return feeList.stream().filter(x -> x.getId() == id).findFirst().get();
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
        Fee fee = new Fee(1, "11/11", 10,"ngay nha giao VN");
        f.createFee(fee);
        f.searchFee(3);
    }
}
