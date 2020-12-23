package app.services;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import app.database.MSSQLDatabase;
import app.entity.HouseHold;
import app.entity.People;
import app.helper.DateHelper;
import app.helper.StringHelper;
import app.helper.ValidateHelper;
import app.services.common.LogService;

public class PeopleService {
	private static MSSQLDatabase orm;
	private People people;

	private List<People> peopleList = new ArrayList<People>();
	
	public PeopleService() {
		initPeople();
	}
	
	public People createPeople(People people) throws Exception {
		orm = MSSQLDatabase.getInstance();

		if (people.getId() > 0 && peopleList.stream().anyMatch(p -> p.getId() == people.getId()))
			return people;


		people.setId(peopleList.size() + 1);
		peopleList.add(people);
		orm.insertPeople(people);

		return people;
	}

	public People updatePeople(People people) throws Exception {
		if (!(people.getId() > 0 && peopleList.stream().anyMatch(p -> p.getId() == people.getId())))
			throw new Exception("Không tìm thấy thông tin nhân khẩu này trong hệ thống.");

		MSSQLDatabase.getInstance().updatePeople(people);

		this.initPeople();

		return people;
	}

	public long coutAllPeople() {
		return this.peopleList.stream().count();
	}

	public long countNewPeople() {
		return peopleList.stream().filter(x ->
				DateHelper.getDaysBetween(x.getRegisDate(), new Date()) < 1
		).count();
	}
	
	public void initPeople() {
		try {
			peopleList = MSSQLDatabase.getInstance().getAllPeoples();

			for (People item : peopleList) {
				HouseHold mappingHouseHold = ServiceFactory.getHouseHoldService().getHouseHold(item.getIdHouseHold());
				item.setHouseHold(mappingHouseHold);
			}
		}
		catch(Exception e) {
			LogService.error(e.getMessage());
		}
	}

	public List<People> getAllPeople() {
		return peopleList;
	}

	public People getPeople(int id) {
		return peopleList.stream().filter(x -> x.getId() == id).findFirst().get();
	}

	public List<People> searchPeopleFull(String query) {
		if (query == null || query.isEmpty())
			return this.getAllPeople();

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

	public List<People> statistic(int genderIndex, int ageFrom, int ageTo, Date timeFrom, Date timeTo) {
		Predicate<People> filterGender = p -> genderIndex == -1 || genderIndex == p.getGender();
		Predicate<People> filterAgeGreater = p -> ageFrom == -1 || p.getAge() >= ageFrom;
		Predicate<People> filterAgeLower = p -> ageTo == -1 || p.getAge() <= ageTo;
		Predicate<People> filterRegisGreater = p -> timeFrom == null || p.getRegisDate().after(timeFrom);
		Predicate<People> filterRegisLower = p -> timeTo == null || p.getRegisDate().before(timeTo);

		return this.getAllPeople().stream()
				.filter(filterGender)
				.filter(filterAgeGreater)
				.filter(filterAgeLower)
				.filter(filterRegisGreater)
				.filter(filterRegisLower)
				.collect(Collectors.toList());
	}


	public static void main(String[] args) throws Exception {
		try {
			PeopleService pService = new PeopleService();

			People newPeople = new People();
			newPeople.setFullName("Trần Tiến Đại");
			newPeople.setNickName("DaiNgo123");
			newPeople.setDateOfBirth(new Date(2005, 12,1));
			newPeople.setBirthPlace("Thanh Lương, Cao Bằng");
			newPeople.setEthnic("Kinh");
			newPeople.setWorkPlace("Hà Nội");
			newPeople.setIdentityNo("035545121244");
			newPeople.setIdentityMfg(new Date(2014,6,9));
			newPeople.setIdentityOrigin("Hà Nội");
			//newPeople.setIdHouseHold(Integer.getInteger(idHoKhau.getText()));
			newPeople.setRegisDate(new Date());

			pService.createPeople(newPeople);
		} catch (Exception e) {
			throw e;
		}
	}
}