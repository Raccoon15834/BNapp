package das.anusha.bnapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class fishCanvas extends View {
    Fish mFish;
    int w, h;
    public fishCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mFish = new Fish(getResources());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        w = getWidth();
        h = getHeight();
        //TODO add "rivers" with reading lvls
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mFish.draw(canvas);

        invalidate();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
//
//        double MIN_DISTANCE, x1, x2;
//        MIN_DISTANCE = 40;
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                x1 = event.getX();
//                break;
//            case MotionEvent.ACTION_UP:
//                x2 = event.getX();
//                double deltaX = x2 - x1;
//                if (deltaX > MIN_DISTANCE) {
//                    //left to right
//                } else if(deltaX<-MIN_DISTANCE){
//                    //right to left
//                }
//                break;
//        }
//    }


}
