package app.services;

public class ServiceFactory {
    private static AuthService authService;
    private static HouseHoldService houseHoldService;

    public ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
    	return new ServiceFactory();
    }
    
    public static void Init() {
        authService = new AuthService();
        houseHoldService = new HouseHoldService();
    }

    public static AuthService getAuthService() {
        return authService;
    }

	public static HouseHoldService getHouseHoldService() {
		return houseHoldService;
	}
}
