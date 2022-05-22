package ru.palyanaff.samsung_project_english_learning.screens.menu;

import android.app.Application;

public class UserSettings extends Application {
    public static final String PREFERENCES = "preferences";
    // key
    public static final String CUSTOM_THEME = "customTheme";
    // values
    public static final String DARK_THEME = "darkTheme";
    public static final String LIGHT_THEME = "lightTheme";

    private String customTheme;

    public String getCustomTheme() {
        return this.customTheme;
    }

    public void setCustomTheme(String customTheme) {
        this.customTheme = customTheme;
    }
}
