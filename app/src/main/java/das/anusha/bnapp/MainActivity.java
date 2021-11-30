package das.anusha.bnapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Menu mybar;
    AppCompatImageButton settings, plus, search;
    AppCompatButton about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ctrl+B to move to step
        setUpMenu(); //TODO highlight selected, smooth transition
        setUpButtons();
    }

    private void setUpMenu() {
        mybar = ((ActionMenuView) findViewById(R.id.bottombar)).getMenu();
        getMenuInflater().inflate(R.menu.menubar, mybar);
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
                Intent starter = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(starter);
            }
        });
    }


}