package com.example.jorge.helloworld;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import adapters.UserAdapter;
import models.User;

/**
 * Created by Jorge on 01/03/2018.
 */

public class UserListActivity extends ListActivity {

    UserAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<User> users = new ArrayList<User>();

        users.add(new User("Jorge Gallego Madrid", "Jorgec10"));
        users.add(new User("Adrián Miralles Palazón", "Adrymyry"));

        for (int i = 1; i < 11; i++) {
            String fullName = "UserFullName" + String.valueOf(i);
            String userName = "User" + String.valueOf(i);
            users.add(new User(fullName, userName));
        }

        adapter = new UserAdapter(this, users);
        setListAdapter(adapter);

    }
}
