package info.devexchanges.session;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import info.devexchanges.manager.SessionManager;

/**
 * Created by Hong Thai
 */
public class HomeActivity extends Activity {

    private SessionManager sessionManager;
    private TextView textUserName;
    private View btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Create/Retrieve session from application context
        sessionManager = new SessionManager(getApplicationContext());

        //find view by Id
        textUserName = (TextView) findViewById(R.id.user_name_login);
       // btnLogout = (View) findViewById(R.id.btn_logout);


       textUserName.setText("Logged in as [" + sessionManager.getSavedUserName() + "]");
        //btnLogout.setOnClickListener(onClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_logout:
                     sessionManager.clearSession(); //clear session
                     Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                     startActivity(intent); //go to Login Screen
                     finish(); //finish this activity
                     return  true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

  /*  private View.OnClickListener onClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.clearSession(); //clear session
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent); //go to Login Screen
                finish(); //finish this activity
            }
        };*/



}
