package das.anusha.bnapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class SpeakLesson extends Activity {
    AppCompatButton back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int tmp = savedInstanceState.getInt("layout");
        setContentView(tmp);
        //Different set content views depending on which lesson clicked

//        back = (AppCompatButton) findViewById();
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent starter = new Intent(getApplicationContext(), SpeakActivity.class);
//                startActivity(starter);
//            }
//        });
    }
}
