package com.freelance.android.crudapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.freelance.android.crudapp.data.DbHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity{

    Intent i;
    TextView contactId;

    DbHelper dbHelper = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<HashMap<String, String>> cList = dbHelper.getAllContactInfo();
        dbHelper.getAllContactInfo();

        if(cList.size() != 0){
            ListView lv = getListView();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    contactId = (TextView) view.findViewById(R.id.contactId);
                    String contactIdValue = contactId.getText().toString();

                    Intent i = new Intent(getApplication(), EditContact.class);
                    i.putExtra("contactId", contactIdValue);
                    startActivity(i);
                }
            });

            ListAdapter lA = new SimpleAdapter(
                MainActivity.this, cList, R.layout.contact_entry,
                new String[] {"contactId", "LastName", "FirstName"},
                new int[] {R.id.contactId, R.id.lName, R.id.fName}
            );
            setListAdapter(lA);
        }
    }

    public void showAddContact(View view){
        Intent i = new Intent(getApplication(), NewContact.class);
        startActivity(i);
    }
}
