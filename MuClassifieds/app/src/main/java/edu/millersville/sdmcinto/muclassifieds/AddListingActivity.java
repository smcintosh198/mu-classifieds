package edu.millersville.sdmcinto.muclassifieds;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AddListingActivity extends MenuActivity {
    public long id;
    EditText titleEditText;
    EditText priceEditText;
    EditText categoryEditText;
    EditText descriptionEditText;
    EditText postedEditText;
    EditText expiresEditText;
    EditText emailEditText;

    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_listing);
        initGui();
        initButton();
    }

    private void initGui() {

        titleEditText = (EditText) findViewById(R.id.addListingTitle);
        priceEditText = (EditText) findViewById(R.id.addListingPrice);
        categoryEditText = (EditText) findViewById(R.id.addListingCategory);
        descriptionEditText = (EditText) findViewById(R.id.addListingDescription);
        postedEditText = (EditText) findViewById(R.id.addListingPosted);
        expiresEditText = (EditText) findViewById(R.id.addListingExpires);
        emailEditText = (EditText) findViewById(R.id.addListingEmail);


    }

    public void initButton () {
        addButton = (Button) findViewById(R.id.addListingButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = runInsert();
                if (!success){
                    System.out.print("Unable to add Listing");
                    Toast t = Toast.makeText(mContext ,"Unable to add!", Toast.LENGTH_SHORT );
                    t.show();
                } else {
                    finish();
                }
            }
        });
    }

    public boolean runInsert () {
        boolean success;
        dbUtility.openDB();
        id = dbUtility.insert(titleEditText.getText().toString(),
            priceEditText.getText().toString(),
            categoryEditText.getText().toString(),
            descriptionEditText.getText().toString(),
            postedEditText.getText().toString(),
            expiresEditText.getText().toString(),
            emailEditText.getText().toString());
        if (id == -1) {
            success = false;
        } else {
            success = true;
        }
        dbUtility.closeDB();
        return success;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_listing, menu);
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
