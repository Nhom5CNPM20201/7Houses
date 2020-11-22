package app.entity;

import java.sql.Date;

public class Change {
	private int id;
	private int idPeople;
	private int idHouseHold;
	private Date changingDate;
	private int type;
	private String content;
	
	public Change(int id, int idPeople, int idHouseHold, String changingTime, int type, String content) {
		this.id = id;
		this.idPeople = idPeople;
		this.idHouseHold = idHouseHold;
		this.changingDate = Date.valueOf(changingTime);
		this.type = type;
		this.content = content;
	}
	
	public Change(int idPeople, int idHouseHold, Date changingDate, int type, String content) {
		this.idPeople = idPeople;
		this.idHouseHold = idHouseHold;
		this.changingDate = changingDate;
		this.type = type;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPeople() {
		return idPeople;
	}

	public void setIdPeople(int idPeople) {
		this.idPeople = idPeople;
	}

	public int getIdHouseHold() {
		return idHouseHold;
	}

	public void setIdHouseHold(int idHouseHold) {
		this.idHouseHold = idHouseHold;
	}

	public Date getChangingDate() {
		return changingDate;
	}

	public void setChangingDate(Date changingDate) {
		this.changingDate = changingDate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}	
	

}
