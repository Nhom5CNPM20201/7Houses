package app.services;

import app.database.MSSQLDatabase;
import app.entity.Address;
import app.entity.TemporaryResident;

import java.util.ArrayList;
import java.util.List;

public class TemporaryResidentService {
    private TemporaryResident ts;
    private List<TemporaryResident> tSList = new ArrayList<>();
    public TemporaryResidentService() {
        InitTS();
    }


    public TemporaryResident createTS(TemporaryResident ts) {
        try {

            MSSQLDatabase.getInstance().insertTS(ts);
            tSList.add(ts);
            System.out.println("Tao thanh cong!");
            System.out.print(ts.getIdPeople());
            return ts;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void InitTS() {
        try {
            tSList = MSSQLDatabase.getInstance().getAllTS();
            for (TemporaryResident i : tSList) {
                System.out.println(i.getIdPeople() + "\t " + i.getIdAddress() + ", " + i.getTime() + ", " + i.getStart()
                        + ", " + i.getDuration() + ", " + i.getCagetory());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public TemporaryResident searchTS(int id) {
        try {

            TemporaryResident searchTS = MSSQLDatabase.getInstance().searchTS(id);
            if (searchTS != null)
            System.out.println(searchTS.getIdPeople() + "\t " + searchTS.getIdAddress());
            return searchTS;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void deleteTS(int id) {
        try {
            TemporaryResident searchA = MSSQLDatabase.getInstance().searchTS(id);
            if (searchA != null) {
                MSSQLDatabase.getInstance().removeTS(id);
                System.out.println("Xoa thanh cong!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        TemporaryResidentService tsService = new TemporaryResidentService();
        tsService.InitTS();
    }
}
