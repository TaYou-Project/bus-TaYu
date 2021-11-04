package org.devTayu.busTayu.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
                // 현 BusActivity 종료
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

                        bindList(station_num, bus_name);
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

                bindList(station_num, bus_name);
                Log.d("유소정 bindList ", "플로팅으로 bindList 호출");
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

        // 첫 번째 버스 예약
        Button data1Btn = findViewById(R.id.bus_data1Btn);
        data1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(BusActivity.this, "첫 번째 버스 예약", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(BusActivity.this);
                // 도착예정시간 : 현재시간 + 남은시간 추가
                builder.setTitle("버스 예약").setMessage(
                        Html.fromHtml("정류소 : " + "<b>" + mDatas.get(0).getStNm() + "</b>" + "<br>" +
                                        "버스 : " + "<b>" + mDatas.get(0).getRtNm() + "</b>" + "<br>" +
                                        "방면 : " + "<b>" + mDatas.get(0).getAdirection() + "</b>" + "<br>" +
                                        "남은시간 : " + "<b>" + mDatas.get(0).getArrmsgSec1() + "</b>" + "<br>" + "<br>" +
                                        "<b>예약하시겠습니까?</b>"
                                , Html.FROM_HTML_MODE_LEGACY)
                );

                builder.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(BusActivity.this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNeutralButton("예약", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(BusActivity.this, "예약 되었습니다.", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        // 두 번째 버스 예약
        Button data2Btn = findViewById(R.id.bus_data2Btn);
        data2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(BusActivity.this, "두 번째 버스 예약", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(BusActivity.this);
                // 도착예정시간 : 현재시간 + 남은시간 추가
                builder.setTitle("버스 예약").setMessage(
                        Html.fromHtml("정류소 : " + "<b>" + mDatas.get(0).getStNm() + "</b>" + "<br>" +
                                        "버스 : " + "<b>" + mDatas.get(0).getRtNm() + "</b>" + "<br>" +
                                        "방면 : " + "<b>" + mDatas.get(0).getAdirection() + "</b>" + "<br>" +
                                        "남은시간 : " + "<b>" + mDatas.get(0).getArrmsgSec2() + "</b>" + "<br>" + "<br>" +
                                        "<b>예약하시겠습니까?</b>"
                                , Html.FROM_HTML_MODE_LEGACY)
                );

                builder.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(BusActivity.this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNeutralButton("예약", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(BusActivity.this, "예약 되었습니다.", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void init() {

        /*버스 : if 운행종료, 일반버스, 폐지 than button.setEnabled */
        /* "곧 도착" 일경우 예약 가능하게 할지 말지 */
        Button data1Btn = findViewById(R.id.bus_data1Btn);
        if (mDatas.get(0).getArrmsgSec1().equals("첫 번째 버스 운행종료")) {
            data1Btn.setEnabled(false);
        } else if (mDatas.get(0).getBusType1().equals("일반버스")) {
            data1Btn.setEnabled(false);
        } else if (mDatas.get(0).getRoutType().equals("폐지 노선")) {
            data1Btn.setEnabled(false);
        }
        Button data2Btn = findViewById(R.id.bus_data2Btn);
        if (mDatas.get(0).getArrmsgSec2().equals("두 번째 버스 운행종료")) {
            data2Btn.setEnabled(false);
        } else if (mDatas.get(0).getBusType2().equals("일반버스")) {
            data2Btn.setEnabled(false);
        } else if (mDatas.get(0).getRoutType().equals("폐지 노선")) {
            data2Btn.setEnabled(false);
        }

        /*
        버스 공통 데이터
        방향
        첫차시간
        막차시간
        다음 정류장
        노선유형
        구간명
        배차간격
        */
        TextView textView = findViewById(R.id.bus_dataCommon);
        String busData =
                "<Strong>" + "[ 버스 정보 ]" + "</Strong>" + "<br><br>" +
                        "버스 방면 : " + mDatas.get(0).getAdirection() + "<br>" +
                        "첫차 시간 : " + mDatas.get(0).getFirstTm() + "<br>" +
                        "막차 시간 : " + mDatas.get(0).getLastTm() + "<br>" +
                        "다음 정류장 : " + mDatas.get(0).getNxtStn() + "<br>" +
                        "노선 유형 : " + mDatas.get(0).getRoutType() + "<br>" +
                        "구간 명 : " + mDatas.get(0).getSectNm() + "<br>" +
                        "배차 간격 : " + mDatas.get(0).getTerm();
        textView.setText(Html.fromHtml(busData, Html.FROM_HTML_MODE_COMPACT));

        /*
        첫 번째 도착 버스 데이터
        버스정보
        버스타입
        막차여부
        */
        TextView textView1 = findViewById(R.id.bus_data1TV);
        String busData1 =
                mDatas.get(0).getArrmsgSec1() + "<br>" +
                        "<Strong>" + "버스 종류 (일반 / 저상) : " + mDatas.get(0).getBusType1() + "</Strong><br>" +
                        "막차 여부 (일반 / 막차) :  " + mDatas.get(0).getIsLast1();
        textView1.setText(Html.fromHtml(busData1, Html.FROM_HTML_MODE_COMPACT));

        /*
        두 번째 도착 버스 데이터
        버스정보
        버스타입
        막차여부
        */
        TextView textView2 = findViewById(R.id.bus_data2TV);
        String busData2 =
                mDatas.get(0).getArrmsgSec2() + "<br>" +
                        "<Strong>" + "버스 종류 (일반 / 저상) : " + mDatas.get(0).getBusType2() + "</Strong><br>" +
                        "막차 여부 (일반 / 막차) :  " + mDatas.get(0).getIsLast2();
        textView2.setText(Html.fromHtml(busData2, Html.FROM_HTML_MODE_COMPACT));

        /*for (int i = 0; i < mDatas.size(); i++) {
            System.out.println(mDatas.get(i).getArrmsgSec1());
            System.out.println(mDatas.get(i).getAdirection());
            System.out.println(mDatas.get(i).getArrmsgSec2());
            System.out.println(mDatas.get(i).getStNm());
            System.out.println(mDatas.get(i).getRtNm());
        }*/

        /*for (Liked x : mDatas) {
            System.out.print(x + ",");
        }*/

        /*
        Iterator<Liked> it = mDatas.iterator();//emps.get(index);와 같은 방법으로 요소를 가져올 수도 있으나, iterator를 사용했다.
        for (int i = 0; i < mDatas.size(); i++) {
            if (it.hasNext()) {//pc(프로그램카운터가 이동하여, 다음 요소가 있는지 확인)
                Liked e = it.next();//hasNext를 해서 다음요소가 있는지 확인해야지만, it.next해서 그 요소를 불러올 수가 있다.
                System.out.println(e.getArrmsgSec1() + e.getStNm());
            }
        }
        */
    }
}
