package com.example.ex3ap2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {

//    private AppDB db;
//    private PostDao postDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

//        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "PostsDB")
//                .allowMainThreadQueries().build();
//        postDao = db.postDao();

//        Button addContactBtn = findViewById(R.id.addContactBtn);
//        addContactBtn.setOnClickListener(view -> {
//            EditText etItemUsername = findViewById(R.id.addContactUsername);
//            Post post = new Post(etItemUsername.getText().toString());
//            postDao.insert(post);
//            finish();
//        });
    }
}