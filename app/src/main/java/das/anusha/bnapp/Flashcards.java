package das.anusha.bnapp;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Guideline;

import org.w3c.dom.Text;

public class Flashcards extends Activity {
    int[] imgs;
    String[] strs;
    int cardNums;
    int currCard;
    ConstraintLayout scrn;
    ImageView currImg;
    TextView currDef;
    boolean flipped = true;
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

        scrn = (ConstraintLayout) findViewById(R.id.card);
        populateRandomCard();
        setUpClicker();
        setUpBackBtn(R.id.back, MemorizeActivity.class, this);

        //currImg.animationlistener(new Animation.AnimationListener())
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

    private void populateRandomCard() {
        TransitionSet fader = new TransitionSet().addTransition(new Fade());
        TransitionManager.beginDelayedTransition(scrn, fader);
        currDef.setVisibility(View.GONE);

        Animation fadeOut = new TranslateAnimation(0,0,0,5000);
        fadeOut.setDuration(300);
////        AnimationSet animation = new AnimationSet(false); //change to false
////        animation.addAnimation(fadeOut);
////        animation.addAnimation(fadeIn);-- for second one you want must set startDelay
        currImg.startAnimation(fadeOut);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                currCard = (int)(Math.random()*cardNums);
                currImg.setImageResource(imgs[currCard]);
                currDef.setText(strs[currCard]);

                ConstraintSet set = new ConstraintSet();
                set.clone(scrn);
                set.connect(currImg.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
                set.connect(currImg.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                set.applyTo(scrn);

                Animation fadeIn = new TranslateAnimation(0,0, 5000,0);
                fadeIn.setDuration(300);

                 currImg.startAnimation(fadeIn);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //-----------TESTING
    }

    private void setUpClicker() {
        scrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flipped){
                    TransitionSet revealflip = new TransitionSet().addTransition(new ChangeBounds()).addTransition(new Fade());
                    TransitionManager.beginDelayedTransition(scrn, revealflip);

                    ConstraintSet set = new ConstraintSet();
                    set.clone(scrn);
                    set.connect(currImg.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
                    set.connect(currImg.getId(), ConstraintSet.TOP, R.id.halfway, ConstraintSet.BOTTOM);
                    set.applyTo(scrn);

                    currDef.setVisibility(View.VISIBLE);
                }
                else populateRandomCard();

                flipped = !flipped;
            }
        });
    }
}
