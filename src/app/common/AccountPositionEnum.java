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

    public AccountPositionEnum setValue(int pos) {
        switch (pos) {
            case 0: return LEADER;
            case 1: return VICE_LEADER;
            case 2: return ACCOUNTANT;
            default: return NONE;
        }
    }

    public int getValue() {
        return value;
    }

    public String getPositionName() {
        switch (this) {
            case LEADER:
                return "Tổ trưởng";
            case VICE_LEADER:
                return "Tổ phó";
            case ACCOUNTANT:
                return "Kế toán";
            default:
                return "Khách";
        }
    }
}
