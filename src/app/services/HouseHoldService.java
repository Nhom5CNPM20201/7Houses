package app.services;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import app.database.MSSQLDatabase;
import app.database.config.HouseHoldConfig;
import app.entity.HouseHold;

public class HouseHoldService {
	private static MSSQLDatabase orm;
	private HouseHold houseHold;
	
	public HouseHoldService() {
		
	}
	
	private static MSSQLDatabase inORM() throws Exception{
		if(orm == null) orm = MSSQLDatabase.getInstance();
		return orm;
	}
	
	public void createHouseHold(HouseHold houseHold) {
		try {
			orm = inORM();
			HouseHold searchHH = orm.searchHouseHold(houseHold.getHouseHoldBook());
			if(searchHH == null) {
				orm.insertHouseHold(houseHold);
				System.out.println("Tao thanh cong.");
			}
			else {
				System.out.println("Da ton tai ho khau!");
			}
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getAllHouseHold() {
		try {
			orm = inORM();
			List<HouseHold> houseHolds = orm.getAllHouseHold();
			for(HouseHold i : houseHolds) {
				System.out.println(i.getHouseHoldBook() + "\t" + i.getName());
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void searchHouseHold(String houseHoldBook) {
		try {
			orm = inORM();
			HouseHold searchHH = orm.searchHouseHold(houseHoldBook);
			if(searchHH != null) System.out.println(searchHH.getHouseHoldBook() + "\t" + searchHH.getName());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteHouseHold(String houseHoldBook) {
		try {
			orm = inORM();
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
