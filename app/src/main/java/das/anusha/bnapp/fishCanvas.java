package das.anusha.bnapp;

import static android.view.MotionEvent.INVALID_POINTER_ID;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

public class fishCanvas extends View {
    Fish mFish;
    //VectorDrawableCompat lvlmap, lvls;
    VectorDrawableCompat[] lvlmaps;
    Resources res;
    int numOfLvls = 20;//must be multiple of 6?
    int numOfMaps;
    //int[][] lvlXYloc;
    Fish[] lvls;
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

        numOfMaps = numOfLvls/6;
        lvlmaps = new VectorDrawableCompat[numOfMaps];
        for(int v=0; v< numOfMaps; v++){
            lvlmaps[v]= VectorDrawableCompat.create(res, R.drawable.ic_lvlmap, null);
            lvlmaps[v].setBounds(0+v*w, h/4, w+v*w, 5*h/8);

        }

        int heightavg = h/4;
        //STATIC ORIGINAL POSITIONS
        int[][] OGset= new int[][]{{20,heightavg},{20,heightavg},{50,heightavg},{80,heightavg},{110,heightavg},{140,heightavg}};
        //lvlXYloc = new int[numOfLvls][2];
        lvls = new Fish[numOfLvls];
        int indx = 0;
        for(Fish i: lvls){
            //initialize "fishes"
            i = new Fish(res, OGset[indx][0], OGset[indx][1]);
            indx++;
            indx = indx%6;
        }

        //TODO add "level" map

    }

    private float xmove = 0;
    private float xset = 0;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(xset+xmove, 0);
        for(VectorDrawableCompat v: lvlmaps){
            v.draw(canvas);
        }
        invalidate();
    }
    private float lastTouchX=0;
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x, y;
        if(action==MotionEvent.ACTION_DOWN){
            lastTouchX = event.getX();
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

    private void lvlMapoffSetBounds(float x) {
        Log.i("read","setx"+xset);
        if(-xset-x<0){
            x=0;
            xset += xmove;
        }
        if (-xset-x>w*(numOfMaps-1)){
            x=0;
            xset += xmove;
        }
        //TODO fix
        xmove = x;
    }
}
