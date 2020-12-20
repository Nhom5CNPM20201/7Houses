package app.entity;

import java.util.Date;

public class Move {
	private int id;
	private int idHouseHold;
	private int idOldAddress;
	private int idNewAddress;
	private Date movingDate;
	private int type;

	public Move() {

	}
	
	public Move(int id, int idHouseHold, int idOldAddress, int idNewAddress, Date movingDate, int type) {
		this.id = id;
		this.idHouseHold = idHouseHold;
		this.idOldAddress = idOldAddress;
		this.idNewAddress = idNewAddress;
		this.movingDate = movingDate;
		this.type = type;
	}
	
	public Move(int idHouseHold, int idOldAddress, int idNewAddress, Date movingDate, int type) {
		this.idHouseHold = idHouseHold;
		this.idOldAddress = idOldAddress;
		this.idNewAddress = idNewAddress;
		this.movingDate = movingDate;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdHouseHold() {
		return idHouseHold;
	}
	public void setIdHouseHold(int idHouseHold) {
		this.idHouseHold = idHouseHold;
	}
	public int getIdOldAddress() {
		return idOldAddress;
	}
	public void setIdOldAddress(int idOldAddress) {
		this.idOldAddress = idOldAddress;
	}
	public int getIdNewAddress() {
		return idNewAddress;
	}
	public void setIdNewAddress(int idNewAddress) {
		this.idNewAddress = idNewAddress;
	}
	public Date getMovingDate() {
		return movingDate;
	}
	public void setMovingDate(Date movingDate) {
		this.movingDate = movingDate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
