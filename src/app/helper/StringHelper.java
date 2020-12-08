package app.helper;

public class StringHelper {
    public static boolean containNormalString(String text_a, String text_b) {
        if (text_a == null || text_a.equals("")) {
            return false;
        }

        if (text_b == null || text_b.equals("")) {
            return true;
        }

        String a = text_a.trim().toLowerCase();
        String b = text_b.trim().toLowerCase();

        return a.contains(b);
    }
}
