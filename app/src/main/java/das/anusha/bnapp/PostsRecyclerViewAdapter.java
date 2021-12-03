package das.anusha.bnapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.LinkedList;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private int numViews;
    private ArrayList<DataSnapshot> mDS;
    public PostsRecyclerViewAdapter(Context c) {
        context = c;
        numViews = 0;
    }
    public PostsRecyclerViewAdapter(Context c, DataSnapshot ds){
        context = c;
        mDS = new ArrayList<DataSnapshot>();
        for(DataSnapshot d: ds.child("/AllPosts").getChildren())mDS.add(d);
        numViews = mDS.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.postfrag, parent, false);
        mViewHolder vH = new mViewHolder(v); //creates a view with my fragment layout
        return vH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((mViewHolder) holder).setData((PostData) mDS.get(position).getValue()); // gives holder with position data
    }

    @Override
    public int getItemCount() {
        return numViews;
    }

    public static class mViewHolder extends RecyclerView.ViewHolder {

        public PostData fragData;
        public TextView c, t, u;
        public mViewHolder(View itemView) {
            super(itemView); //gets the views of the fragment
            t = (TextView) itemView.findViewById(R.id.titlePost);
            u = (TextView) itemView.findViewById(R.id.userPost);
        }
        public void setData(PostData pd){
            fragData = pd;
            u.setText(pd.getName());
            t.setText(pd.getTitle());
        }
    }

}