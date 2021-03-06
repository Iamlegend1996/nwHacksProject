package com.example.pariay.wheredidiputit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItems extends AppCompatActivity {

    DataBaseHelper mDataBaseHelper;

    public static final String TAG = "AddDataActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        setTitle("Where Did I Put It?");

        final EditText itemToAdd = (EditText) findViewById(R.id.itemToAdd);

        final EditText itemLocation = (EditText) findViewById(R.id.itemLocation);

        Button addItem = (Button) findViewById(R.id.addItem);

        mDataBaseHelper = new DataBaseHelper(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = itemToAdd.getText().toString();
                String location = itemLocation.getText().toString();

                if (item.length() == 0) {
                    toastMessage("Please enter an Item name");
                }
                else if (location.length() == 0) {
                    toastMessage("Please enter a location for the item");
                }
                else if (location.length() == 0 && item.length() == 0) {
                    toastMessage("Please enter something");
                }
                else {
//                    Items.itemName.add(item);
//                    Items.itemLocation.add(location);
                    addData(item, location);
                    onBackPressed();
//                    toastMessage("Item added");

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addData(String item, String location) {
        boolean insertData = mDataBaseHelper.addData(item, location);
        if (insertData) {
            toastMessage("Insertion successful");
        } else {
            toastMessage("Something went wrong");
        }
    }

    public void toastMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }
}
