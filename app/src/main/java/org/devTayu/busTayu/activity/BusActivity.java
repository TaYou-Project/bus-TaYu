package org.devTayu.busTayu.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.model.Liked;
import org.devTayu.busTayu.ui.station.LikedAPI;

import java.util.ArrayList;

public class BusActivity extends AppCompatActivity {

    ArrayList<Liked> mDatas;
    private LikedAPI likedAPI;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        // 뒤로가기 버튼
        ImageButton backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 현 busActivity 종료
                finish();
            }
        });

        /*
        Intent 가져올 때 null 체크 하려고 했는데 잘 안됨 : 일단 null 이어도 실행에 문제는 없음 : 나중에 확인
        */
        // 00000 에서 넘어온 station_num : 정류소 번호
        String station_num = getIntent().getStringExtra("station_num");
        // SearchHolder 에서 넘어온 station_name : 정류소 명
        String station_name = getIntent().getStringExtra("station_name");
        // 00000 에서 넘어온 bus_name : 버스 명
        String bus_name = getIntent().getStringExtra("bus_name");

        // 버스_명
        TextView textView1 = (TextView) findViewById(R.id.bus_BusName);
        textView1.setText(bus_name);
        // 정류소_명 [정류소 번호]
        TextView textView2 = (TextView) findViewById(R.id.bus_StationName);
        textView2.setText(station_name + " [ " + station_num + " ]");

        Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                /* Android Honeycomb 이후 MainThread 에서 networking 처리 불가 */
                // bindList("15172", "양천04");
                bindList(station_num, bus_name);
                Log.d("유소정", "busActivity : 핸들러로 bindList 호출");
            }
        };
        Handler mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 1000);

        // 쓸어서 새로고침 : swipeRefreshLayout
        SwipeRefreshLayout mSwipeRefreshLayout = findViewById(R.id.bus_SwipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(BusActivity.this, "새로고침1", Toast.LENGTH_SHORT).show();

                        bindList("", "");
                        Log.d("유소정 bindList ", "스크롤로 bindList 호출");

                        mSwipeRefreshLayout.setRefreshing(false); // false : 새로고침 중지
                    }
                }, 500);
            }
        });

        // 플로팅 새로고침 : floatingActionButton
        FloatingActionButton fab = findViewById(R.id.bus_floatingbtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BusActivity.this, "새로고침2", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // bindList( 정류소번호 , 버스명)
    private void bindList(String station_num, String bus_num) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    likedAPI = new LikedAPI();
                    mDatas = new ArrayList<>();
                    try {
                        mDatas = likedAPI.liked_arsId(station_num, bus_num);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // UI 관련
                            init();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    private void init() {

    }
}
