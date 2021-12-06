package das.anusha.bnapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ReadActivity extends Activity {
    Menu mybar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        ActivityWithMenu.setUpMenu(this, mybar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (!ActivityWithMenu.setOptionsSelected(this, item)) return super.onOptionsItemSelected(item);
        return true;
    }
}
