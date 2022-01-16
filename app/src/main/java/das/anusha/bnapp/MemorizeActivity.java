package das.anusha.bnapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.LinkedList;
import java.util.List;

public class MemorizeActivity extends Activity implements FlashcardDeck.deckSelector, NavigationBarView.OnItemSelectedListener{
    int num;
    AppCompatImageButton search;
    EditText searchBox;
    LinkedList<FlashcardDeck> decks;
    FragmentManager man;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorize);

        num =0;
        Resources res = getResources();
        String p = getPackageName();
        decks = new LinkedList<FlashcardDeck>();
        int i=1;
        //obtaining all the data from arrays
        String title; String[] tags; int[] pics; String[] strs;
        while(res.getIdentifier("title"+i, "string", p)!=0){
            //HOW TO GET STRINGS
            title=res.getString(res.getIdentifier("title"+i, "string", p));
            tags =res.getStringArray(res.getIdentifier("tags"+i, "array", p));
            TypedArray prePics =res.obtainTypedArray(res.getIdentifier("pics"+i, "array", p));
            pics = new int[prePics.length()];
            for(int j=0; j<pics.length; j++) pics[j] = prePics.getResourceId(j, 0);
            strs =res.getStringArray(res.getIdentifier("deck"+i, "array", p));
            FlashcardDeck myFrag = FlashcardDeck.newInstance(pics, strs, title, tags);
            decks.add(myFrag);
            i+=1;
            Log.i("cardData", pics[0]+"at retrieving resources Memorize");
        }
        addTheseDecks(decks);
        //set up navigation bar
        search = (AppCompatImageButton) findViewById(R.id.flashcardSearch);
        searchBox = (EditText) findViewById(R.id.decksSearchBox);
        man = getFragmentManager();
        setUpSearchFunction(search, searchBox);
        //navbar
        BottomNavigationView nav =  (BottomNavigationView)findViewById(R.id.bottom_navigatin_view);
        nav.setSelectedItemId(R.id.memorize);
        nav.setOnItemSelectedListener(this);
        nav.setItemIconTintList(null);
    }

    private void setUpSearchFunction(AppCompatImageButton btn, EditText box) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipVisibilities();
            }
        });
        box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String txt) {
        LinkedList<FlashcardDeck> newDecksList = new LinkedList<FlashcardDeck>();;
//        decks.add(myFrag);
//        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.add(R.id.deckFrags, myFrag, title).commit();
        for(FlashcardDeck fr: decks){
            for(String tag: fr.tags){
                if (tag.contains(txt)){
                    newDecksList.add(fr);
                    break;
                }
            }
        }
        addTheseDecks(newDecksList);
    }
    private void addTheseDecks(LinkedList<FlashcardDeck> decks){
        int num=0;
        for(Fragment myFrag: decks) {
            ((FlashcardDeck)myFrag).setNum(num);
            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.deckFrags, myFrag).commit();
            num++;
        }
    }

    private void flipVisibilities() {
        if(searchBox.getVisibility()== View.GONE){
            searchBox.setVisibility(View.VISIBLE);
            //search.setImageDrawable(getResources().getDrawable(R.drawable.check));
        }
        else{
            searchBox.setVisibility(View.GONE);
            //search.setImageDrawable(getResources().getDrawable(R.drawable.sch));
            filter("");
        }
    }
    public void clear(){
        for (Fragment fragment : man.getFragments()) {
            man.beginTransaction().remove(fragment).commit();
        }
    }



    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return ActivityWithMenu.setOptionsSelected(this, item);
    }

    @Override
    public void onDeckSelect(int[] imgs, String[] strs) {
        Intent starter = new Intent(getApplicationContext(), Flashcards.class);
        Bundle extras = new Bundle();
        extras.putIntArray("imgs", imgs);
        extras.putStringArray("strs", strs);
        starter.putExtras(extras);
        startActivity(starter);
    }
}
