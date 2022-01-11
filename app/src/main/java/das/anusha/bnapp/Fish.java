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
    int x, y;
    public Fish(Resources res, int x, int y){
        this.res = res;
        Drawable d= res.getDrawable(R.drawable.ic_levelmarker);
        self = drawableToBitmap(d);
        h = self.getHeight();
        w = self.getHeight();
        this.x = x;
        this.y = y;
    }

    public void draw(Canvas canvas){
        //can call offset(x, y) at an time resetting fishes position --- here or at track right

        Rect src = new Rect(x, y, x+w, y+h); // Frame of bitmap
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