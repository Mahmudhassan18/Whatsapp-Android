package com.example.ex3ap2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ContactsActivity extends AppCompatActivity {

    //private AppDB db;
//    private PostDao postDao;
//    private List<Post> posts;
//    private ArrayAdapter<Post> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        //db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "PostsDB")
        //.allowMainThreadQueries().build();
        //postDao = db.postDao();

//        FloatingActionButton addContactFloatingBtn = findViewById(R.id.addContactFloatingBtn);
//        addContactFloatingBtn.setOnClickListener(view -> {
//            Intent addContactIntent = new Intent(this, AddContactActivity.class);
//            startActivity(addContactIntent);
//        });
//
//        posts = new ArrayList<>();
//
//        ListView lvContacts = findViewById(R.id.lvContacts);
//        adapter = new ArrayAdapter<Post>(this,
//                android.R.layout.simple_list_item_1,
//                posts);
//
//        lvContacts.setAdapter(adapter);
//
//         lvContacts.setOnItemLongClickListener((adapterView, view, i, l) -> {
//             Post post = posts.remove(i);
//             postDao.delete(post);
//             adapter.notifyDataSetChanged();
//             return true;
//        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        posts.clear();
//        posts.addAll(postDao.index());
//        adapter.notifyDataSetChanged();
//    }
}