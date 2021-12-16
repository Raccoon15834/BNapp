package das.anusha.bnapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
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
        int[] imgs = getResources().getIntArray(R.array.speakImages);
        int[] lays = getResources().getIntArray(R.array.speakLayoutIds);
        for(int i=0; i<lays.length;i++){
            addFragment(titles[i], imgs[i], lays[i]);
        }

        BottomNavigationView nav =  (BottomNavigationView)findViewById(R.id.bottom_navigatin_view);
        nav.setOnItemSelectedListener(this);
    }

    private void addFragment(String title, int img, int lay) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        Fragment myFrag = SpeakLessonFragment.newInstance(img, title, lay);
        ft.add(R.id.lessonList, myFrag , title);
        ft.commit();
    }

    @Override
    public void onSetSelect(int lay) {
        Intent starter = new Intent(getApplicationContext(), SpeakLesson.class);
        Bundle extras = starter.getExtras();
        extras.putInt("layout", lay);
        startActivity(starter);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return ActivityWithMenu.setOptionsSelected(this, item);
    }
}

//Bundle extras = mIntent.getExtras();
//extras.putString(key, value); - when starting different lessonsF
