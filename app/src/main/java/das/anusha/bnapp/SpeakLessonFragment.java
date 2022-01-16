package das.anusha.bnapp;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SpeakLessonFragment extends Fragment {
    int layId;
    String title;
    private setSelector myListener;
    public interface setSelector{
        void onSetSelect(int layout);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.myListener = (setSelector) context;
    }
    public static SpeakLessonFragment newInstance(String title, int layId) {
        SpeakLessonFragment fragment = new SpeakLessonFragment();
        fragment.title = title;
        fragment.layId = layId;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.speakfrag, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView titleView = (TextView) view.findViewById(R.id.lessonTitle);
        titleView.setText(title);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener.onSetSelect(layId);
            }
        });
    }
    @Override
    public void onDetach() {
        super.onDetach();
        this.myListener = null;
    }
}
