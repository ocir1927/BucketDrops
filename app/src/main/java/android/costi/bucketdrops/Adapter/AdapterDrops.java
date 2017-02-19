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
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Costi on 17.02.2017.
 */

public class AdapterDrops extends RecyclerView.Adapter<AdapterDrops.DropHolder> {
    private RealmResults<Drop> items;

    private LayoutInflater mLayoutInflater;

/*    private void initItems(){
        items=new ArrayList<>();
        for(int i=1;i<=100;i++){
            items.add("Obiectul "+i);
        }
    }*/

    public void update(RealmResults<Drop> results){
        items=results;
        notifyDataSetChanged();
    }

    public AdapterDrops(Context context,RealmResults<Drop> results) {
        mLayoutInflater=LayoutInflater.from(context);
        update(results);
//        items=results;
//        initItems();
    }

    @Override
    public DropHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(R.layout.drop_row,parent,false);
        DropHolder dropHolder=new DropHolder(view);
        return dropHolder;
    }

    @Override
    public void onBindViewHolder(DropHolder holder, int position) {
        holder.textViewWhat.setText(items.get(position).getWhat());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class DropHolder extends RecyclerView.ViewHolder{

        TextView textViewWhat;
        public DropHolder(View itemView) {
            super(itemView);
            textViewWhat= (TextView) itemView.findViewById(R.id.tv_what);
        }
    }
}
