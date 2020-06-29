package com.manoj.gallery.view;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created By : Manoj DB on 29/6/20
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private int spacing;

    public GridItemDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = spacing;
        outRect.right = 0;
        outRect.bottom = spacing;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1 ||
                parent.getChildLayoutPosition(view) == 2 || parent.getChildLayoutPosition(view) == 3) {
            outRect.top = spacing;
        } else {
            outRect.top = 0;
        }
    }
}
