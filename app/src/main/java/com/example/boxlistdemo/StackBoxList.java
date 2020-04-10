package com.example.boxlistdemo;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StackBoxList extends FrameLayout {

    private ArrayAdapter adapter;

    private View[] views;

    private int itemSize;

    private DataSetObserver dataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            attachChildViews();
        }
    };




    public StackBoxList(@NonNull Context context) {
        super(context,null);
    }

    public StackBoxList(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public StackBoxList(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(ArrayAdapter adapter, int itemSize) {
        if (adapter == null) {
            throw new IllegalArgumentException("adapter not null");
        }

        if (this.adapter != null) {
            this.adapter.unregisterDataSetObserver(dataSetObserver);
        }

        this.itemSize = itemSize;

        this.adapter = adapter;

        this.adapter.registerDataSetObserver(dataSetObserver);

        views = new View[itemSize];

        attachChildViews();
    }

    private void attachChildViews() {
        removeAllViews();
        for (int position = 0; position < itemSize; position++) {
            views[position] = adapter.getView(position, views[position], this);
            addViewInLayout(views[position], 0, views[position].getLayoutParams());
        }
        requestLayout();
    }


}
