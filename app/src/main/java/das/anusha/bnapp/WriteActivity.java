package das.anusha.bnapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.SwitchCompat;

import com.firebase.client.Firebase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//COMBINE recycler view - https://www.geeksforgeeks.org/staggered-gridview-in-android-with-example/
//WITH firebase database - https://github.com/dltra/FirebaseChatRoom/commit/82d7895b9d7ecaffb4419c3b866c0d2d6eb3b573
//other chat tutorials
//https://code.tutsplus.com/tutorials/how-to-create-an-android-chat-app-using-firebase--cms-27397
//https://medium.com/@meetpatel12121995/realtime-chat-using-firebase-database-b65ee23f3b6a
//https://www.raywenderlich.com/22067733-firebase-tutorial-real-time-chat

public class WriteActivity extends Activity implements NavigationBarView.OnItemSelectedListener{
    AppCompatImageButton check;
    FirebaseDatabase mFD;
    SwitchCompat anonOption;
    EditText username;
    Activity a  = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        setUpUserName();
        setUpSubmissionChecker();//TODO ensure that it meets post requirements
        //mFD = FirebaseDatabase.getInstance();
        //TODO just for now, clearing all inputs

        BottomNavigationView nav =  (BottomNavigationView)findViewById(R.id.bottom_navigatin_view);
        nav.setSelectedItemId(R.id.write);
        nav.setOnItemSelectedListener(this);
    }


    private void setUpSubmissionChecker() {
        check = (AppCompatImageButton) findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title = (EditText) findViewById(R.id.titlePost);
                EditText content = (EditText) findViewById(R.id.textPost);
                String titleStr = String.valueOf(title.getText());
                String contentStr = String.valueOf(content.getText());
                String username = getUserName();
                //clear edit texts
                title.setText("");
                content.setText("");
                //input in database
                //TODO increment number of posts too
                //what the heck is a .child??? why not just getReference()? - https://www.techotopia.com/index.php?title=Writing_Firebase_Realtime_Database_Data&mobileaction=toggle_view_mobile#
                //String key = messageRef.push().getKey();
                //chatMessageModel.messageId = key;
                //messageRef.child(key).setValue(chatMessageModel); CAN DO THIS orrr directly ref.push().setValue();
                // update post in multiple places https://firebase.google.com/docs/database/android/read-and-write#update_specific_fields
                //automatically create pathways - by calling getReference(/test/data/message1);
                //do set numbers like #posts pathway through android?
                //:: get username, add posts to its user, add posts to all posts
                PostData newPost = new PostData(contentStr, titleStr, username);
                //DatabaseReference mDR = mFD.getReference("/Users/"+FirebaseAuth.getInstance().getCurrentUser().toString());
                //mDR.push().setValue(newPost);
                mFD.getReference("/AllPosts").push().setValue(newPost);
                ActivityWithMenu.initiate(MainActivity.class, a);
            }
        });
    }

    private String getUserName() {
        //if(anonOption.select)
        if( anonOption.isChecked()) return "Anonymous";
        String user = String.valueOf(username.getText());
        username.setText("");
        anonOption.setChecked(false);
        return user;
        //https://dev.to/akshayranagujjar/how-to-make-custom-switch-in-android-5d1d custom look
        //<?xml version="1.0" encoding="utf-8"?>
        //<shape xmlns:android="http://schemas.android.com/apk/res/android">
        //    <corners  android:radius="50dp"/>
        //    <solid android:color="@color/purple"></solid>
        //</shape>
        //set on selected/on touched different colors
    }
    private void setUpUserName() {
        username = (EditText) findViewById(R.id.userPost);
        anonOption = (SwitchCompat) findViewById(R.id.anonSwitch);
        anonOption.setChecked(true);
        username.setVisibility(View.GONE);
        anonOption.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) username.setVisibility(View.GONE);
                else username.setVisibility(View.VISIBLE);
            }
        });

    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return ActivityWithMenu.setOptionsSelected(this, item);
    }
}
