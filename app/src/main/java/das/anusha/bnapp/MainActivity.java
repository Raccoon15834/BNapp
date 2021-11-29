package das.anusha.bnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //https://medium.com/@saranyaan2710/steps-to-create-a-android-menu-bbb17edd07af
        //https://blog.fossasia.org/adding-actionmenuview-to-place-navigation-buttons-in-phimpme-android/
        return super.onCreateOptionsMenu(menu);
    }
}