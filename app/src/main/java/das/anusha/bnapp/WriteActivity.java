package das.anusha.bnapp;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
//COMBINE recycler view - https://www.geeksforgeeks.org/staggered-gridview-in-android-with-example/
//WITH firebase database - https://github.com/dltra/FirebaseChatRoom/commit/82d7895b9d7ecaffb4419c3b866c0d2d6eb3b573
//look at other chat tutorials
//https://code.tutsplus.com/tutorials/how-to-create-an-android-chat-app-using-firebase--cms-27397
//https://medium.com/@meetpatel12121995/realtime-chat-using-firebase-database-b65ee23f3b6a
//https://www.raywenderlich.com/22067733-firebase-tutorial-real-time-chat
public class WriteActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
    }
}
