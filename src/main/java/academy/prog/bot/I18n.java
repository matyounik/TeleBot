package academy.prog.bot;

import java.util.HashMap;
import java.util.Map;

public class I18n {

    private static final Map<String, Map<String, String>> DATA = new HashMap<>();

    static {

        /* ================= EN ================= */
        Map<String, String> en = new HashMap<>();

        en.put("menu", "📋 Menu");
        en.put("settings", "⚙️ Settings");

        en.put("btn_register", "📝 Register");
        en.put("btn_login", "🔐 Login");
        en.put("btn_profile", "👤 Profile");
        en.put("btn_schedule", "📅 Schedule");
        en.put("btn_grades", "📊 Grades");
        en.put("btn_request", "📩 Leave request");
        en.put("btn_settings", "⚙️ Settings");

        en.put("btn_language", "🌍 Language");
        en.put("btn_logout", "🚪 Logout");
        en.put("btn_back", "⬅ Back");
        en.put("enter_login", "📞📧 Enter phone number or email");
        en.put("login_not_found", "❌ User not found");

        en.put("enter_name", "👤 Enter your name");
        en.put("enter_phone", "📞 Enter phone number");
        en.put("enter_email", "📧 Enter email");
        en.put("enter_class", "🎓 Choose your class");

        en.put("enter_password", "🔑 Enter password");
        en.put("enter_new_password", "🔐 Enter new password");
        en.put("repeat_password", "🔁 Repeat password");
        en.put("btn_home", "🏠 Home");

        en.put("password_mismatch", "❌ Passwords do not match");
        en.put("password_wrong", "❌ Wrong password");
        en.put("login_success", "✅ You are logged in");

        en.put("phone_invalid", "❌ Invalid phone number");
        en.put("email_invalid", "❌ Invalid email");

        en.put("profile",
                "👤 Profile\n\n" +
                        "Name: %s\n" +
                        "📞 Phone: %s\n" +
                        "📧 Email: %s\n" +
                        "🎓 Class: %s");

        en.put("btn_edit_name", "✏️ Edit name");
        en.put("btn_edit_phone", "✏️ Edit phone");
        en.put("btn_edit_email", "✏️ Edit email");
        en.put("btn_edit_class", "✏️ Edit class");
        en.put("btn_change_password", "🔐 Change password");

        en.put("grades_soon", "📊 Grades (soon)");
        en.put("request_enter", "📩 Enter your request");
        en.put("request_done", "✅ Request sent");
        en.put("no_lessons", "📭 No lessons for today");

        DATA.put("en", en);

        /* ================= ES ================= */
        Map<String, String> es = new HashMap<>();

        es.put("menu", "📋 Menú");
        es.put("settings", "⚙️ Ajustes");

        es.put("btn_register", "📝 Registro");
        es.put("btn_login", "🔐 Entrar");
        es.put("btn_profile", "👤 Perfil");
        es.put("btn_schedule", "📅 Horario");
        es.put("btn_grades", "📊 Calificaciones");
        es.put("btn_request", "📩 Enviar solicitud");
        es.put("btn_settings", "⚙️ Ajustes");

        es.put("btn_language", "🌍 Idioma");
        es.put("btn_logout", "🚪 Salir");
        es.put("btn_back", "⬅ Atrás");
        es.put("btn_home", "🏠 Inicio");

        es.put("enter_name", "👤 Ingresa tu nombre");
        es.put("enter_phone", "📞 Ingresa tu número de teléfono");
        es.put("enter_email", "📧 Ingresa tu correo electrónico");
        es.put("enter_class", "🎓 Elige tu clase");
        es.put("enter_login", "📞📧 Ingresa teléfono o email");
        es.put("login_not_found", "❌ Usuario no encontrado");

        es.put("enter_password", "🔑 Introduce la contraseña");
        es.put("enter_new_password", "🔐 Introduce la nueva contraseña");
        es.put("repeat_password", "🔁 Repite la contraseña");

        es.put("password_mismatch", "❌ Las contraseñas no coinciden");
        es.put("password_wrong", "❌ Contraseña incorrecta");
        es.put("login_success", "✅ Has iniciado sesión");

        es.put("phone_invalid", "❌ Número de teléfono inválido");
        es.put("email_invalid", "❌ Correo electrónico inválido");

        es.put("profile",
                "👤 Perfil\n\n" +
                        "Nombre: %s\n" +
                        "📞 Teléfono: %s\n" +
                        "📧 Email: %s\n" +
                        "🎓 Clase: %s");

        es.put("btn_edit_name", "✏️ Editar nombre");
        es.put("btn_edit_phone", "✏️ Editar teléfono");
        es.put("btn_edit_email", "✏️ Editar email");
        es.put("btn_edit_class", "✏️ Editar clase");
        es.put("btn_change_password", "🔐 Cambiar contraseña");

        es.put("grades_soon", "📊 Calificaciones (pronto)");
        es.put("request_enter", "📩 Escribe tu solicitud");
        es.put("request_done", "✅ Solicitud enviada");
        es.put("no_lessons", "📭 No hay clases para hoy");

        DATA.put("es", es);

        /* ================= UA ================= */
        Map<String, String> ua = new HashMap<>();

        ua.put("menu", "📋 Меню");
        ua.put("settings", "⚙️ Налаштування");

        ua.put("btn_register", "📝 Реєстрація");
        ua.put("btn_login", "🔐 Вхід");
        ua.put("btn_profile", "👤 Профіль");
        ua.put("btn_schedule", "📅 Розклад");
        ua.put("btn_grades", "📊 Оцінки");
        ua.put("btn_request", "📩 Залишити заявку");
        ua.put("btn_settings", "⚙️ Налаштування");
        ua.put("btn_home", "🏠 Головна");

        ua.put("btn_language", "🌍 Мова");
        ua.put("btn_logout", "🚪 Вийти");
        ua.put("btn_back", "⬅ Назад");
        ua.put("enter_login", "📞📧 Введіть телефон або email");
        ua.put("login_not_found", "❌ Користувача не знайдено");

        ua.put("enter_name", "👤 Введіть ім’я");
        ua.put("enter_phone", "📞 Введіть номер телефону");
        ua.put("enter_email", "📧 Введіть email");
        ua.put("enter_class", "🎓 Оберіть клас");

        ua.put("enter_password", "🔑 Введіть пароль");
        ua.put("enter_new_password", "🔐 Введіть новий пароль");
        ua.put("repeat_password", "🔁 Повторіть пароль");

        ua.put("password_mismatch", "❌ Паролі не співпадають");
        ua.put("password_wrong", "❌ Невірний пароль");
        ua.put("login_success", "✅ Ви увійшли");

        ua.put("phone_invalid", "❌ Невірний номер телефону");
        ua.put("email_invalid", "❌ Невірний email");

        ua.put("profile",
                "👤 Профіль\n\n" +
                        "Ім’я: %s\n" +
                        "📞 Телефон: %s\n" +
                        "📧 Email: %s\n" +
                        "🎓 Клас: %s");

        ua.put("btn_edit_name", "✏️ Змінити ім’я");
        ua.put("btn_edit_phone", "✏️ Змінити телефон");
        ua.put("btn_edit_email", "✏️ Змінити email");
        ua.put("btn_edit_class", "✏️ Змінити клас");
        ua.put("btn_change_password", "🔐 Змінити пароль");

        ua.put("grades_soon", "📊 Оцінки (скоро)");
        ua.put("request_enter", "📩 Введіть текст заявки");
        ua.put("request_done", "✅ Заявку надіслано");
        ua.put("no_lessons", "📭 На сьогодні уроків немає");

        DATA.put("ua", ua);
    }

    public static String t(String key, String lang) {
        return DATA.getOrDefault(lang, DATA.get("en"))
                .getOrDefault(key, key);
    }
}