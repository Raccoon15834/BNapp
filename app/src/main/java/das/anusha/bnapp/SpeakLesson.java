package das.anusha.bnapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.viewpager2.widget.ViewPager2;

import java.util.LinkedList;

public class SpeakLesson extends Activity {
    AppCompatImageButton back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        int tmp = b.getInt("layout");
       // int tmp = getIntent().getIntExtra("layout", 0);
        Log.i("speakdebug",tmp+"-layout" );
        setContentView(tmp);
        //Different set content views depending on which lesson clicked

        back = (AppCompatImageButton) findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent starter = new Intent(getApplicationContext(), SpeakActivity.class);
                startActivity(starter);
            }
        });
        Resources res = getResources();
        String p = getPackageName();
        String lessNum = res.getResourceName(tmp);
        lessNum = lessNum.substring(lessNum.length()-1);
        int i=1;
        while(res.getIdentifier("animboxes"+lessNum+i, "array", p)!=0) {
            //HOW TO GET STRINGS
            TypedArray vids = res.obtainTypedArray(res.getIdentifier("animboxes"+lessNum+i, "array", p));

            GridView gridView = (GridView)findViewById(res.getIdentifier("vid"+lessNum+"."+i,"id",p));
            MyBaseAdapter baseAdapter = new MyBaseAdapter(this, vids);
            gridView.setAdapter(baseAdapter);
            i++;
        }

    }

    private class MyBaseAdapter extends BaseAdapter {
        Context c;
        TypedArray items;

        MyBaseAdapter(Context c, TypedArray arr)
        {
            this.c = c;
            items = arr;
        }
        @Override
        public int getCount() {
            return items.length();
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
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.vidfrag, null);

            AppCompatImageButton btn1 = (AppCompatImageButton) view.findViewById(R.id.btnplay);
            String vidPath1 = "android.resource://" + getPackageName() + "/"+R.raw.bari;
            Log.i("spkdebug", vidPath1);
            Uri uri = Uri.parse(vidPath1);
            VideoView mv1 = (VideoView) view.findViewById(R.id.vid);

            mv1.setVideoURI(uri);
            //mv1.setVideoURI(Uri.parse("android.resource://das.anusha.bnapp/"+R.raw.bari));  VIDPATH REPLACE items.getResourceId(i,0)
            btn1.setZ(10);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btn1.setVisibility(View.INVISIBLE);
                    mv1.start();
                }
            });
            mv1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    btn1.setVisibility(View.VISIBLE);
                    mv1.resume();
                }
            });
            return view;
        }
    }
}
