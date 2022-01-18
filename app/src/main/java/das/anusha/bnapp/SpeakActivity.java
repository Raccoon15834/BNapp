package das.anusha.bnapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.TypedArray;
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

        String[] titles = getResources().getStringArray(R.array.speakLessonNames);
        //int[] lays = getResources().getIntArray(R.array.speakLayoutIds);
        TypedArray lays = getResources().obtainTypedArray(R.array.speakLayoutIds);

        for(int i=0; i<lays.length();i++){
            addFragment(titles[i], lays.getResourceId(i, 0));
            //Log.i("speakdebug",lays[i]+"-layoutfirst" );
        }
        lays.recycle();//whaat?

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
        Bundle extras = new Bundle();
        extras.putInt("layout", lay);
        Log.i("speakdebug",lay+"-layoutbefore" );
        starter.putExtras(extras);
        startActivity(starter);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return ActivityWithMenu.setOptionsSelected(this, item);
    }
}

//Bundle extras = mIntent.getExtras();
//extras.putString(key, value); - when starting different lessonsF
