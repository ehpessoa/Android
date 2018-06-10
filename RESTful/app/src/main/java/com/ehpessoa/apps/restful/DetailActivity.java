package com.ehpessoa.apps.restful;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageview_userpic;
    private TextView  textView_username;
    private TextView  textView_useremail;
    private TextView  textview_userphone;
    private TextView  textView_usercity;
    private TextView  textView_userzipcode;


    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        imageview_userpic = (ImageView) findViewById(R.id.imageView_detailActivity_userimage);
        textView_username = (TextView) findViewById(R.id.textView_detailActivity_username);
        textView_useremail = (TextView) findViewById(R.id.textView_detailActivity_useremail);
        textview_userphone = (TextView) findViewById(R.id.textView_detailActivity_userphone);
        textView_usercity = (TextView) findViewById(R.id.textView_detailActivity_usercity);
        textView_userzipcode = (TextView) findViewById(R.id.textview_detailActivity_userzipcode);


        user.setId(getIntent().getExtras().getString("userid"));
        ConsumeDetailUserService();

    }

    private void ConsumeDetailUserService()
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
                user.setPhone(jsonObject_response.getString("phone"));

                JSONObject  jsonObject_adress = jsonObject_response.getJSONObject("address");
                user.setCity(jsonObject_adress.getString("city"));
                user.setZipcode(jsonObject_adress.getString("zipcode"));


                Picasso.with(this).load(R.mipmap.userlist_userpic_48px).into(imageview_userpic);
                textView_username.setText("Name: "+user.getName());
                textView_useremail.setText("Email: "+user.getEmail());
                textview_userphone.setText("Phone: "+user.getPhone());
                textView_usercity.setText("City: "+user.getCity());
                textView_userzipcode.setText("Zip Code: "+user.getZipcode());
            }


        } catch (JSONException e) {

            Log.d("Users", "Error " + e.toString());

        }


    }

}
