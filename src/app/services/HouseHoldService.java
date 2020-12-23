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
			HouseHold searchHH = MSSQLDatabase.getInstance().searchHouseHold(houseHold.getHouseHoldBook());
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
			LogService.error(e.getMessage());
			return null;
		}
	}

	public HouseHold updateHouseHold(HouseHold houseHold) {
		try {
			HouseHold searchHH = this.getHouseHold(houseHold.getId());
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

	public List<HouseHold> getNewHouseHold() {
		return houseHoldList.stream().filter(x ->
				DateHelper.getDaysBetween(x.getCreatedDate(), new Date()) < 1
		).collect(Collectors.toList());
	}

	public HouseHold getHouseHold(int id) {
		if (id < 1) return null;
		return houseHoldList.stream().filter(x -> x.getId() == id).findFirst().get();
	}

	public HouseHold getHouseHoldByNo(String houseHoldNo) {
		var items = houseHoldList.stream().filter(x -> houseHoldNo.toLowerCase().equals(x.getHouseHoldBook().trim().toLowerCase()))
				.collect(Collectors.toList());

		return items.size() > 0 ? items.get(0) : null;
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
		return this.getNewHouseHold().size();
	}

	public List<HouseHold> searchHouseHoldFull(String query) {
		if (query == null || query.isEmpty())
			return this.getAllHouseHold();

		List<HouseHold> searchedHouseHolds = this.houseHoldList.stream()
				.filter(h ->
					 StringHelper.containNormalString(h.getHouseHoldBook(), query)
					 || StringHelper.containNormalString(h.getName(), query)
					 || StringHelper.containNormalString(h.getAddressDetail(), query)
				).collect(Collectors.toList());
		return searchedHouseHolds;
	}
	
	public HouseHold searchHouseHold(String houseHoldBook) {
		try {
			return MSSQLDatabase.getInstance().searchHouseHold(houseHoldBook);
		}
		catch(Exception e) {
			LogService.error(e.getMessage());
			return null;
		}
	}
	
	public void deleteHouseHold(String houseHoldBook) {
		try {
			HouseHold searchHH = MSSQLDatabase.getInstance().searchHouseHold(houseHoldBook);
			if(searchHH != null) {
				orm.removeHouseHold(searchHH);
				NotiService.info("Xoa thanh cong!");
			}
		}
		catch(Exception e) {
			LogService.error(e.getMessage());
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
