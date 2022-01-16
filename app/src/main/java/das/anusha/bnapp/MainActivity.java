package das.anusha.bnapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarMenu;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import das.anusha.bnapp.ActivityWithMenu;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    Menu mybar;
    AppCompatImageButton settings, plus, search;
    AppCompatButton about;
    FirebaseDatabase mFD;
    RecyclerView posts;
    BottomNavigationView nav;
    Activity a = this;
    chilListener mPostListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ctrl+B to move to step
        setUpButtons();
        setUpRecyclerView();
        nav =  (BottomNavigationView)findViewById(R.id.bottom_navigatin_view);
        nav.setSelectedItemId(R.id.home);
        nav.setItemIconTintList(null);
        nav.setOnItemSelectedListener(this);


//        //set up database listener
        mFD = FirebaseDatabase.getInstance();
        mPostListener = new chilListener();
        mFD.getReference("/AllPosts").addChildEventListener(mPostListener);
//
//        //add initial posts TODO
        setPostsAlreadyThere();

    }

    private void setPostsAlreadyThere() {
        Log.i("dbListen"," posts");
        mFD.getReference("/AllPosts").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<DataSnapshot> initLIst = ((PostsRecyclerViewAdapter)posts.getAdapter()).mDS;
                for(DataSnapshot i: snapshot.getChildren()){
                    initLIst.add(0, snapshot);
                }
                Log.i("dbListen","inititializing posts");
                Log.i("dbListen","beginningList"+initLIst.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("dbListen","inititializing posts??");
            }
        });
        posts.getAdapter().notifyDataSetChanged();
    }

    private class chilListener implements ChildEventListener{

        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Log.i("dbListen", "newPost");
            ((PostsRecyclerViewAdapter)posts.getAdapter()).mDS.add(0, snapshot);
            posts.getAdapter().notifyItemInserted(0);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    }
    private void setUpRecyclerView() {
        RecyclerView.LayoutManager mLM = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        posts = (RecyclerView) findViewById(R.id.postScroll);
        posts.setLayoutManager(mLM);
        posts.setAdapter(new PostsRecyclerViewAdapter(this));
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        nav.setSelectedItemId(R.id.home);
//        if(FirebaseAuth.getInstance().getCurrentUser()==null){
//            startSignIn();//TODO sign in with google or something, use firebase assistant
//            //temp;
//            FirebaseAuth.getInstance().signInAnonymously();
//            ////FirebaseAuth.getInstance().updateCurrentUser(FirebaseAuth.getInstance().getCurrentUser());
//            //mFD.getReference("/Users/"+FirebaseAuth.getInstance().getCurrentUser().toString());
//        }
//    }
//https://firebase.google.com/docs/auth/android/custom-auth
    private void startSignIn() {
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
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            return ActivityWithMenu.setOptionsSelected(this, item);
        }

    @Override
    protected void onResume() {
        super.onResume();
        //nav.setSelectedItemId(R.id.home);TODO
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFD.getReference("/AllPosts").removeEventListener(mPostListener);
    }
}