package com.thadsanapoom.nitipat.googlelogin.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.Spinner;

import com.thadsanapoom.nitipat.googlelogin.R;
import com.thadsanapoom.nitipat.googlelogin.io.DBHandler;
import com.thadsanapoom.nitipat.googlelogin.io.model.Person;
import com.thadsanapoom.nitipat.googlelogin.user;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Nitipat on 20/3/2559.
 */
public class UserCRUDActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {


        EditText nameEditText, lastnameEditText, birthdayeEditText, genderEditText, weightEditText, lenghtEditText, bloodtypeEditText, allergicEditText;
        Button saveButton, updateButton;
        RelativeLayout add_view, update_view;
        TextView tvadd_gender;

        Spinner spinner;;
        String item;


        String validName = null, Toast_msg = null, validLastName = null, validBirthDay = null, validGender = null, validWeigth = null, validLenght = null, validBloodType = null, validAllergic = null, valid_user_id = "";
        int USER_ID;
        DBHandler dbHandler = new DBHandler(this);

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user);

            // set screen
            Set_Add_Update_Screen();

            // set visibility of view as per calling activity
            String called_from = getIntent().getStringExtra("called");

            if (called_from.equalsIgnoreCase("add")) {
                add_view.setVisibility(View.VISIBLE);
                update_view.setVisibility(View.GONE);
            } else {

                update_view.setVisibility(View.VISIBLE);
                add_view.setVisibility(View.GONE);
                USER_ID = Integer.parseInt(getIntent().getStringExtra("USER_ID"));

                Person p = dbHandler.getPerson(USER_ID);

                nameEditText.setText(p.getName());
                lastnameEditText.setText(p.getLastName());
                birthdayeEditText.setText(p.getBirthDay());
                tvadd_gender.setText(p.getGender());
                weightEditText.setText(p.getWeight());
                lenghtEditText.setText(p.getLenght());
                bloodtypeEditText.setText(p.getBloodType());
                allergicEditText.setText(p.getAllergic());



                // dbHandler.close();
            }
            spinner.setOnItemSelectedListener(this);
            test();

            saveButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    validName = nameEditText.getText().toString();
                    validLastName = lastnameEditText.getText().toString();
                    validBirthDay = birthdayeEditText.getText().toString();
                    validGender = tvadd_gender.getText().toString();
                    validWeigth = weightEditText.getText().toString();
                    validLenght = lenghtEditText.getText().toString();
                    validBloodType = bloodtypeEditText.getText().toString();
                    validAllergic = allergicEditText.getText().toString();

                    // check the value state is null or not
                    if (nameEditText != null && validLastName != null && validBirthDay != null && validGender != null && validWeigth != null && validLenght != null && validBloodType != null && validAllergic != null) {

                        dbHandler.addPerson(new Person(validName, validLastName, validBirthDay, validGender, validWeigth, validLenght, validBloodType, validAllergic));
                        Toast_msg = "Data inserted successfully";
                        Show_Toast(Toast_msg);
                        Reset_Text();

                        Intent view_user = new Intent(UserCRUDActivity.this, user.class);
                        view_user.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(view_user);
                        finish();
                    } else {
                        Toast_msg = "Sorry Some Fields are missing.\nPlease Fill up all.";
                        Show_Toast(Toast_msg);
                    }


                }
            });

            updateButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    validName = nameEditText.getText().toString();
                    validLastName = lastnameEditText.getText().toString();
                    validBirthDay = birthdayeEditText.getText().toString();
                    validGender = tvadd_gender.getText().toString();
                    validWeigth = weightEditText.getText().toString();
                    validLenght = lenghtEditText.getText().toString();
                    validBloodType = bloodtypeEditText.getText().toString();
                    validAllergic = allergicEditText.getText().toString();

                    if (validName != null && validLastName != null&& validBirthDay != null && validGender != null && validWeigth != null && validLenght != null && validBloodType != null && validAllergic != null)
                    {

                        dbHandler.updatePerson(new Person(USER_ID, validName, validLastName, validBirthDay, validGender, validWeigth, validLenght, validBloodType, validAllergic));
                        dbHandler.close();
                        Toast_msg = "Data Update successfully";
                        Show_Toast(Toast_msg);
                        Reset_Text();

                        Intent view_user = new Intent(UserCRUDActivity.this, user.class);
                        view_user.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(view_user);
                        finish();

                    } else {
                        Toast_msg = "Sorry Some Fields are missing.\nPlease Fill up all.";
                        Show_Toast(Toast_msg);
                    }

                }
            });

        }

        public void test(){
            // Spinner Drop down elements
            List<String> gender = new ArrayList<String>();
            gender.add("Male");
            gender.add("Female");

            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);



            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
            ///////////////////////////////


        }




        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            item = adapterView.getItemAtPosition(i).toString();
            tvadd_gender.setText(String.valueOf(item));
        }
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

        public void Set_Add_Update_Screen() {

            nameEditText = (EditText) findViewById(R.id.add_name);
            lastnameEditText = (EditText) findViewById(R.id.add_lastname);
            birthdayeEditText = (EditText) findViewById(R.id.add_birthday);
            // genderEditText = (EditText) findViewById(R.id.add_gender);
            weightEditText = (EditText) findViewById(R.id.add_weightt);
            lenghtEditText = (EditText) findViewById(R.id.add_lenght);
            bloodtypeEditText = (EditText) findViewById(R.id.add_bloodtype);
            allergicEditText = (EditText) findViewById(R.id.add_allergic);

            tvadd_gender = (TextView) findViewById(R.id.tvadd_gender);


            ////-------Dropdown New version////////////////////////////////////////////////////////
            // Spinner element
            spinner = (Spinner) findViewById(R.id.spinner);

            saveButton = (Button) findViewById(R.id.add_save_btn);
            updateButton = (Button) findViewById(R.id.update_btn);

            add_view = (RelativeLayout) findViewById(R.id.add_view);
            update_view = (RelativeLayout) findViewById(R.id.update_view);
            add_view.setVisibility(View.GONE);
            update_view.setVisibility(View.GONE);

        }


        public void Show_Toast(String msg) {
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        }

        public void Reset_Text() {

            nameEditText.getText().clear();
            lastnameEditText.getText().clear();
            birthdayeEditText.getText().clear();

            weightEditText.getText().clear();
            lenghtEditText.getText().clear();
            bloodtypeEditText.getText().clear();
            allergicEditText.getText().clear();

        }
    }


