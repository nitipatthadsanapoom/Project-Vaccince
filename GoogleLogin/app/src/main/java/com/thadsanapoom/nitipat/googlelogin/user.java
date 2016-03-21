package com.thadsanapoom.nitipat.googlelogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import com.github.clans.fab.FloatingActionButton;
import com.thadsanapoom.nitipat.googlelogin.io.DBHandler;
import com.thadsanapoom.nitipat.googlelogin.io.model.Person;
import com.thadsanapoom.nitipat.googlelogin.ui.activities.UserCRUDActivity;
import com.thadsanapoom.nitipat.googlelogin.ui.adabter.PersonListAdabter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nitipat on 15/3/2559.
 */
public class user extends Activity  {

   // private Button addButton;
    private ListView listView;
    private PersonListAdabter personListAdabter;
    TextView tv;

    private List<Person> personList = new ArrayList<>();
    private DBHandler dbHandler;


    private FloatingActionButton menu_item;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        tv = (TextView) findViewById(R.id.tv);
        try {
            listView = (ListView) findViewById(R.id.list);
            listView.setItemsCanFocus(false);


            //addButton = (Button) findViewById(R.id.add_btn); //edit
            menu_item = (FloatingActionButton) findViewById(R.id.menu_item);

            refreshData();

        } catch (Exception e){
            Log.e("some error","" + e);
        }

        menu_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //System.out.print("\"Hello\"");
                addUpdateUserPage();
            }
        });

    }


    private void addUpdateUserPage() {
        Intent i = new Intent(this, UserCRUDActivity.class);
        i.putExtra("called", "add");
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    public void refreshData() {
        personList.clear();
        dbHandler = new DBHandler(this);
        ArrayList<Person> data = dbHandler.getPerson();

        for (int i = 0; i < data.size(); i++) {

            int id = data.get(i).getId();
            String name = data.get(i).getName();
            String lastname = data.get(i).getLastName();
            String birthday = data.get(i).getBirthDay();
            String gender = data.get(i).getGender();
            String weight = data.get(i).getWeight();
            String lenght = data.get(i).getLenght();
            String bloodtype = data.get(i).getBloodType();
            String allergic = data.get(i).getAllergic();
            Person psn = new Person();
            psn.setId(id);
            psn.setName(name);
            psn.setLastName(lastname);
            psn.setBirthDay(birthday);
            psn.setGender(gender);
            psn.setWeigth(weight);
            psn.setLenght(lenght);
            psn.setBloodType(bloodtype);
            psn.setAllergic(allergic);


            personList.add(psn);
        }
        dbHandler.close();
        personListAdabter = new PersonListAdabter(this, R.layout.listview_row, personList);
        listView.setAdapter(personListAdabter);
        personListAdabter.notifyDataSetChanged();
    }


}
