package das.anusha.bnapp;

import static android.view.MotionEvent.INVALID_POINTER_ID;
import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import java.util.ArrayList;

public class fishCanvas extends View {
    Fish mFish;
    VectorDrawableCompat[] lvlmaps;
    Resources res;
    int numOfLvls ;//must be multiple of 6?
    int numOfMaps;
    String p;
    Fish[] lvls;
    lvlSelector myListener;
    int w, h;

    public fishCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        w = getWidth();
        h = getHeight();
        res = getResources();
        this.myListener = (lvlSelector) getContext();

        int inv = h/16; //inteveral
        //STATIC ORIGINAL POSITIONS
        int[][] OGset= new int[][]{{w/12,6*inv},{3*w/12,4*inv},{5*w/12,6*inv},{7*w/12,9*inv},{9*w/12,7*inv},{11*w/12,7*inv}};
        lvls = new Fish[numOfLvls];
        numOfMaps = numOfLvls/6;
        lvlmaps = new VectorDrawableCompat[numOfMaps];
        for(int v=0; v< numOfMaps; v++){
            lvlmaps[v]= VectorDrawableCompat.create(res, R.drawable.ic_lvlmap, null);
            lvlmaps[v].setBounds(0+v*w, h/4, w+v*w, 5*h/8);

            for(int i=v*6;i<v*6+6;i++){
                lvls[i] = new Fish(res, v*w+OGset[i%6][0], OGset[i%6][1], v+i+1);
            }

        }

    }
    public interface lvlSelector{
        void onLvlSelect(int f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(xset+xmove, 0);
        for(VectorDrawableCompat v: lvlmaps){
            v.draw(canvas);
        }for(Fish f: lvls){
            f.draw(canvas);
        }
        invalidate();
    }
    private float xmove = 0;
    private float xset = 0;
    private float lastTouchX=0;
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x;
        if(action==MotionEvent.ACTION_DOWN){
            lastTouchX = event.getX();
            if(lvlClicked(xset+lastTouchX, event.getY())) return true;
        }
        if(action==MotionEvent.ACTION_MOVE){
            x = event.getX();
            lvlMapoffSetBounds(x-lastTouchX);;
        }
        if(action==MotionEvent.ACTION_UP){
            lastTouchX = 0;
            xset += xmove;
            xmove = 0;
        }
        return true;
    }

    private boolean lvlClicked(float x, float y) {
        for(int f=0; f<lvls.length; f++){
            if(lvls[f].contains(x, y)){
                myListener.onLvlSelect(f);
                return true;
            }
        }
        return false;
    }

    private void lvlMapoffSetBounds(float x) {
        if(-xset-x<0){
            x=0;
            xset += xmove;
        }
        if (-xset-x>w*(numOfMaps-1)){
            x=0;
            xset += xmove;
        }
        xmove = x;
    }

    public void setNumOfLvls(int num){
        numOfLvls = num;
    }

}
