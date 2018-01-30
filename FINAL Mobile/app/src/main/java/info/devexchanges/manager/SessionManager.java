package info.devexchanges.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {
    private SharedPreferences pref;
    private Editor editor;
    private Context context;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "login";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IS_LOGIN = "isLogin";

    // Constructor
    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setSavedUserName(String userName) {
        editor.putString(KEY_USER_NAME, userName);
        editor.commit();
    }

    public String getSavedUserName() {
        return pref.getString(KEY_USER_NAME, "");
    }

    public void setSavedPassword(String pass) {
        editor.putString(KEY_PASSWORD, pass);
        editor.commit();
    }

    public boolean isUserLogin() {
        return pref.getBoolean(KEY_IS_LOGIN, false);
    }

    public void setUserLoggedIn(boolean isLogin) {
        editor.putBoolean(KEY_IS_LOGIN, isLogin);
        editor.commit();
    }

    public void clearSession() {
        editor.clear();
        editor.commit();
    }
}
