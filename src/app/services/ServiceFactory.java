package app.services;

import app.component.dashboard.peopleManage.PeopleManage;

public class ServiceFactory {
    private static AuthService authService;
    private static HouseHoldService houseHoldService;
    private static AddressService addressService;
    private static FeeService feeService;
    private static PeopleService peopleService;

    public ServiceFactory() {

    }

    public static void Init() {
        authService = new AuthService();
        peopleService = new PeopleService();
    }

    public static AuthService getAuthService() {
        return authService;
    }

    public static void addressInstance(){
        addressService = new AddressService();
    }
    public static AddressService getAddressService(){
        return addressService;
    }
    public static FeeService getFeeService(){
        return feeService;
    }
    public static void feeInstance(){
        feeService = new FeeService();
    }
    public static void houseHoldInstance() {
		houseHoldService = new HouseHoldService();
	}

	public static HouseHoldService getHouseHoldService() {
		return houseHoldService;
	}
	
    public static void peopleInstance() {
		
	}

	public static PeopleService getPeopleService() {
		return peopleService;
	}


}
