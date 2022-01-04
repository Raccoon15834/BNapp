package das.anusha.bnapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

public class SpeakLesson extends Activity {
    AppCompatButton back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int tmp = getIntent().getIntExtra("layout", 0);
        Log.i("speakdebug",tmp+"-layout" );
        setContentView(tmp);
        //Different set content views depending on which lesson clicked

//        back = (AppCompatButton) findViewById();
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent starter = new Intent(getApplicationContext(), SpeakActivity.class);
//                startActivity(starter);
//            }
//        });
        if(tmp ==R.layout.less1) addVids1();

    }

    private void addVids1() {
//        LinearLayout ln = (LinearLayout) findViewById(R.id.gall1_1);
//        View myV = View.inflate(getApplicationContext(), R.layout.vidfrag, ln);
//        VideoView mv1 = (VideoView) myV.findViewById(R.id.vid1);
//        VideoView mv2 = (VideoView) myV.findViewById(R.id.vid2);
//        AppCompatImageButton btn1 = (AppCompatImageButton) myV.findViewById(R.id.btn1);
//        AppCompatImageButton btn2 = (AppCompatImageButton) myV.findViewById(R.id.btn2);
//        //get the video and set to view
//        String vidPath1 = "android.resource://" + getPackageName() + "/"+R.raw.bari;
//        String vidPath2 = "android.resource://" + getPackageName() + "/"+R.raw.bhari;
//        inflateVid(mv1, vidPath1, btn1);
//        inflateVid(mv2, vidPath2, btn2);
    }

    private void inflateVid(VideoView mv1, String vidPath1, AppCompatImageButton btn1) {
        Uri uri = Uri.parse(vidPath1);
        mv1.setVideoURI(uri);
        //btn1.setZ(10);
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
        mv1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) { //TODO fix audio
                //mv1.start();
                mp.setVolume(10, 10);
            }
        });
    }
}
