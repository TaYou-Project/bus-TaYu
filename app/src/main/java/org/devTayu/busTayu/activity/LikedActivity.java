package org.devTayu.busTayu.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.adapter.LikedAdapter;
import org.devTayu.busTayu.model.Liked;

import java.util.ArrayList;
import java.util.List;

public class LikedActivity extends AppCompatActivity {

    private RecyclerView mPostRecyclerView;
    private LikedAdapter mAdpater;
    private List<Liked> mDatas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_liked);

        Log.d("유소정", "LikeActivity onCreate");

        mPostRecyclerView = findViewById(R.id.recyclerView_liked);
        mDatas = new ArrayList<>(); // 샘플 데이터 추가
        mDatas.add(new Liked("정류장명", "정류장번호", "방면", "버스", "도착정보1","도착정보2"));
        mDatas.add(new Liked("정류장명", "정류장번호", "방면", "버스", "도착정보1","도착정보2"));
        mDatas.add(new Liked("정류장명", "정류장번호", "방면", "버스", "도착정보1","도착정보2"));
        mDatas.add(new Liked("정류장명", "정류장번호", "방면", "버스", "도착정보1","도착정보2"));
        mDatas.add(new Liked("정류장명", "정류장번호", "방면", "버스", "도착정보1","도착정보2"));
        mDatas.add(new Liked("정류장명", "정류장번호", "방면", "버스", "도착정보1","도착정보2"));
        mDatas.add(new Liked("정류장명", "정류장번호", "방면", "버스", "도착정보1","도착정보2"));
        mDatas.add(new Liked("정류장명", "정류장번호", "방면", "버스", "도착정보1","도착정보2"));

        // Adapter, LayoutManager 연결
        mAdpater = new LikedAdapter(mDatas);
        mPostRecyclerView.setAdapter(mAdpater);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
