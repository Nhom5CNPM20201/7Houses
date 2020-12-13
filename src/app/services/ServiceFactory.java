package app.services;

public class ServiceFactory {
    private static AuthService authService;
    private static HouseHoldService houseHoldService;
    private static AddressService addressService;
    private static FeeService feeService;
    private static TemporaryResidentService temporaryresidentService;
    private static PeopleService peopleService;
    private static ContributeService contributeService;

    public ServiceFactory() {

    }

    public static void Init() {
        authService = new AuthService();
        addressService = new AddressService();
        feeService = new FeeService();
        houseHoldService = new HouseHoldService();
        temporaryresidentService = new TemporaryResidentService();
        peopleService = new PeopleService();
        contributeService = new ContributeService();
    }

    public static AuthService getAuthService() {
        return authService;
    }
    public static AddressService getAddressService(){
        return addressService;
    }
    public static FeeService getFeeService(){
        return feeService;
    }
	public static HouseHoldService getHouseHoldService() {
		return houseHoldService;
	}
    public static TemporaryResidentService getTemporaryresidentService() {
        return temporaryresidentService;
    }
	public static PeopleService getPeopleService() {
		return peopleService;
	}
	public static ContributeService getContributeService() {return contributeService;}
}
