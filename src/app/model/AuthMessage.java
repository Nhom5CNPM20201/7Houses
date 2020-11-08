package app.model;

public class AuthMessage {
    private boolean status;
    private String message;

    public AuthMessage() {

    }

    public AuthMessage(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
