package org.devTayu.busTayu.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.adapter.LikedAdapter;
import org.devTayu.busTayu.database.LikedDatabase;
import org.devTayu.busTayu.model.Liked;
import org.devTayu.busTayu.model.LikedDB;

import java.util.ArrayList;
import java.util.List;

public class LikedActivity extends AppCompatActivity {

    private RecyclerView mPostRecyclerView;
    private LikedAdapter mAdpater;
    private List<Liked> mDatas;

    private LikedDatabase db;
    private TextView mResultTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_liked);

        Log.d("유소정", "LikeActivity onCreate");

        mResultTextView = (TextView) findViewById(R.id.textview11111);

        // 데이터베이스 생성
        db = Room.databaseBuilder(this, LikedDatabase.class, "liked_database").build();
        /*
        아래와 같은 것
        db.likedDAO().getAll().observe(this, dataList -> {
            mResultTextView.setText(dataList.toString());
        });
        */
        //UI 갱신 (라이브데이터 Observer 이용, 해당 디비값이 변화가생기면 실행됨)
        db.likedDAO().getAll().observe(this, new Observer<List<LikedDB>>() {
            @Override
            public void onChanged(List<LikedDB> dataList) {
                mResultTextView.setText(dataList.toString());
            }
        });
        //DB 데이터 불러오기 (SELECT)
        mResultTextView.setText(db.likedDAO().getAll().toString());

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
