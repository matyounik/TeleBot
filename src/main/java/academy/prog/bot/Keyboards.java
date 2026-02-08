package academy.prog.bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public class Keyboards {

    /*  LANGUAGE  */

    public static ReplyKeyboardMarkup language() {
        KeyboardRow row = new KeyboardRow();
        row.add("🇬🇧 English");
        row.add("🇪🇸 Español");
        row.add("🇺🇦 Українська");

        ReplyKeyboardMarkup kb = new ReplyKeyboardMarkup();
        kb.setKeyboard(List.of(row));
        kb.setResizeKeyboard(true);
        kb.setOneTimeKeyboard(true);
        return kb;
    }

    /*  MAIN MENU  */

    public static ReplyKeyboardMarkup main(String lang, boolean registered, boolean loggedIn) {
        ReplyKeyboardMarkup kb = new ReplyKeyboardMarkup();
        kb.setResizeKeyboard(true);

        if (!registered) {
            kb.setKeyboard(List.of(
                    row(I18n.t("btn_register", lang)),
                    row(I18n.t("btn_login", lang))
            ));
            return kb;
        }

        if (!loggedIn) {
            kb.setKeyboard(List.of(
                    row(I18n.t("btn_login", lang)),
                    row(I18n.t("btn_back", lang))
            ));
            return kb;
        }

        kb.setKeyboard(List.of(
                row(I18n.t("btn_profile", lang), I18n.t("btn_schedule", lang)),
                row(I18n.t("btn_grades", lang), I18n.t("btn_request", lang)),
                row(I18n.t("btn_settings", lang))
        ));
        return kb;
    }

    /*  PROFILE  */

    public static ReplyKeyboardMarkup profile(String lang) {
        ReplyKeyboardMarkup kb = new ReplyKeyboardMarkup();
        kb.setResizeKeyboard(true);

        kb.setKeyboard(List.of(
                row(I18n.t("btn_edit_name", lang), I18n.t("btn_edit_class", lang)),
                row(I18n.t("btn_edit_phone", lang), I18n.t("btn_edit_email", lang)),
                row(I18n.t("btn_back", lang))
        ));
        return kb;
    }

    /*  SETTINGS  */

    public static ReplyKeyboardMarkup settings(String lang) {
        ReplyKeyboardMarkup kb = new ReplyKeyboardMarkup();
        kb.setResizeKeyboard(true);

        kb.setKeyboard(List.of(
                row(I18n.t("btn_change_password", lang)),
                row(I18n.t("btn_language", lang)),
                row(I18n.t("btn_logout", lang)),
                row(I18n.t("btn_back", lang))
        ));
        return kb;
    }

    /*  CLASS  */

    public static ReplyKeyboardMarkup classSelect() {
        ReplyKeyboardMarkup kb = new ReplyKeyboardMarkup();
        kb.setResizeKeyboard(true);
        kb.setKeyboard(List.of(
                row("10", "11", "12")
        ));
        return kb;
    }

    /* REMOVE */

    public static ReplyKeyboard remove() {
        ReplyKeyboardRemove kb = new ReplyKeyboardRemove();
        kb.setRemoveKeyboard(true);
        return kb;
    }

    /*  SCHEDULE  */


    public static ReplyKeyboardMarkup schedule(String lang) {
        ReplyKeyboardMarkup kb = new ReplyKeyboardMarkup();
        kb.setResizeKeyboard(true);

        kb.setKeyboard(List.of(
                row("⬅", "➡"),
                row(I18n.t("btn_home", lang))
        ));
        return kb;
    }

    /* HELPER */

    private static KeyboardRow row(String... buttons) {
        KeyboardRow row = new KeyboardRow();
        for (String b : buttons) row.add(b);
        return row;
    }
}