package app.services;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import app.database.MSSQLDatabase;
import app.entity.People;
import app.helper.StringHelper;
import app.services.common.LogService;

public class PeopleService {
	private static MSSQLDatabase orm;
	private People people;

	private List<People> peopleList = new ArrayList<People>();
	
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
			peopleList = orm.getAllPeoples();
		}
		catch(Exception e) {
			LogService.error(e.getMessage());
		}
	}

	public List<People> getAllPeople() {
		return peopleList;
	}

	public List<People> searchPeopleFull(String query) {
		List<People> searchedPeople = this.peopleList.stream()
				.filter(h ->
						StringHelper.containNormalString(h.getIdentityNo(), query)
						|| StringHelper.containNormalString(h.getFullName(), query)
						|| StringHelper.containNormalString(h.getNickName(), query)
				).collect(Collectors.toList());

		return searchedPeople;
	}
	
	public void searchPeopleByFullName(String fullName) {
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


	public static void main(String[] args) {
		PeopleService pService = new PeopleService();

		for (var item : pService.searchPeopleFull("xxxxxx")) {
			LogService.info(item.getFullName());
		}
	}
}