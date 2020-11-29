package app.services;

public class ServiceFactory {
    private static AuthService authService;
    private static HouseHoldService houseHoldService;
    private static AddressService addressService;
    private static FeeService feeService;

    public ServiceFactory() {

    }

    public static void Init() {
        authService = new AuthService();
        addressService = new AddressService();
        feeService = new FeeService();
        houseHoldService = new HouseHoldService();
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
}
