package app.services;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import app.database.MSSQLDatabase;
import app.database.config.PeopleConfig;
import app.entity.People;
import app.services.common.LogService;

public class PeopleService {
	private static MSSQLDatabase orm;
	private People people;

	private List<People> peoples;
	
	public PeopleService() {
		initPeople();
	}
	
	public void createPeople(People people) {
		try {
			orm = MSSQLDatabase.getInstance();
			People searchP = orm.searchPeople(people.getFullName());
			if(searchP == null) {
				orm.insertPeople(people);
				System.out.println("Tao thanh cong.");
			}
			else {
				System.out.println("Da ton tai nhan khau!");
			}
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int coutAllPeople() {
		try {
			int count = MSSQLDatabase.getInstance().countAllPeople();
			return count;
		} catch(Exception e) {
			LogService.error(e.getMessage());
			return 0;
		}
	}
	
	public void initPeople() {
		try {
			orm = MSSQLDatabase.getInstance();
			peoples = orm.getAllPeoples();
		}
		catch(Exception e) {
			LogService.error(e.getMessage());
		}
	}

	public List<People> getAllPeople() {
		return peoples;
	}
	
	public void searchPeople(String fullName) {
		try {
			orm = MSSQLDatabase.getInstance();
			People searchP = orm.searchPeople(fullName);
			if(searchP != null) System.out.println(searchP.getFullName() + "\t" + searchP.getFullName());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deletePeople(String fullName) {
		try {
			orm = MSSQLDatabase.getInstance();
			People searchP = orm.searchPeople(fullName);
			if(searchP != null) {
				orm.removePeople(searchP);
				System.out.println("Xoa thanh cong!");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updatePeople() {
		
	}
	
//	Test Service
/*
	public static void main(String[] args) {
		PeopleService pService = new PeopleService();
		People people = new People();
	//	pService.createPeople(people);
//		pService.searchPeople("mt");
//		pService.deletePeople("Nguyen Van A");
//		pService.getAllPeople();
	} 
	*/
}