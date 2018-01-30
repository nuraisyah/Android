package info.devexchanges.session;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import info.devexchanges.constant.ConstantString;
import info.devexchanges.manager.SessionManager;


public class LoginActivity extends Activity {

    private EditText textUserName;
    private EditText textPassword;
    private View btnLogin;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initial Session Manager with Application Context
        sessionManager = new SessionManager(getApplicationContext());

        //if user logged in, go to Home Screen, skip Login screen
        if (sessionManager.isUserLogin()) {
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
            finish();
        } else {
            setContentView(R.layout.activity_login);
            textPassword = (EditText) findViewById(R.id.password);
            textUserName = (EditText) findViewById(R.id.user_name);
            btnLogin = (View) findViewById(R.id.btn_login);

            btnLogin.setOnClickListener(onClickListener());
        }
    }

    private View.OnClickListener onClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textUserName.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter your user name...", Toast.LENGTH_SHORT).show();
                } else if (textPassword.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter your password...", Toast.LENGTH_SHORT).show();
                } else if (!checkLogin(textUserName.getText().toString().trim(), textPassword.getText().toString().trim())) {
                    Toast.makeText(LoginActivity.this, "User name or password failed!", Toast.LENGTH_SHORT).show();
                } else {
                    goToHomeActivity();
                }

            }
        };
    }

    private void goToHomeActivity() {
        sessionManager.setSavedPassword(textPassword.getText().toString());
        sessionManager.setSavedUserName(textUserName.getText().toString());
        sessionManager.setUserLoggedIn(true);

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish(); //finish LoginActivity
    }

    /**
     * Checking input is valid?
     * @param userName
     * @param password
     * @return
     */
    private boolean checkLogin(String userName, String password) {
        if (password.equals(ConstantString.KEY_PASSWORD)) {
            int i = 0;
            while (i < ConstantString.KEY_USER_NAME.length) {
                if (ConstantString.KEY_USER_NAME[i].equals(userName)) {
                    return true;
                } else {
                    i++;
                }
            }
        }
        return false;
    }

}
