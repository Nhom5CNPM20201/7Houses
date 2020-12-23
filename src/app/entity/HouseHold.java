package app.entity;

import app.entity.IEntity;

import java.util.Date;

public class HouseHold implements IEntity {
	private int id;
	private int idAddress;
	private String houseHoldBook;
	private int idOwner;
	private String name;
	private String addressDetail;
	private Date createdDate;

	public HouseHold() {

	}

	public HouseHold(int id, int idAddress, String houseHoldBook, int idOwner, String name) {
		this.id = id;
		this.idAddress = idAddress;
		this.idOwner = idOwner;
		this.houseHoldBook = houseHoldBook.trim();
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

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String content) {
		this.addressDetail = content;
	}

	public int getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}

	public String getHouseHoldBook() {
		return houseHoldBook;
	}

	public void setHouseHoldBook(String houseHoldBook) {
		this.houseHoldBook = houseHoldBook;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
