package app.services;

import app.database.MSSQLDatabase;
import app.entity.Contribute;
import app.services.common.LogService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContributeService {
    private List<Contribute> contributeList = new ArrayList<>();

    public ContributeService() {
        InitAllContribute();
    }

    private void InitAllContribute() {
        try {
            contributeList = MSSQLDatabase.getInstance().getAllContribute();
            for (var item : contributeList) {
                item.setHouseHold(ServiceFactory.getHouseHoldService().getHouseHold(item.getIdHouseHold()));
                item.setFee(ServiceFactory.getFeeService().getFee(item.getIdFee()));
            }
        } catch(Exception e) {
            LogService.error(e.getMessage());
        }
    }

    public List<Contribute> getAllContributes() {
        return this.contributeList;
    }

    public Contribute createContribute(Contribute contribute) {
        try {
            contribute.setCreateDate(new Date());
            MSSQLDatabase.getInstance().insertContribute(contribute);
            contribute.setId(contributeList.size() + 1);
            contributeList.add(contribute);
            return contribute;
        } catch (Exception e) {
            LogService.error(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        ServiceFactory.Init();
        Contribute contribute = new Contribute();
        contribute.setIdFee(1);
        contribute.setIdHouseHold(2);
        contribute.setAmount(16000);
        contribute.setNote("Đây là thông tin thêm về đóng góp của một hộ nào đó");

        //contributeService.createContribute(contribute);

        for (var item : ServiceFactory.getContributeService().getAllContributes()) {
            LogService.info(item.getHouseHold().getName());
        }
    }
}
