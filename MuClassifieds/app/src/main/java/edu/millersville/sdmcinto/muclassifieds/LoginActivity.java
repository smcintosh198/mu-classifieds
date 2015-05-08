package edu.millersville.sdmcinto.muclassifieds;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteCursor;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import edu.millersville.sdmcinto.utils.SQLiteDBUtility;


//liferay imports


public class LoginActivity extends ActionBarActivity {

    Button loginButton;
    public String username = "";
    private EditText usernameEditText;
    private String password;

    public SQLiteDBUtility dbUtility;
    public SimpleCursorAdapter dAdapter;
    private String[] columns = new String[] {
            dbUtility.KEY_ID,
            dbUtility.KEY_TITLE,
            dbUtility.KEY_PRICE,
            dbUtility.KEY_CATEGORY,
            dbUtility.KEY_DESCRIPTION,
            dbUtility.KEY_POSTED,
            dbUtility.KEY_EXPIRES,
            dbUtility.KEY_EMAIL,
    };

    private int[] toView = new int[] {
            R.id.title,
            R.id.price,
            R.id.category,
            R.id.description,
            R.id.posted,
            R.id.expires,
            R.id.email,
            R.id.bytes,
            R.id.price
    };


    public static SharedPreferences settings;
    public static SharedPreferences.Editor prefEditor;

    private static final String APP_PREFERENCES = null;

    final Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = "MU Classifieds";
        this.setTitle(title);
        setContentView(R.layout.activity_login);
        new SQLCall().execute();
        getAppPreferences();
        initGui();
        initAdapter();



    }

    private void initAdapter () {
        dAdapter = new SimpleCursorAdapter(mContext,R.layout.listings_view, dbUtility.cursor, columns, toView);
    }

    private void initGui () {

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        if (!username.isEmpty()) {
            usernameEditText.setText(username);
        } else {
            //username = usernameEditText.getText().toString();
            usernameEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    username = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {

                    saveAppPreferences();
                }
            });

    }

        loginButton = (Button) findViewById(R.id.loginButton);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (usernameEditText.getText().toString().trim().length() == 0) {
                        CharSequence text = "Please fill in the (u). field!";
                        Toast toast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Intent i = new Intent(v.getContext(), MenuActivity.class);
                        startActivity(i);
                    }
                }
            });

    }

    private void getAppPreferences () {
        settings = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        prefEditor = settings.edit();
        if (settings.contains("username")) {
            username = settings.getString("username", username);
        } else {
            prefEditor.putString("username", username);
            prefEditor.commit();
        }
    }

    private void saveAppPreferences() {
        settings = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        prefEditor = settings.edit();

        prefEditor.putString("username", usernameEditText.getText().toString());
        prefEditor.commit();
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

    private class SQLCall extends AsyncTask<Object, Object, SimpleCursorAdapter> {

        protected void onPreExecute() {
            dbUtility = new SQLiteDBUtility(mContext);
            dbUtility.openDB();
        }
        @Override
        protected SimpleCursorAdapter doInBackground(Object... params) {
            SQLiteCursor cursor = dbUtility.getTracks();
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(mContext, R.layout.activity_login, cursor, columns, toView, 0);
            return adapter;
        }

        protected void onPostExecute(SimpleCursorAdapter adapter) {
            dbUtility.closeDB();
            dAdapter = adapter;
            if (dbUtility.isOpen()) {
                System.out.println("Database not closing");
                dbUtility.closeDB();
            } else {
                System.out.println(" Database closed. should be set for insert call");
            }

        }

    }

}
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
