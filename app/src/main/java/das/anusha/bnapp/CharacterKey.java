package das.anusha.bnapp;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
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
        int[] chars = getResources().getIntArray(R.array.charphotos);
        int[] sounds= getResources().getIntArray(R.array.charsounds);;

        GridView gridView = (GridView)findViewById(R.id.symbols);
        CharacterKey.MyCharAdapter baseAdapter = new CharacterKey.MyCharAdapter(this, chars, sounds);
        gridView.setAdapter(baseAdapter);
    }

    private class MyCharAdapter extends BaseAdapter {
        int[] chars, sounds;

        public MyCharAdapter(CharacterKey characterKey, int[] chars, int[] sounds) {
            this.chars = chars;
            this.sounds = sounds;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return chars.length;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.charview, null);
            ImageView charimg = (ImageView) findViewById(R.id.charview);
            charimg.setImageResource(chars[i]);
            MediaPlayer firstSound = MediaPlayer.create(CharacterKey.this, sounds[i]);

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
