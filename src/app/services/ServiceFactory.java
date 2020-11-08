package app.services;

public class ServiceFactory {
    private static AuthService authService;

    public ServiceFactory() {

    }

    public static void Init() {
        authService = new AuthService();
    }

    public static AuthService getAuthService() {
        return authService;
    }
}
