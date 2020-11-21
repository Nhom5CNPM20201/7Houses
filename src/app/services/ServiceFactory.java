package app.services;

public class ServiceFactory {
    private static AuthService authService;
    private static HouseHoldService houseHoldService;
    private static AddressService addressService;

    public ServiceFactory() {

    }

    public static void Init() {
        authService = new AuthService();
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
    public static void houseHoldInstance() {
		houseHoldService = new HouseHoldService();
	}

	public static HouseHoldService getHouseHoldService() {
		return houseHoldService;
	}
}
