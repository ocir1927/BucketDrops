package android.costi.bucketdrops.Adapter;

import android.content.Context;
import android.costi.bucketdrops.DialogAddDrop;
import android.costi.bucketdrops.R;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by Costi on 21.02.2017.
 */

public class Divider extends RecyclerView.ItemDecoration {
    public static final String TAG = "Costi";
    private Drawable mDivider;
    private int mOrientaion;

    public Divider(Context context, int orientation) {
        mDivider = ContextCompat.getDrawable(context, R.drawable.divider);
        if (orientation != LinearLayoutManager.VERTICAL) {
            throw new IllegalArgumentException("This Item decoration requires a vertical orientation");
        }
        mOrientaion = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientaion == LinearLayoutManager.VERTICAL) {
            drawHorizontalDivider(c, parent, state);
        }
    }

    private void drawHorizontalDivider(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left, top, right, bottom;
        left = 0;
        right = parent.getWidth();
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            if (parent.getAdapter().getItemViewType(i) == AdapterDrops.ITEM) {
                View current = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) current.getLayoutParams();
                top = current.getTop() - params.topMargin;
                bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
                Log.d(TAG, "drawHorizontalDivider: " + "l:" + left + " t:" + top + " r:" + right + " b" + bottom);
            }
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
