package android.costi.bucketdrops;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Costi on 20.02.2017.
 */

public class BucketRecyclerView extends RecyclerView {
    private List<View> emptyViews = Collections.emptyList();

    private List<View> nonEmptyViews = Collections.emptyList();


    private AdapterDataObserver mObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            toggleViews();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            toggleViews();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            toggleViews();
        }
    };

    private void toggleViews() {
        if (getAdapter() != null && !emptyViews.isEmpty() && !nonEmptyViews.isEmpty()) {
            System.out.println(getAdapter().getItemCount());
            if (getAdapter().getItemCount() <= 1) {

                //recycler view should be not visible
                setVisibility(View.GONE);

                //show views that are meant to be shown when no items in list
                for (View w : emptyViews) {
                    w.setVisibility(View.VISIBLE);
                }

                //hide views that are meant to be hide when no items in list
                for (View w : nonEmptyViews) {
                    w.setVisibility(View.GONE);
                }


            } else {

                setVisibility(View.VISIBLE);

                for (View w : emptyViews) {
                    w.setVisibility(View.GONE);
                }

                for (View w : nonEmptyViews) {
                    w.setVisibility(View.VISIBLE);
                }

            }
        }
    }

    public BucketRecyclerView(Context context) {
        super(context);
    }

    public BucketRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BucketRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(mObserver);
        }
        mObserver.onChanged();
    }

    public void hideIfEmpty(View... views) {
        nonEmptyViews = Arrays.asList(views);
    }

    public void showIfEmpty(View... views) {
        emptyViews = Arrays.asList(views);
    }
}
