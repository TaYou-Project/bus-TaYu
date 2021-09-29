package org.devTayu.busTayu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.MainActivity;
import org.devTayu.busTayu.R;
import org.devTayu.busTayu.adapter.SearchAdapter;
import org.devTayu.busTayu.model.Search;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView mPostRecyclerView;
    private SearchAdapter mAdpater;
    private List<Search> mDatas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);

        mPostRecyclerView = findViewById(R.id.recyclerView_search);
        mDatas = new ArrayList<>(); // 샘플 데이터 추가
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));
        mDatas.add(new Search("search","contents","time",20,10));

        // Adapter, LayoutManager 연결
        mAdpater = new SearchAdapter(mDatas);
        mPostRecyclerView.setAdapter(mAdpater);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
