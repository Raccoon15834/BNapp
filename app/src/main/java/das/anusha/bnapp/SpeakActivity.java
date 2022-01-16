package das.anusha.bnapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class SpeakActivity extends Activity implements SpeakLessonFragment.setSelector, NavigationBarView.OnItemSelectedListener{
    Menu mybar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak);
        //TODO add 8 lesson fragments by iterating through array of its looks,
        // embedding videos https://www.c-sharpcorner.com/article/adding-video-to-an-android-application/
        String[] titles = getResources().getStringArray(R.array.speakLessonNames);
        int[] lays = getResources().getIntArray(R.array.speakLayoutIds);
        for(int i=0; i<lays.length;i++){
            addFragment(titles[i], lays[i]);
        }

        BottomNavigationView nav =  (BottomNavigationView)findViewById(R.id.bottom_navigatin_view);
        nav.setOnItemSelectedListener(this);
        nav.setItemIconTintList(null);
    }

    private void addFragment(String title, int lay) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        Fragment myFrag = SpeakLessonFragment.newInstance(title, lay);
        ft.add(R.id.lessonList, myFrag , title);
        ft.commit();
    }

    @Override
    public void onSetSelect(int lay) {
        Intent starter = new Intent(getApplicationContext(), SpeakLesson.class);
//        Bundle extras = new Bundle();
//        extras.putInt("layout", lay);
        starter.putExtra("layout", lay);
        Log.i("speakDebug", lay+"");
        startActivity(starter);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return ActivityWithMenu.setOptionsSelected(this, item);
    }
}

//Bundle extras = mIntent.getExtras();
//extras.putString(key, value); - when starting different lessonsF
