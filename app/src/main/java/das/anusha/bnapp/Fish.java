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
    public Fish(Resources res){
        this.res = res;
//        Drawable d= res.getDrawable(R.id.ic_fish);
//        self = drawableToBitmap(d);
//        h = self.getHeight();
//        w = self.getHeight();
    }

    public void trackRight(){

    }
    public void draw(Canvas canvas){
        //can call offset(x, y) at an time resetting fishes position --- here or at track right

        //Rect src = new Rect() // Frame of bitmap
        //canvas.drawBitmap(self, src, this, null);
    }
    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

}