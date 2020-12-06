package app.services;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import app.database.MSSQLDatabase;
import app.database.config.PeopleConfig;
import app.entity.People;

public class PeopleService {
	private static MSSQLDatabase orm;
	private People people;
	
	public PeopleService() {
		
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
	
	public void getAllPeople() {
		try {
			orm = MSSQLDatabase.getInstance();
			List<People> peoples = orm.getAllPeoples();
			for(People i : peoples) {
				System.out.println(i.getFullName() + "\t" + i.getFullName());
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
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