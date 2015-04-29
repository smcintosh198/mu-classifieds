package edu.millersville.sdmcinto.muclassifieds;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

//liferay imports

import org.json.JSONArray;
import org.json.JSONObject;


public class LoginActivity extends ActionBarActivity {

    Button loginButton;
    public String username;
    private String password;
/*
    private final String jsonWSPath = "JSONWS_PATH_61";

    Session session = new SessionImpl("http://10.0.2.2:8080", new BasicAuthentication("test@liferay.com", "test"));

    HttpUtil hu = new HttpUtil();


    SignIn signIn = new SignIn(session, new JSONObjectAsyncTaskCallback() {

        @Override
        public void onSuccess(JSONObject userJSONObject) {
            System.out.println("Successful sign-in, user details: " + userJSONObject);
        }

        @Override
        public void onFailure(Exception e) {
            e.printStackTrace();
        }

    });
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = "MU Classifieds";
        this.setTitle(title);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MenuActivity.class);
                startActivity(i);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
