package android.costi.bucketdrops.Adapter;

import android.content.Context;
import android.costi.bucketdrops.R;
import android.costi.bucketdrops.domain.Drop;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Costi on 17.02.2017.
 */

public class AdapterDrops extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM = 0;
    public static final int FOOTER = 1;

    private RealmResults<Drop> items;
    private LayoutInflater mLayoutInflater;

    @Override
    public int getItemViewType(int position) {
        if (items == null || position < items.size()) {
            return ITEM;
        } else return FOOTER;
    }

    public void update(RealmResults<Drop> results) {
        items = results;
        notifyDataSetChanged();
    }

    public AdapterDrops(Context context, RealmResults<Drop> results) {
        mLayoutInflater = LayoutInflater.from(context);
        update(results);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FOOTER) {
            View view = mLayoutInflater.inflate(R.layout.footer, parent, false);
            return new FooterHolder(view);
        } else {
            View view = mLayoutInflater.inflate(R.layout.drop_row, parent, false);
            return new DropHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DropHolder) {
            DropHolder dropHolder= (DropHolder) holder;
            dropHolder.textViewWhat.setText(items.get(position).getWhat());
        }
    }

    @Override
    public int getItemCount() {
        return items.size()+1;
    }

    public static class DropHolder extends RecyclerView.ViewHolder {

        TextView textViewWhat;

        public DropHolder(View itemView) {
            super(itemView);
            textViewWhat = (TextView) itemView.findViewById(R.id.tv_what);
        }
    }

    public static class FooterHolder extends RecyclerView.ViewHolder {

        Button btnAddFooter;

        public FooterHolder(View itemView) {
            super(itemView);
            btnAddFooter = (Button) itemView.findViewById(R.id.btn_footer);
        }
    }
}
