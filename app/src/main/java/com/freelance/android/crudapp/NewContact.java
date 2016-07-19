package com.freelance.android.crudapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.freelance.android.crudapp.data.DbHelper;

import java.util.HashMap;

/**
 * Created by Administrator on 07/19/2016.
 */
public class NewContact extends Activity {

    EditText firstName;
    EditText lastName;
    EditText phoneNumber;
    EditText emailAddress;
    EditText homeAddress;

    DbHelper dbHelper = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_addnew);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        homeAddress = (EditText) findViewById(R.id.homeAddress);

    }

    public void addNewContact(View view){
        HashMap<String, String> queryValuesMap = new HashMap<String, String>();

        queryValuesMap.put("FirstName", firstName.getText().toString());
        queryValuesMap.put("LastName", lastName.getText().toString());
        queryValuesMap.put("PhoneNumber", phoneNumber.getText().toString());
        queryValuesMap.put("EmailAddress", emailAddress.getText().toString());
        queryValuesMap.put("HomeAddress", homeAddress.getText().toString());

        dbHelper.insertContactInfo(queryValuesMap);
        Toast.makeText(getApplication(), "Saved Successfully.", Toast.LENGTH_SHORT).show();
        this.callMainActivity(view);
     }

    public void callMainActivity(View view){
        Intent i = new Intent(getApplication(), MainActivity.class);
        startActivity(i);
    }
}
