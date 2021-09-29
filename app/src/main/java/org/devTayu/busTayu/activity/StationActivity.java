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
import org.devTayu.busTayu.model.Station;
import org.devTayu.busTayu.adapter.StationAdapter;

import java.util.ArrayList;
import java.util.List;

public class StationActivity extends AppCompatActivity {

    private RecyclerView mPostRecyclerView;
    private StationAdapter mAdpater;
    private List<Station> mDatas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_station);

        // "이전" 버튼 == MainActivity로 이동 : 나중에 되면 이전 페이지로 돌아가도록 변경해야함
        Button backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        mPostRecyclerView = findViewById(R.id.recyclerView_station);
        mDatas = new ArrayList<>(); // 샘플 데이터 추가
        mDatas.add(new Station("title1","contents","time",20,10));
        mDatas.add(new Station("title2","contents","time",20,10));
        mDatas.add(new Station("title3","contents","time",20,10));
        mDatas.add(new Station("title4","contents","time",20,10));
        mDatas.add(new Station("title5","contents","time",20,10));

        // Adapter, LayoutManager 연결
        mAdpater = new StationAdapter(mDatas);
        mPostRecyclerView.setAdapter(mAdpater);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
