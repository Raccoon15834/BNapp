package das.anusha.bnapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class fishCanvas extends View {
    Bitmap bmp;
    public fishCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bmp = Fish.drawableToBitmap(getResources().getDrawable(R.drawable.ic_letter));
    }
}
