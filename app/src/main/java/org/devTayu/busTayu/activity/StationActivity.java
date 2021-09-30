package org.devTayu.busTayu.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.MainActivity;
import org.devTayu.busTayu.R;
import org.devTayu.busTayu.model.Station;
import org.devTayu.busTayu.adapter.StationAdapter;
import org.devTayu.busTayu.model.StationAPI;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StationActivity extends AppCompatActivity {

    String station_name = "신도림역2번출구";
    String station_num = "17909";
    String RtNm = "";

    @SuppressLint("SetTextI18n")
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

        TextView textView = (TextView) findViewById(R.id.station_name);
        textView.setText(station_name + " [" + station_num + " ]");

        /* 쓰레드에서 돌려줘야 함 */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StationAPI stationAPI = new StationAPI();
                    stationAPI.station_arsId();
                    RtNm = stationAPI.getRtNm();
                    Log.d("유소정", "run: " + RtNm);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            recyclerView();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();

    }

    // recyclerView
    public void recyclerView(){
        RecyclerView mPostRecyclerView = findViewById(R.id.recyclerView_station);

        /* default 선 추가
        // Divider 추가
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        mPostRecyclerView.addItemDecoration(dividerItemDecoration);
        */

        // 아이템 간 간격 추가
        OffsetItemDecoration itemDecoration = new OffsetItemDecoration(20);
        mPostRecyclerView.addItemDecoration(itemDecoration);

        // 샘플 데이터 추가
        List<Station> mDatas = new ArrayList<>();
        mDatas.add(new Station("title1", "contents", "time", 20, 10));
        mDatas.add(new Station("title2", "contents", "time", 20, 10));
        mDatas.add(new Station("title3", "contents", "time", 20, 10));
        mDatas.add(new Station("title4", "contents", "time", 20, 10));
        mDatas.add(new Station("title5", "contents", "time", 20, 10));
        mDatas.add(new Station("title3", "contents", "time", 20, 10));
        mDatas.add(new Station("title4", "contents", "time", 20, 10));
        mDatas.add(new Station("title5", "contents", "time", 20, 10));

        // Adapter, LayoutManager(LinerLayoutManager) 연결
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        StationAdapter mAdpater = new StationAdapter(mDatas);
        mPostRecyclerView.setAdapter(mAdpater);
        mPostRecyclerView.setLayoutManager(linearLayoutManager);
    }

    // 아이템 간 간격 정의
    // ItemDecoration : RecyclerView 내부에 있는 abstract class 로 item 간 구분선이나 여백을 설정할 수 있다
    public static class OffsetItemDecoration extends RecyclerView.ItemDecoration {
        private final int mPadding;

        public OffsetItemDecoration(int a_padding) {
            mPadding = a_padding;
        }

        // 각 항목을 배치 시 호출
        @Override
        public void getItemOffsets(@NotNull Rect a_outRect, @NotNull View a_view, @NotNull RecyclerView a_parent, @NotNull RecyclerView.State a_state) {
            super.getItemOffsets(a_outRect, a_view, a_parent, a_state);
            //a_outRect.top = mPadding;
            a_outRect.bottom = mPadding;
            a_outRect.left = mPadding;
            a_outRect.right = mPadding;
        }
    }

}
