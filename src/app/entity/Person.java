package app.entity;

public class Person {
    private int ID;//them mot bien ID de tien cho viec quan li them sua xoa
    private String fullName;
    private String nickname;
    private String birthplace;
    private String identityNo;
    private String job;
    private String dateOfBirth;//minh chinh tat ca kieu ngay ve String de tien cho viec nhap dau vao
    private String identityMfg;//minh chinh tat ca kieu ngay ve String de tien cho viec nhap dau vao
    private String identityOrigin;
    private String regisDate;//minh chinh tat ca kieu ngay ve String de tien cho viec nhap dau vao
    private String houseHolderRela;
    private String ethnic;
    private String nativePlace;
    private String residence;
    private boolean gender;
    private String workplace;
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getBirthplace() {
        return birthplace;
    }
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }
    public String getIdentityNo() {
        return identityNo;
    }
    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getIdentityMfg() {
        return identityMfg;
    }
    public void setIdentityMfg(String identityMfg) {
        this.identityMfg = identityMfg;
    }
    public String getIdentityOrigin() {
        return identityOrigin;
    }
    public void setIdentityOrigin(String identityOrigin) {
        this.identityOrigin = identityOrigin;
    }
    public String getRegisDate() {
        return regisDate;
    }
    public void setRegisDate(String regisDate) {
        this.regisDate = regisDate;
    }
    public String getHouseHolderRela() {
        return houseHolderRela;
    }
    public void setHouseHolderRela(String houseHolderRela) {
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
    public String getResidence() {
        return residence;
    }
    public void setResidence(String residence) {
        this.residence = residence;
    }
    public boolean isGender() {
        return gender;
    }
    public void setGender(boolean gender) {
        this.gender = gender;
    }
    public String getWorkplace() {
        return workplace;
    }
    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }
    public Person(int iD, String fullName, String nickname, String birthplace, String identityNo, String job,
                  String dateOfBirth, String identityMfg, String identityOrigin, String regisDate, String houseHolderRela,
                  String ethnic, String nativePlace, String residence, boolean gender, String workplace) {
        super();
        ID = iD;
        this.fullName = fullName;
        this.nickname = nickname;
        this.birthplace = birthplace;
        this.identityNo = identityNo;
        this.job = job;
        this.dateOfBirth = dateOfBirth;
        this.identityMfg = identityMfg;
        this.identityOrigin = identityOrigin;
        this.regisDate = regisDate;
        this.houseHolderRela = houseHolderRela;
        this.ethnic = ethnic;
        this.nativePlace = nativePlace;
        this.residence = residence;
        this.gender = gender;
        this.workplace = workplace;
    }
    public Person() {
        super();
    }
    public void showInfo() {
        //minh chinh thu tu in ra khac so voi thu tu dat bien de trong logic hon
        System.out.println("Name: " +this.fullName);
        if(isGender()==false)
            System.out.println("Sex: male" );
        else
            System.out.println("Sex: female");
        System.out.println("Nickname: " +this.nickname);
        System.out.println("Job: " +this.job);
        System.out.println("Office: " +this.workplace);
        System.out.println("Place of birth : " +this.birthplace);
        System.out.println("Birthday: " +this.dateOfBirth);
        System.out.println("ID number: " +this.identityNo);
        System.out.println("ID number's issued date: " +this.identityMfg);
        System.out.println("ID number's issued place: " +this.identityOrigin);
        System.out.println("Subcription date: " +this.regisDate);
        System.out.println("Relationship with house holder : " +this.houseHolderRela);
        System.out.println("Ethnic: " +this.ethnic);
        System.out.println("Home town: " +this.nativePlace);
        System.out.println("Adrress: " +this.residence);
    }
}
