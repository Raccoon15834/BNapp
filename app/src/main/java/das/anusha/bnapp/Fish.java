package das.anusha.bnapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class Fish extends RectF {
    Resources res;
    Bitmap self;
    int h, w;
    public Fish(Resources res, int x, int y, int lvl){
        this.res = res;
        Drawable d= res.getDrawable(R.drawable.ic_levelmarker);
        self = drawableToBitmap(d);
        h = self.getHeight();
        w = self.getWidth();
        left = x;
        top = y;
        right = x+w;
        bottom = y+h;
    }

    public void draw(Canvas canvas){
        Rect src = new Rect(0,0,w,h); // Frame of bitmap
        canvas.drawBitmap(self, src, this, null);
    }
    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

}