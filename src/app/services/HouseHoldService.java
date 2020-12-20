package app.services;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Collectors;

import app.component.dashboard.householdManage.HouseholdList;
import app.database.MSSQLDatabase;
import app.entity.HouseHold;
import app.entity.Move;
import app.helper.DateHelper;
import app.helper.StringHelper;
import app.services.common.LogService;
import app.services.common.NotiService;


public class HouseHoldService {
	private static MSSQLDatabase orm;

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
				houseHold.setCreatedDate(new Date());
				houseHold.setId(houseHoldList.size() + 1);
				houseHoldList.add(houseHold);
				orm.insertHouseHold(houseHold);
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

	public HouseHold updateHouseHold(HouseHold houseHold) {
		try {
			HouseHold searchHH = MSSQLDatabase.getInstance().searchHouseHold(houseHold.getHouseHoldBook());
			if(searchHH != null) {
				int index = this.findIndex(houseHold.getHouseHoldBook());
				houseHoldList.set(index, houseHold);
				MSSQLDatabase.getInstance().updateHouseHold(houseHold);
				return houseHold;
			}
			else {
				throw new Exception("Không tồn tại hộ khẩu!");
			}
		} catch (Exception e) {
			LogService.error(e.getMessage());
			return null;
		}
	}
	
	public List<HouseHold> getAllHouseHold() {
		return this.houseHoldList;
	}

	public HouseHold getHouseHold(int id) {
		return houseHoldList.stream().filter(x -> x.getId() == id).findFirst().get();
	}

	private int findIndex(String houseHoldNo) {
		int i = 0;
		for (var h : houseHoldList) {
			if (h.getHouseHoldBook().equals(houseHoldNo)) return i;
			i++;
		}

		return -1;
	}

	public long countAllHouseHold() {
		return houseHoldList.stream().count();
	}

	public long countNewHouseHold() {
		return houseHoldList.stream().filter(x ->
				DateHelper.getDaysBetween(x.getCreatedDate(), new Date()) < 30
		).count();
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
			HouseHold searchHH = MSSQLDatabase.getInstance().searchHouseHold(houseHoldBook);
			if(searchHH != null) {
				orm.removeHouseHold(searchHH);
				System.out.println("Xoa thanh cong!");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void moveHouseHold(Move move) {
		try {
			HouseHold houseHold = this.getHouseHold(move.getIdHouseHold());

			houseHold.setIdAddress(move.getIdNewAddress());
			houseHold.setAddressDetail(ServiceFactory.getAddressService().searchAddress(move.getIdNewAddress()).getDetail());

			MSSQLDatabase.getInstance().updateHouseHold(houseHold);
		} catch (Exception e) {
			LogService.error(e.getMessage());
		}
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
