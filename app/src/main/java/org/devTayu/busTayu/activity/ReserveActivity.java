/*
package org.devTayu.busTayu.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.adapter.ReserveAdapter;
import org.devTayu.busTayu.model.Reserve;

import java.util.ArrayList;
import java.util.List;

public class ReserveActivity extends AppCompatActivity {

    private RecyclerView mPostRecyclerView;
    private ReserveAdapter mAdpater;
    private List<Reserve> mDatas;  //데이터를 넣은 리스트 변수

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reserve);
        Log.d("유소정", "ReserveActivity onCreate");

        mPostRecyclerView = findViewById(R.id.recyclerView_reserve);
        mDatas = new ArrayList<>(); // 샘플 데이터 추가

        mDatas.add(new Reserve("남은 시간", "버스번호", "정류소번호", "도착예정시간", "버스 정보"));
        mDatas.add(new Reserve("2 분 06초 남음", "6638", "17001", "2021.10.17 12:25", "버스 상태 : 여유"));

        // Adapter, LayoutManager 연결
        mAdpater = new ReserveAdapter(mDatas);
        mPostRecyclerView.setAdapter(mAdpater);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
*/
