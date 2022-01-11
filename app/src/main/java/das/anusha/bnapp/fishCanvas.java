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
    int[][] lvlXYloc;
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
        lvlXYloc = new int[numOfLvls][2];
        for(int[] i: lvlXYloc){
            //initialize "fishes"
        }

        //TODO add "level" map

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(VectorDrawableCompat v: lvlmaps){
            v.draw(canvas);
        }

        invalidate();
    }
    private int mActivePointerId = INVALID_POINTER_ID;
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x, y;
        float lastTouchX=0;
        if(action==MotionEvent.ACTION_DOWN){
            final int pointerIndex = MotionEventCompat.getActionIndex(event);
            lastTouchX = MotionEventCompat.getX(event, pointerIndex);
            // Save the ID of this pointer (for dragging)
            mActivePointerId = MotionEventCompat.getPointerId(event, 0);
        }
        if(action==MotionEvent.ACTION_MOVE){
            final int pointerIndex =
                    MotionEventCompat.findPointerIndex(event, mActivePointerId);

            x = MotionEventCompat.getX(event, pointerIndex);
           // x = event.getX();
            //y = event.getY();
            lvlMapoffSetBounds(x-lastTouchX);
            Log.i("readdebug", "dragging "+(x-lastTouchX));
        }
        return true;
    }

    private void lvlMapoffSetBounds(float x) {
//        Rect farLeft = lvlmaps[0].getBounds();
//        if(farLeft.left<=0 && x<0) return;
//        Rect farRight = lvlmaps[numOfMaps-1].getBounds();
//        if(farLeft.left>=0 && x>0) return;
        for(VectorDrawableCompat v: lvlmaps){
            Rect mB = v.getBounds();
            mB.offset((int)x,0);
            v.setBounds(mB);
        }
    }
}
