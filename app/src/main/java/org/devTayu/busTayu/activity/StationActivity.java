package org.devTayu.busTayu.activity;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.adapter.StationAdapter;
import org.devTayu.busTayu.model.Station;
import org.devTayu.busTayu.ui.station.LikedAPI;
import org.devTayu.busTayu.ui.station.StationAPI;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StationActivity extends AppCompatActivity {

    ArrayList<Station> mDatas;
    private StationAPI stationAPI;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);

        // 뒤로가기 버튼
        ImageButton backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 현 StationActivity 종료
                finish();
            }
        });

        /*
        Intent 가져올 때 null 체크 하려고 했는데 잘 안됨 : 일단 null 이어도 실행에 문제는 없음 : 나중에 확인
        String station_num = getIntent().getStringExtra("station_num").equals("")?"17001":getIntent().getStringExtra("station_num");
        */

        // SearchHolder 에서 넘어온 station_num : 정류소 번호
        String station_num = getIntent().getStringExtra("station_num");
        // SearchHolder 에서 넘어온 station_name : 정류소 명
        String station_name = getIntent().getStringExtra("station_name");
        // 정류소_명 [정류소 번호]
        TextView textView = (TextView) findViewById(R.id.station_name);
        textView.setText(station_name + " [ " + station_num + " ]");

        Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                bindList(station_num);
                Log.d("유소정", "핸들러로 bindList 호출");
            }
        };
        Handler mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 1000);

        /* Android Honeycomb 이후 MainThread 에서 networking 처리 불가 */
        // 5초에 한번씩 호출
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        Thread.sleep(5000);

                        // 데이터 추가
                        stationAPI = new StationAPI();
                        mDatas = new ArrayList<>();
                        try {
                            mDatas = stationAPI.station_arsId(station_num);
                            LikedAPI likedAPI = new LikedAPI();
                            likedAPI.liked_arsId("01004", "G8110성남");
                            Log.d("유소정", "StationActivity thread 쓰레드 실행");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.d("유소정", "StationActivity : recyclerView: " + mDatas.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                recyclerView();
                                Log.d("유소정", "StationActivity runOnUiThread 쓰레드 실행");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }).start();
        */

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

                        bindList(station_num);
                        Log.d("유소정 bindList ", "스크롤로 bindList 호출");

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

    // bindList(정류소 번호)
    private void bindList(String station_num) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 데이터 추가
                    stationAPI = new StationAPI();
                    mDatas = new ArrayList<>();
                    try {
                        mDatas = stationAPI.station_arsId(station_num);

                        LikedAPI likedAPI = new LikedAPI();
                        likedAPI.liked_arsId("01004", "G8110성남");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d("유소정 bindList ", "bindList 함수 : 쓰레드 실행");

                    Log.d("유소정 bindList ", "StationActivity : recyclerView: " + mDatas.size());
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
    }

    // recyclerView
    private void recyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView mPostRecyclerView = findViewById(R.id.recyclerView_station);

        // 레이아웃에서 박스 처리해서 필요 없어짐
        /* default 선 추가 : Divider 추가 */
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        //mPostRecyclerView.addItemDecoration(dividerItemDecoration);

        // 아이템 간 간격 추가
        OffsetItemDecoration itemDecoration = new OffsetItemDecoration(20, 20);
        mPostRecyclerView.addItemDecoration(itemDecoration);

        Log.d("유소정", "recyclerView: " + mDatas.size());
        // 샘플 데이터
        for (int i = 0; i < 2; i++) {
            mDatas.add(new Station("샘플데이터", "샘플데이터", "샘플데이터", "샘플데이터", "샘플데이터", "샘플데이터"));
        }

        // Adapter, LayoutManager(LinerLayoutManager) 연결
        StationAdapter mAdpater = new StationAdapter(mDatas);
        mPostRecyclerView.setAdapter(mAdpater);
        mPostRecyclerView.setLayoutManager(linearLayoutManager);

    }

    // 아이템 간 간격 정의
    // ItemDecoration : RecyclerView 내부에 있는 abstract class 로 item 간 구분선이나 여백을 설정할 수 있다
    private static class OffsetItemDecoration extends RecyclerView.ItemDecoration {
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