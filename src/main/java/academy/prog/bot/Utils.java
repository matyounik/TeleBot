package academy.prog.bot;

import java.util.regex.Pattern;

public class Utils {

    private static final Pattern PHONE =
            Pattern.compile("^\\d{9,15}$");

    private static final Pattern EMAIL =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public static boolean isValidPhone(String text) {
        return text != null && PHONE.matcher(text).matches();
    }

    public static boolean isValidEmail(String text) {
        return text != null && EMAIL.matcher(text).matches();
    }
}