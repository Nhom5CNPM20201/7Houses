package app.services;

public class ServiceFactory {
    private static AuthService authService;
    private static HouseHoldService houseHoldService;

    public ServiceFactory() {

    }

    public static void Init() {
        authService = new AuthService();
    }

    public static AuthService getAuthService() {
        return authService;
    }
    
    public static void houseHoldInstance() {
		houseHoldService = new HouseHoldService();
	}

	public static HouseHoldService getHouseHoldService() {
		return houseHoldService;
	}
}
