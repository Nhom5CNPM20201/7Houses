package app.common;

public enum Gender {
    NONE(-1),
    MALE(0),
    FEMALE(1);

    Gender(int value) {
        this.value = value;
    }

    private int value;

    public Gender setValue(int key) {
        switch (key) {
            case 0: return MALE;
            case 1: return FEMALE;
            default: return NONE;
        }
    }

    public String getGenderName() {
        switch (this) {
            case MALE:
                return "Nam";
            case FEMALE:
                return "Nữ";
            default:
                return "Khác";
        }
    }
}
