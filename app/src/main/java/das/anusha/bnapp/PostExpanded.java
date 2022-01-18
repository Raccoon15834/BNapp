package das.anusha.bnapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;

public class PostExpanded extends Activity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullpost);

        Bundle b = getIntent().getExtras();
        TextView fptitle = (TextView)findViewById(R.id.fptitle);
        fptitle.setText(b.getString("ptitle"));
        TextView fpname = (TextView)findViewById(R.id.fpname);
        fpname.setText(b.getString("pname"));

        AppCompatImageButton btn = (AppCompatImageButton) findViewById(R.id.back);
        Activity a = this;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityWithMenu.initiate(MainActivity.class, a);
            }
        });
    }
}
