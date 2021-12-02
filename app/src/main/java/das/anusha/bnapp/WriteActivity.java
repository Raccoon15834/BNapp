package das.anusha.bnapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.SwitchCompat;
//COMBINE recycler view - https://www.geeksforgeeks.org/staggered-gridview-in-android-with-example/
//WITH firebase database - https://github.com/dltra/FirebaseChatRoom/commit/82d7895b9d7ecaffb4419c3b866c0d2d6eb3b573
//look at other chat tutorials
//https://code.tutsplus.com/tutorials/how-to-create-an-android-chat-app-using-firebase--cms-27397
//https://medium.com/@meetpatel12121995/realtime-chat-using-firebase-database-b65ee23f3b6a
//https://www.raywenderlich.com/22067733-firebase-tutorial-real-time-chat

public class WriteActivity extends Activity {
    AppCompatImageButton check;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        setUpSubmissionChecker();//TODO ensure that it meets post requirements
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


            }
        });
    }

    private String getUserName() {
        SwitchCompat anonOption = (SwitchCompat) findViewById(R.id.anonOption);
        //if(anonOption.select)
        if( anonOption.isChecked()) return "Anonymous";
        EditText username = (EditText) findViewById(R.id.userPost);
        return String.valueOf(username.getText());
        //https://dev.to/akshayranagujjar/how-to-make-custom-switch-in-android-5d1d custom look
        //<?xml version="1.0" encoding="utf-8"?>
        //<shape xmlns:android="http://schemas.android.com/apk/res/android">
        //    <corners  android:radius="50dp"/>
        //    <solid android:color="@color/purple"></solid>
        //</shape>
        //set on selected/on touched different colors
    }
}
