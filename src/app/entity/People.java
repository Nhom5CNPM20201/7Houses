package app.entity;
import java.sql.Date;
import java.sql.*;
public class People  {
	
	private int id;
	
	private int idHouseHold;

    private String fullName;

    private String nickName;

    private String birthPlace;
    
    private String job;
    
    private Date dateOfBirth ;
    
    private Date regisDate;
    
    private int houseHolderRela;
    
    private String ethnic;
    
    private String nativePlace ;
    
    private int gender ;
    
    private String workPlace;
    
    private String identityNo;
    
    private Date identityMfg ;
    
    private String identityOrigin;
    

    
   public People() {
    	
    //	idHouseHold = 5;
     	fullName = "mt" ;
    	nickName = "mmnbnajksd";
    	birthPlace = "HN";
    	job = "sinh vien";
    	dateOfBirth = Date.valueOf("1980-07-01");
    	regisDate = Date.valueOf("1999-02-01");
    	houseHolderRela = 1;
    	ethnic = "kinh";
    	nativePlace = "HY";
    	gender = 1;
    	workPlace = "HN";
    	identityNo = "123132163";
    	identityMfg = Date.valueOf("2000-02-01");
    	identityOrigin = "HYYYY";    
    }
    
   

	public People(int id, int idHouseHold, String fullName, String nickName, String birthPlace, String job,
		Date dateOfBirth, Date regisDate, int houseHolderRela, String ethnic, int gender,
		String workPlace, String identityNo, Date identityMfg, String identityOrigin) {
	this.id = id;
	this.idHouseHold = idHouseHold;
	this.fullName = fullName;
	this.nickName = nickName;
	this.birthPlace = birthPlace;
	this.job = job;
	this.dateOfBirth = dateOfBirth;
	this.regisDate = regisDate;
	this.houseHolderRela = houseHolderRela;
	this.ethnic = ethnic;
	this.gender = gender;
	this.workPlace = workPlace;
	this.identityNo = identityNo;
	this.identityMfg = identityMfg;
	this.identityOrigin = identityOrigin;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getDateOfBirth() {
	//	Date date = Date.valueOf(this.dateOfBirth);      
		return dateOfBirth ;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getIdentityMfg() {
	//	Date date = Date.valueOf(this.identityMfg);      
		return regisDate ;
	}

	public void setIdentityMfg(Date identityMfg2) {
		this.identityMfg = identityMfg2;
	}

	public int getHouseHolderRela() {
		return houseHolderRela;
	}

	public void setHouseHolderRela(int houseHolderRela) {
		this.houseHolderRela = houseHolderRela;
	}

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getIdentityOrigin() {
		return identityOrigin;
	}

	public void setIdentityOrigin(String identityOrigin) {
		this.identityOrigin = identityOrigin;
	}

	public Date getRegisDate() {
	//	Date date = Date.valueOf(this.regisDate);      
		return regisDate ;
	}

	public void setRegisDate(Date regisDate) {
		this.regisDate = regisDate;
	}
}
