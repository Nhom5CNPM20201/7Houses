package app.common;

public enum AccountPositionEnum {
    NONE(-1),
    LEADER(0),
    VICE_LEADER(1),
    ACCOUNTANT(2);

    AccountPositionEnum(int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }
}
