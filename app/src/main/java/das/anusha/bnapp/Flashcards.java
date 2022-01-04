package das.anusha.bnapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

public class Flashcards extends Activity {
    int[] imgs;
    String[] strs;
    int cardNums;
    int currCard;
    View scrn;
    ImageView currImg;
    TextView currDef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card);

        Bundle b = getIntent().getExtras();
        imgs = b.getIntArray("imgs");
        strs = b.getStringArray("strs");
        Log.i("cardData", imgs[0]+"at flashCardsONcreate");
        cardNums = imgs.length;
        Log.i("cardData", ""+cardNums);
        currImg = (ImageView) findViewById(R.id.cardImg);
        currDef = (TextView) findViewById(R.id.cardStr);

        scrn = findViewById(R.id.card);
        populateRandomCard();
        setUpClicker();
    }

    private void populateRandomCard() {
        currCard = (int)(Math.random()*cardNums);
        currImg.setImageResource(imgs[currCard]);
        Log.i("cardData", imgs[currCard]+"");
        currDef.setText(strs[currCard]);
    }

    private void setUpClicker() {
        scrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                populateRandomCard();
            }
        });
    }
}
