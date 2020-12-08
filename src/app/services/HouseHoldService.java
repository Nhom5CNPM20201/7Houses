package app.services;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import app.database.MSSQLDatabase;
import app.database.config.HouseHoldConfig;
import app.entity.HouseHold;
import app.helper.StringHelper;
import app.services.common.LogService;
import app.services.common.NotiService;
import jdk.jshell.spi.ExecutionControl;

public class HouseHoldService {
	private static MSSQLDatabase orm;
	private HouseHold houseHold;

	private List<HouseHold> houseHoldList = new ArrayList<HouseHold>();
	
	public HouseHoldService() {
		Init();
	}

	public void Init() {
		try {
			this.houseHoldList = MSSQLDatabase.getInstance().getAllHouseHold();

			for (var houseHold : houseHoldList) {
				var address = ServiceFactory.getAddressService().searchAddress(houseHold.getIdAddress());
				houseHold.setAddressDetail(address.getDetail());
			}
		} catch (Exception e) {
			LogService.error(e.getMessage());
		}
	}
	
	public HouseHold createHouseHold(HouseHold houseHold) {
		try {
			orm = MSSQLDatabase.getInstance();
			HouseHold searchHH = orm.searchHouseHold(houseHold.getHouseHoldBook());
			if(searchHH == null) {
				houseHold.setId(houseHoldList.size() + 1);
				houseHoldList.add(houseHold);
				orm.insertHouseHold(houseHold);
				NotiService.info("Tao thanh cong.");
				return houseHold;
			}
			else {
				NotiService.info("Da ton tai ho khau!");
				return null;
			}
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public List<HouseHold> getAllHouseHold() {
		return this.houseHoldList;
	}

	public int countAllHouseHold() {
		try {
			int count = MSSQLDatabase.getInstance().countAllHouseHold();
			return count;
		} catch(Exception e) {
			LogService.error(e.getMessage());
			return 0;
		}
	}

	public List<HouseHold> searchHouseHoldFull(String query) {
		List<HouseHold> searchedHouseHolds = this.houseHoldList.stream()
				.filter(h ->
					 StringHelper.containNormalString(h.getHouseHoldBook(), query)
					 || StringHelper.containNormalString(h.getName(), query)
					 || StringHelper.containNormalString(h.getAddressDetail(), query)
				).collect(Collectors.toList());
		return searchedHouseHolds;
	}
	
	public void searchHouseHold(String houseHoldBook) {
		try {
			orm = MSSQLDatabase.getInstance();
			HouseHold searchHH = orm.searchHouseHold(houseHoldBook);
			if(searchHH != null) System.out.println(searchHH.getHouseHoldBook() + "\t" + searchHH.getName());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteHouseHold(String houseHoldBook) {
		try {
			orm = MSSQLDatabase.getInstance();
			HouseHold searchHH = orm.searchHouseHold(houseHoldBook);
			if(searchHH != null) {
				orm.removeHouseHold(searchHH);
				System.out.println("Xoa thanh cong!");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateHouseHold() {
		
	}
	
//	Test Service
/*
	public static void main(String[] args) {
		HouseHoldService hService = new HouseHoldService();
//		HouseHold houseHold = new HouseHold(4, "LK104", 3, "@@@");
//		hService.createHouseHold(houseHold);
//		hService.searchHouseHold("LK104");
//		hService.deleteHouseHold("LK104");
//		hService.getAllHouseHold();
	} 
*/
}
