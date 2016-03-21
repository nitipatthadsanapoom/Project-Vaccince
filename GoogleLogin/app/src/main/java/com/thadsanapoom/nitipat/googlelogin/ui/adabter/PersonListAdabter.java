package com.thadsanapoom.nitipat.googlelogin.ui.adabter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import com.thadsanapoom.nitipat.googlelogin.io.model.Person;
import com.thadsanapoom.nitipat.googlelogin.R;
import com.thadsanapoom.nitipat.googlelogin.io.DBHandler;

import com.thadsanapoom.nitipat.googlelogin.ui.activities.UserCRUDActivity;
import com.thadsanapoom.nitipat.googlelogin.user;

/**
 * Created by Nitipat on 20/3/2559.
 */
public class PersonListAdabter extends ArrayAdapter<Person>{

        Activity activity;
        int layoutResourceId;
        Person user;
        List<Person> data = new ArrayList<Person>();

        public PersonListAdabter(Activity act, int layoutResourceId, List<Person> data) {
            super(act, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.activity = act;
            this.data = data;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            UserHolder holder = null;

            if (row == null) {
                LayoutInflater inflater = LayoutInflater.from(activity);

                row = inflater.inflate(layoutResourceId, parent, false);
                holder = new UserHolder();
                holder.name = (TextView) row.findViewById(R.id.user_name_txt);

                holder.edit = (Button) row.findViewById(R.id.btn_update);
                holder.delete = (Button) row.findViewById(R.id.btn_delete);
                row.setTag(holder);
            } else {
                holder = (UserHolder) row.getTag();
            }
            user = data.get(position);
            holder.edit.setTag(user.getId());
            holder.delete.setTag(user.getId());
            holder.name.setText(user.getName());


            holder.edit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.i("Edit Button Clicked", "**********");

                    Intent update_user = new Intent(activity,
                            UserCRUDActivity.class);
                    update_user.putExtra("called", "update");
                    update_user.putExtra("USER_ID", v.getTag().toString());
                    activity.startActivity(update_user);

                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {

                    // show a message while loader is loading

                    AlertDialog.Builder adb = new AlertDialog.Builder(activity);
                    adb.setTitle("Delete?");
                    adb.setMessage("Are you sure you want to delete ");
                    final int user_id = Integer.parseInt(v.getTag().toString());
                    adb.setNegativeButton("Cancel", null);
                    adb.setPositiveButton("Ok",
                            new AlertDialog.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // MyDataObject.remove(positionToRemove);
                                    DBHandler dbHandler = new DBHandler(
                                            activity.getApplicationContext());
                                    dbHandler.deleteContact(user_id);

                                    ((user) activity).refreshData();
                                }
                            });
                    adb.show();
                }

            });

            return row;
        }


        class UserHolder {

            TextView name;
            Button edit;
            Button delete;
        }
    }



