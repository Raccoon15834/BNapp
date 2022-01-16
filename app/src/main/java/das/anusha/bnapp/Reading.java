package das.anusha.bnapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Reading extends Activity {
    TextView title, description, txt;
    String titleStr, descriptionStr, txtStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readerview);

        Bundle b = getIntent().getExtras();
        titleStr = b.getString("titleStr");
        descriptionStr = b.getString("descriptionStr");
        txtStr = b.getString("txtStr");
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        txt = (TextView) findViewById(R.id.txt);
        title.setText(titleStr);
        description.setText(descriptionStr);
        txt.setText(txtStr);

        setUpBackBtn(R.id.backbtn, ReadActivity.class, this);
    }

    private void setUpBackBtn(int back, Class c, Activity a) {
        AppCompatImageButton btn = (AppCompatImageButton) findViewById(back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityWithMenu.initiate(c, a);
            }
        });
    }
}
