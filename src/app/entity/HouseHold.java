package app.entity;

import app.entity.IEntity;

public class HouseHold implements IEntity {
	private int id;
	private int idAddress;
	private String registrationBook;
	private int idOwner;
	private String name;
	
	public HouseHold(int idAddress, String registrationBook, int idOwner, String name) {
		this.idAddress = idAddress;
		this.idOwner = idOwner;
		this.registrationBook = registrationBook;
		this.name = name;
	}
	public HouseHold(int id, int idAddress, String registrationBook, int idOwner, String name) {
		this.id = id;
		this.idAddress = idAddress;
		this.idOwner = idOwner;
		this.registrationBook = registrationBook;
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}

	public int getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}

	public String getRegistrationBook() {
		return registrationBook;
	}

	public void setRegistrationBook(String registrationBook) {
		this.registrationBook = registrationBook;
	}

	
}
