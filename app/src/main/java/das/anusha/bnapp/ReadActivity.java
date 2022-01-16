package das.anusha.bnapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class ReadActivity extends Activity implements NavigationBarView.OnItemSelectedListener, fishCanvas.lvlSelector {
    Menu mybar;
    ArrayList<String[]> readContents = new ArrayList<String[]>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        BottomNavigationView nav =  (BottomNavigationView)findViewById(R.id.bottom_navigatin_view);
        nav.setSelectedItemId(R.id.read);
        nav.setOnItemSelectedListener(this);
        nav.setItemIconTintList(null);

        Resources res = getResources();
        String p = getPackageName();

        int i=1;
        while(res.getIdentifier("reader"+i, "array", p)!=0){
            readContents.add(res.getStringArray(res.getIdentifier("reader"+i, "array", p)));
            i++;
        }
        fishCanvas mFC = (fishCanvas)findViewById(R.id.lvlMap);
        mFC.setNumOfLvls(i-1);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return ActivityWithMenu.setOptionsSelected(this, item);
    }

    @Override
    public void onLvlSelect(int f) {
        String[] content = readContents.get(f);
        Intent starter = new Intent(getApplicationContext(), Reading.class);
        Bundle extras = new Bundle();
        extras.putString("titleStr", readContents.get(f)[0]);
        extras.putString("descriptionStr", readContents.get(f)[1]);
        extras.putString("txtStr", readContents.get(f)[2]);
        starter.putExtras(extras);
        startActivity(starter);
    }
}
