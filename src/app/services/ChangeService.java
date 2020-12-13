package app.services;

import app.database.MSSQLDatabase;
import app.entity.Change;
import app.services.common.LogService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ChangeService {
    private Change change;
    private List<Change> changeList = new ArrayList<>();

    public ChangeService() {
        InitChange();
    }

    public Change createChange(Change change) {
        try {
            if (change.getId() > 0 && changeList.stream().anyMatch(a -> a.getId() == change.getId())) {
                return change;
            }
            change.setId(changeList.size() + 1);
            MSSQLDatabase.getInstance().insertChange(change);
            changeList.add(change);
            LogService.info("Tao thanh cong: " + change.getId());
            return change;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void InitChange() {
        try {
            changeList = MSSQLDatabase.getInstance().getAllChange();
            for (Change i : changeList) {
                System.out.println(i.getId() + "\t " + i.getIdPeople() + ", " + i.getIdHouseHold() + ", " + i.getChangingDate()
                        + ", " + i.getType() + ", " + i.getContent());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Change searchChange(int id) {
        try {
            Change searchA = changeList.stream().filter(x -> x.getId() == id).findFirst().get();

            return searchA;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void deleteChange(int id) {
        try {
            Change searchA = MSSQLDatabase.getInstance().searchChange(id);
            if (searchA != null) {
                MSSQLDatabase.getInstance().removeChange(id);
                System.out.println("Xoa thanh cong!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) throws ParseException {
        ChangeService cService = new ChangeService();
        Change change = new Change(2,1, "2000-11-20",1, "Hi");
        cService.createChange(change);
        cService.searchChange(8);
//        cService.deleteChange(1);
        cService.InitChange();
    }
}