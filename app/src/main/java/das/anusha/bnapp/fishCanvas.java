package das.anusha.bnapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
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

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }
}
