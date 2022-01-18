package das.anusha.bnapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;

public class CharacterKey extends Activity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charkey);

        AppCompatImageButton btn = (AppCompatImageButton) findViewById(R.id.backbtn);
        Activity a = this;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityWithMenu.initiate(ReadActivity.class, a);
            }
        });
        TypedArray chars= getResources().obtainTypedArray(R.array.charphotos);
        TypedArray sounds= getResources().obtainTypedArray(R.array.charsounds);

        GridView gridView = (GridView)findViewById(R.id.symbols);
        CharacterKey.MyCharAdapter baseAdapter = new CharacterKey.MyCharAdapter(this, chars, sounds);
        gridView.setAdapter(baseAdapter);
        gridView.setZ(10);
    }

    private class MyCharAdapter extends BaseAdapter {
        TypedArray chars, sounds;

        public MyCharAdapter(CharacterKey characterKey, TypedArray chars, TypedArray sounds) {
            this.chars = chars;
            this.sounds = sounds;
        }

        @Override
        public int getCount() {
            return chars.length();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.charview, null);
            Log.i("charlog","here");
            ImageView charimg = (ImageView) view.findViewById(R.id.charviewphoto);
            charimg.setImageResource(chars.getResourceId(i, 0));
            MediaPlayer firstSound = MediaPlayer.create(CharacterKey.this, sounds.getResourceId(i, 0));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    firstSound.start();
                }
            });

            return view;
        }
    }
}
