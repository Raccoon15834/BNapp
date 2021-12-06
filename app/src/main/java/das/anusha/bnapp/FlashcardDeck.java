package das.anusha.bnapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

public class FlashcardDeck extends Fragment {
    int img, layId;
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

        FlashcardDeck fragment = new FlashcardDeck();
        fragment.imgs = imgs;
        fragment.title = title;
        fragment.strs = strs;
        fragment.tags = tags;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.deckcover, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView titleView = (TextView) view.findViewById(R.id.lessonTitle);
        titleView.setText(title);
        ImageView imgView = (ImageView) view.findViewById(R.id.lessonImage);
        imgView.setImageResource(img);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener.onDeckSelect(imgs, strs);
            }
        });
    }
    @Override
    public void onDetach() {
        super.onDetach();
        this.myListener = null;
    }
}
