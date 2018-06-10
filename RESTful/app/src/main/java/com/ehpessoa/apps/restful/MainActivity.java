package com.ehpessoa.apps.restful;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listview_users;

    private ArrayList<String> arraylist_nameusers;
    private ArrayList<String> arraylist_emailusers;
    private ArrayList<String> arrayList_idusers;
    private User user = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listview_users = (ListView) findViewById(R.id.listview_main_listviewusers);
        arraylist_nameusers = new ArrayList<>(0);
        arraylist_emailusers = new ArrayList<>(0);
        arrayList_idusers = new ArrayList<>(0);


        ConsumeUserService();



    }

    private void ConsumeUserService()
    {
        AndroidNetworking.get("http://jsonplaceholder.typicode.com/users")
                .setTag("Users")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {

                        Parseinfo(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("Users", "Error in service " + anError.toString());

                    }
                });
    }



    private void Parseinfo(String response) {
        try {

            JSONArray jsonArray_response = new JSONArray(response);


            for (int i = 0; i < jsonArray_response.length(); i++) {
                JSONObject jsonObject_response = jsonArray_response.getJSONObject(i);
                user.setName(jsonObject_response.getString("name"));
                user.setEmail(jsonObject_response.getString("email"));
                user.setId(jsonObject_response.getString("id"));


                arraylist_nameusers.add(user.getName());
                arraylist_emailusers.add(user.getEmail());
                arrayList_idusers.add(user.getId());


                UserlistAdapter userlistAdapter = new UserlistAdapter(this, arraylist_nameusers, arraylist_emailusers, arrayList_idusers);
                listview_users.setAdapter(userlistAdapter);

            }


        } catch (JSONException e) {

            Log.d("Users", "Error " + e.toString());

        }


    }


}
