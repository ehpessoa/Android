package com.ehpessoa.apps.restful;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by PessoaEv on 30/09/2017.
 */

public class UserlistAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<User> arraylist_users = new ArrayList<>(0);
    private ArrayList<String> arrayList_mnameusers = new ArrayList<>(0);
    private ArrayList<String> arrayList_memailusers = new ArrayList<>(0);
    private ArrayList<String> arrayList_midusers = new ArrayList<>(0);


    public UserlistAdapter(Context context,ArrayList<String> arrayList_nameusers, ArrayList<String> arrayList_emailusers,ArrayList<String> arrayList_idusers) {
        this.context = context;
        this.arrayList_mnameusers = arrayList_nameusers;
        this.arrayList_memailusers = arrayList_emailusers;
        this.arrayList_midusers = arrayList_idusers;

    }

    @Override
    public User getItem(int position) {
        return arraylist_users.get(position);
    }


    @Override
    public int getCount() {
        return arrayList_mnameusers.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getViewTypeCount() {
        return arrayList_mnameusers.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {


        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.userlist_item, viewGroup, false);
        }

        ConstraintLayout  constraintLayout_itemuser = (ConstraintLayout) view.findViewById(R.id.constrainlayout_userlist_item);
        ImageView imageView_picuser = (ImageView) view.findViewById(R.id.imageView_userlist_imageuser);
        TextView textview_nameuser = (TextView) view.findViewById(R.id.textView_userlist_nameuser);
        TextView textView_emailuser = (TextView) view.findViewById(R.id.textView_userlist_emailuser);

        constraintLayout_itemuser.setClickable(true);
        constraintLayout_itemuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("userid",arrayList_midusers.get(position));
                context.startActivity(intent);
            }
        });

        Picasso.with(context).load(R.mipmap.userlist_userpic_48px).into(imageView_picuser);

        textview_nameuser.setText(arrayList_mnameusers.get(position));
        textView_emailuser.setText("Email: " + arrayList_memailusers.get(position));


        return view;
    }

}
