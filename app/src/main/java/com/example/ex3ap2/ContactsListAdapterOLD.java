package com.example.ex3ap2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ex3ap2.usersDB.User;

import java.util.ArrayList;

public class ContactsListAdapterOLD extends ArrayAdapter<User> {
    LayoutInflater inflater;

    public ContactsListAdapterOLD(Context ctx, ArrayList<User> userArrayList){
        super(ctx, R.layout.custom_contacts_list, userArrayList);
        this.inflater = LayoutInflater.from(ctx);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        User user = getItem(position);

        if (convertView == null){
            convertView = inflater.inflate(R.layout.custom_contacts_list, parent, false);
        }
        ImageView profilePic = convertView.findViewById(R.id.contact_profile_image);
        TextView username = convertView.findViewById(R.id.contact_user_name);

        username.setText(user.getUsername());
        return convertView;
    }
}
