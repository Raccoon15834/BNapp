package das.anusha.bnapp;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionMenuView;

public abstract class ActivityWithMenu {
    public static void setUpMenu(Activity a, Menu mybar) {
        mybar = ((ActionMenuView) a.findViewById(R.id.bottombar)).getMenu();
        a.getMenuInflater().inflate(R.menu.menubar, mybar);
    }
    public static boolean setOptionsSelected(Activity a, MenuItem item){
        switch (item.getItemId()) {
            case R.id.speak:
                initiate(SpeakActivity.class, a);
                return true;
            case R.id.memorize:
                return true;
            case R.id.home:
                return true;
            case R.id.read:
                return true;
            case R.id.write:
                initiate(WriteActivity.class, a);
                return true;
            default:
                return false;

        }

    }
    public static void initiate(Class page, Activity a) {
        Intent starter = new Intent(a.getApplicationContext(), page);
        a.startActivity(starter);
    }
}
