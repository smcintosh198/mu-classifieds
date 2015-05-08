package edu.millersville.sdmcinto.muclassifieds;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MenuActivity extends LoginActivity {
    private Button listingsButton;
    private Button myListingsButton;
    private Button addListingButton;
    private TextView userNameLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initGui();
    }

    private void initGui () {
        userNameLabel = (TextView) findViewById(R.id.usernameTitle);
        if (!username.isEmpty()) {
            userNameLabel.setText(username);
        } else {
            System.out.println("username is null");
        }

        listingsButton = (Button) findViewById(R.id.listingsButton);
        listingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ListingsActivity.class);
                startActivity(i);
            }
        });

        myListingsButton = (Button) findViewById(R.id.myListingsButton);
        myListingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent (v.getContext(), );
                //startActivity(i);
            }
        });

        addListingButton = (Button)findViewById(R.id.addListingsButton);
        addListingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (v.getContext(), AddListingActivity.class);
                startActivity(i);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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
