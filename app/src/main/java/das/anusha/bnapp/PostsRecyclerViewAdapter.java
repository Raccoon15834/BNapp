package das.anusha.bnapp;

import android.content.Context;
import android.util.Log;
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
import java.util.Iterator;
import java.util.LinkedList;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context context;
    ArrayList<DataSnapshot> mDS;
    public PostsRecyclerViewAdapter(Context c) {
        mDS = new ArrayList<DataSnapshot>();
        context = c;
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
        PostData pdtemp = mDS.get(position).getValue(PostData.class);
        ((mViewHolder) holder).setData(pdtemp); // gives holder with position data
    }

    @Override
    public int getItemCount() {
        return mDS.size();
    }

    public static class mViewHolder extends RecyclerView.ViewHolder {

        public PostData fragData;
        public TextView c, t, u;
        public mViewHolder(View itemView) {
            super(itemView); //gets the views of the fragment
            t = (TextView) itemView.findViewById(R.id.title);
            u = (TextView) itemView.findViewById(R.id.name);
        }
        public void setData(PostData pd){
            fragData = pd;
            u.setText(pd.getName());
            t.setText(pd.getTitle());
        }
    }

}