package app.entity;
import java.util.Date;

public class People  {
	
	private int Id;
	
	private int IdHouseHold;

    private String FullName;

    private String NickName;

    private String BirthPlace;
    
    private String Job;
    
    private Date DateOfBirth ;
    
    private Date IdentityMfg ;
    
    private int HouseHolderRela;
    
    private String Ethnic;
    
    private String NativePlace ;
    
    private int Gender ;
    
    private String WorkPlace;
    
    private String IdentityNo;
    
    private String IdentityOrigin;
    
    private Date RegisDate;
    
    

	public People(int peopleId, int peopleIdHouseHold, String peopleFullName, String peopleNickName,
			String peopleIdBirthPlace, String peopleJob, java.sql.Date peopleDateOfBirth, java.sql.Date peopleRegisDate,
			int peopleHouseHolderRela, String peopleEthnic, String peopleNativePlace, int peopleGender,
			String peopleWorkPlace, String peopleIdentityNo, java.sql.Date peopleIdentityMfg,
			String peopleIdentityOrigin) {
		
		setId( Id);
		
		setIdHouseHold( IdHouseHold);
		
		setFullName( FullName);
		
		setNickName( NickName);
		
		setBirthPlace( BirthPlace);
		setJob( Job);
		setDateOfBirth( DateOfBirth);
		setRegisDate( RegisDate);
		setHouseHolderRela( HouseHolderRela);
		setEthnic( Ethnic);
		setNativePlace( NativePlace);
		setGender( Gender);
		setWorkPlace( WorkPlace);
		setIdentityNo( IdentityNo);
		setIdentityMfg( IdentityMfg);
		setIdentityOrigin( IdentityOrigin);
		
		
		
		
		
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getIdHouseHold() {
		return IdHouseHold;
	}

	public void setIdHouseHold(int idHouseHold) {
		IdHouseHold = idHouseHold;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getBirthPlace() {
		return BirthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		BirthPlace = birthPlace;
	}

	public String getJob() {
		return Job;
	}

	public void setJob(String job) {
		Job = job;
	}

	public Date getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public Date getIdentityMfg() {
		return IdentityMfg;
	}

	public void setIdentityMfg(Date identityMfg) {
		IdentityMfg = identityMfg;
	}

	public int getHouseHolderRela() {
		return HouseHolderRela;
	}

	public void setHouseHolderRela(int houseHolderRela) {
		HouseHolderRela = houseHolderRela;
	}

	public String getEthnic() {
		return Ethnic;
	}

	public void setEthnic(String ethnic) {
		Ethnic = ethnic;
	}

	public String getNativePlace() {
		return NativePlace;
	}

	public void setNativePlace(String nativePlace) {
		NativePlace = nativePlace;
	}

	public int getGender() {
		return Gender;
	}

	public void setGender(int gender) {
		Gender = gender;
	}

	public String getWorkPlace() {
		return WorkPlace;
	}

	public void setWorkPlace(String workPlace) {
		WorkPlace = workPlace;
	}

	public String getIdentityNo() {
		return IdentityNo;
	}

	public void setIdentityNo(String identityNo) {
		IdentityNo = identityNo;
	}

	public String getIdentityOrigin() {
		return IdentityOrigin;
	}

	public void setIdentityOrigin(String identityOrigin) {
		IdentityOrigin = identityOrigin;
	}

	public Date getRegisDate() {
		return RegisDate;
	}

	public void setRegisDate(Date regisDate) {
		RegisDate = regisDate;
	}





}
