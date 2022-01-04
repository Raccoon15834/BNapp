package das.anusha.bnapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

public class FlashcardDeck extends Fragment {
    int img, layId, num;
    String title;
    int[] imgs;
    String[] strs;
    String[] tags;
    private FlashcardDeck.deckSelector myListener;
    public interface deckSelector{
        void onDeckSelect(int[] imgs, String[] strs);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.myListener = (FlashcardDeck.deckSelector) context;
    }
    public static FlashcardDeck newInstance(int[] imgs, String[] strs, String title, String[] tags) {
        Log.i("cardData", imgs[0]+"at newInstance");
        FlashcardDeck fragment = new FlashcardDeck();
        fragment.imgs = imgs;
        fragment.title = title;
        fragment.strs = strs;
        fragment.tags = tags;
        return fragment;
    }
    public void setNum(int n){
        num = n;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View myV =  inflater.inflate(R.layout.deckcover, container, false);
        //GridLayout.LayoutParams lp= new GridLayout.LayoutParams(GridLayout.spec(num/3), GridLayout.spec(num%3));
        GridLayout.LayoutParams lp= new GridLayout.LayoutParams(GridLayout.spec(num/3), GridLayout.spec(GridLayout.UNDEFINED,GridLayout.FILL,1f));
        lp.height = GridLayout.LayoutParams.WRAP_CONTENT;
        lp.width = 0;
        myV.setLayoutParams(lp);
        return  myV;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        LinearLayout ln = (LinearLayout) findViewById(R.id.gall1_1);
//        View myV = View.inflate(getApplicationContext(), R.layout.vidfrag, ln);
        TextView titleView = (TextView) view.findViewById(R.id.deckTitle);
        titleView.setText(title);
        ImageView imgView = (ImageView) view.findViewById(R.id.deckBg);
        //imgView.setImageResource(img);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("cardData", imgs[0]+"at onViewCreated");
                myListener.onDeckSelect(imgs, strs);
            }
        });
    }
    @Override
    public void onDetach() {
        super.onDetach();
        this.myListener = null;
    }
    public String[] getTags(){
        return tags;
    }
}
