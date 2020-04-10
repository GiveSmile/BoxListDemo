package com.example.boxlistdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StackBoxAdapter extends ArrayAdapter {
    private List<Integer> mData;

    private Context context;


    public StackBoxAdapter(Context context) {
        super(context, R.layout.crib_item_left);
        this.context = context;
    }

    public void setmData(List<Integer> mData) {
        this.mData = mData;
    }

    public StackBoxAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crib_item_left, parent, false);
        //String name = mData.get(position);
        // ((TextView) view.findViewById(R.id.name)).setText(name);
        View completeView = view.findViewById(R.id.completeCommand);

        if (mData.get(position) == 1) {
            setLeftIcon(view, position, 0);
        } else {
            setLeftIcon(view, position, 3);
        }

        completeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onParticularsClickListener != null) {
                    onParticularsClickListener.onClikListener(position);
                }
            }
        });
        return view;
    }


    /**
     * 设置分类按钮向左移动动画
     *
     * @param img
     */
    private void setLeftIcon(View img, int i, int size) {
        int marginTop = i * 10;
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(img.getLayoutParams());
        margin.leftMargin = DensityUtil.dip2px(context, size * 2);
        margin.topMargin = DensityUtil.dip2px(context, marginTop);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        img.setLayoutParams(layoutParams);
    }


    public interface onParticularsClickListener {
        void onClikListener(int postion);
    }

    private onParticularsClickListener onParticularsClickListener;

    public void setVideoBackCallBack(onParticularsClickListener onParticularsClickListener) {
        this.onParticularsClickListener = onParticularsClickListener;
    }

}
