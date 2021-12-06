package das.anusha.bnapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MemorizeActivity extends Activity implements FlashcardDeck.deckSelector{
    Menu mybar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorize);

        //TODO add all title, tags, pics, strs,
        Resources res = getResources();
        String p = getPackageCodePath();
        int i=1;
        String title=res.getString(res.getIdentifier("title"+i, null, p));
        String[] tags =res.getStringArray(res.getIdentifier("tags"+i, null, p);
        int[] pics =res.getIntArray(res.getIdentifier("imgs"+i, null, p));
        String[] strs =res.getStringArray(res.getIdentifier("strs"+i, null, p));
        while(title!=null){
            i+=1;
            addFragment(pics, strs, title, tags);
            //HOW TO GET STRINGS
            title=res.getString(res.getIdentifier("title"+i, null, p));
            tags =res.getStringArray(res.getIdentifier("tags"+i, null, p));
            pics =res.getIntArray(res.getIdentifier("imgs"+i, null, p));
            strs =res.getStringArray(res.getIdentifier("strs"+i, null, null));

        }

        ActivityWithMenu.setUpMenu(this, mybar);
    }

    private void addFragment(int[] pics, String[] strs, String title, String[] tags) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment myFrag = FlashcardDeck.newInstance(pics, strs, title, tags);
        ft.add(R.id.deckFrags, myFrag , title);
        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (!ActivityWithMenu.setOptionsSelected(this, item)) return super.onOptionsItemSelected(item);
        return true;
    }

    @Override
    public void onDeckSelect(int[] imgs, String[] strs) {
        Intent starter = new Intent(getApplicationContext(), Flashcards.class);
        Bundle extras = starter.getExtras();
        extras.putIntArray("imgs", imgs);
        extras.putStringArray("title", strs);
        startActivity(starter);
    }
}
