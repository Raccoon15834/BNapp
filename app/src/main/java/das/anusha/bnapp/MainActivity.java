package das.anusha.bnapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import das.anusha.bnapp.ActivityWithMenu;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Menu mybar;
    AppCompatImageButton settings, plus, search;
    AppCompatButton about;
    FirebaseDatabase mFD;
    Activity a = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ctrl+B to move to step
        setUpButtons();
        //set up RecyclerView
        RecyclerView.LayoutManager mLM = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        RecyclerView posts = (RecyclerView) findViewById(R.id.postScroll);
        posts.setLayoutManager(mLM);
        posts.setAdapter(new PostsRecyclerViewAdapter(this));
        //set up database listener
        FirebaseApp app = FirebaseApp.initializeApp(this);
        mFD = FirebaseDatabase.getInstance();
       // mFD = FirebaseDatabase.getInstance(app);
        mFD.getReference("AllPosts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                posts.setAdapter(new PostsRecyclerViewAdapter(getApplicationContext(), dataSnapshot));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //HOW TO GET STRINGS

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startSignIn();//TODO sign in with google or something, use firebase assistant
            //temp;
            FirebaseAuth.getInstance().signInAnonymously();
            ////FirebaseAuth.getInstance().updateCurrentUser(FirebaseAuth.getInstance().getCurrentUser());
            //mFD.getReference("/Users/"+FirebaseAuth.getInstance().getCurrentUser().toString());
        }
    }
//https://firebase.google.com/docs/auth/android/custom-auth
    private void startSignIn() {
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.i("menubar", "menu clicked!!!!!!!");
        if (!ActivityWithMenu.setOptionsSelected(this, item)) return super.onOptionsItemSelected(item);
        return true;
    }

    private void setUpButtons() {
        about = (AppCompatButton)findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        plus = (AppCompatImageButton) findViewById(R.id.add);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityWithMenu.initiate(WriteActivity.class, a);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActivityWithMenu.setUpMenu(this, mybar); //TODO highlight selected, smooth transition
        return true;
    }
}