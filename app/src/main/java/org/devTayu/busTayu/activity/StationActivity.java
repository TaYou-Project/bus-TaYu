package org.devTayu.busTayu.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

    StationAPI stationAPI;
    ArrayList<Station> mDatas;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_station);

        // "이전" 버튼 == MainActivity로 이동 : 지금은 Main 강제 이동이나, 나중에 되면 "이전" 페이지로 돌아가도록 변경해야 함
        ImageButton backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        TextView textView = (TextView) findViewById(R.id.station_name);
        // 정류소_명 [정류소 번호]
        textView.setText(station_name + " [" + station_num + " ]");

        /* Android Honeycomb 이후 MainThread 에서 networking 처리 불가 */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 데이터 추가
                    stationAPI = new StationAPI();
                    mDatas = new ArrayList<>();
                    try {
                        mDatas = stationAPI.station_arsId();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d("유소정", "recyclerView: " + mDatas.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            recyclerView();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();

        // 쓸어서 새로고침 : swipeRefreshLayout
        SwipeRefreshLayout mSwipeRefreshLayout = findViewById(R.id.station_SwipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(StationActivity.this, "새로고침1", Toast.LENGTH_SHORT).show();
                        mSwipeRefreshLayout.setRefreshing(false); // false : 새로고침 중지
                    }
                }, 500);

            }
        });

        // 플로팅 새로고침 : floatingActionButton
        FloatingActionButton fab = findViewById(R.id.station_floatingbtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StationActivity.this, "새로고침2", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // recyclerView
    public void recyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        RecyclerView mPostRecyclerView = findViewById(R.id.recyclerView_station);

        // 레이아웃에서 박스 처리해서 필요 없어짐
        /* default 선 추가 : Divider 추가 */
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        //mPostRecyclerView.addItemDecoration(dividerItemDecoration);


        // 아이템 간 간격 추가
        OffsetItemDecoration itemDecoration = new OffsetItemDecoration(20, 20);
        mPostRecyclerView.addItemDecoration(itemDecoration);

         /*
        Log.d("유소정", "recyclerView: "+mDatas.size());
        for (int i=0; i< mDatas.size(); i++){
            mDatas.add(new Station("title1", "contents"));
        }
        */

        // Adapter, LayoutManager(LinerLayoutManager) 연결
        StationAdapter mAdpater = new StationAdapter(mDatas);
        mPostRecyclerView.setAdapter(mAdpater);
        mPostRecyclerView.setLayoutManager(linearLayoutManager);
    }

    // 아이템 간 간격 정의
    // ItemDecoration : RecyclerView 내부에 있는 abstract class 로 item 간 구분선이나 여백을 설정할 수 있다
    public static class OffsetItemDecoration extends RecyclerView.ItemDecoration {
        private final int mPadding, mPadding2;

        public OffsetItemDecoration(int a_padding, int b_padding) {
            mPadding = a_padding;
            mPadding2 = b_padding;
        }

        // 각 항목을 배치 시 호출
        @Override
        public void getItemOffsets(@NotNull Rect a_outRect, @NotNull View a_view, @NotNull RecyclerView a_parent, @NotNull RecyclerView.State a_state) {
            super.getItemOffsets(a_outRect, a_view, a_parent, a_state);
            //a_outRect.top = mPadding;
            a_outRect.bottom = mPadding;
            a_outRect.left = mPadding2;
            a_outRect.right = mPadding2;
        }
    }

}
