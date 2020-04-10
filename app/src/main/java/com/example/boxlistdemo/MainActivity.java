package com.example.boxlistdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StackBoxList mStackBox;

    private List<Integer> mData = new ArrayList<>();

    private StackBoxAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStackBox = findViewById(R.id.mStackBox);
        for (int i = 0; i < 5; i++) {
            mData.add(0);
        }
        mAdapter = new StackBoxAdapter(this);
        mAdapter.setmData(mData);
        mStackBox.setAdapter(mAdapter, mData.size());
        mAdapter.setVideoBackCallBack(new StackBoxAdapter.onParticularsClickListener() {
            @Override
            public void onClikListener(int postion) {
                for (int i = 0; i < mData.size(); i++) {
                    if (postion == i && mData.get(i) != 1) {
                        mData.set(i, 1);
                    } else {
                        mData.set(i, i+6);
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
