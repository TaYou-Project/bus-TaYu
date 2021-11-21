package org.devTayu.busTayu.ui.reserved;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.database.TaYuDatabase;
import org.devTayu.busTayu.model.Reserved;
import org.devTayu.busTayu.model.ReservedDB;
import org.devTayu.busTayu.ui.station.ReservedAPI;

import java.util.ArrayList;
import java.util.List;

public class ReservedFragment extends Fragment {

    private List<Reserved> mDatas;  //데이터를 넣은 리스트 변수

    private TaYuDatabase db;
    private ReservedAPI reservedAPI;
    private ReservedDB reservedDB;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reserved, container, false);

        // 데이터베이스 생성
        db = Room.databaseBuilder(getContext(), TaYuDatabase.class, "TaYu_database").build();
        // UI 갱신 (라이브데이터 Observer 이용, 해당 디비값이 변화가생기면 실행됨)
        db.reservedDAO().getAll().observe(getViewLifecycleOwner(), new Observer<List<ReservedDB>>() {
            @Override
            public void onChanged(List<ReservedDB> dataList) {

                // state 상태보고 가져올 것 - 최신 0번의 값을 가져오는데 상태가 state : R 인경우만 가져오게 함


                if (dataList.size() > 0) {
                    callAPI(dataList.get(0).getStationNumber(), dataList.get(0).getBusNumber());
                } else {
                    // 예약 : 없어도 에러만 나지 않도록 처리해둔 상태
                    // 예약 없는 경우 처리 "예약된 버스가 없습니다"

                    // 즐겨찾기 탭도 즐찾 없는 경우 즐찾 없다고 표시 할 것 "즐겨찾기 를 눌러 자주 사용하는 정류소`버스 추가하세요"
                    // 검색 탭에서 검색 결과 없으면 처리 "검색 결과가 없습니다"
                }

            }
        });

        return root;
    }

    public void callAPI(String station_num, String bus_num) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reservedAPI = new ReservedAPI();
                    mDatas = new ArrayList<>();
                    try {
                        mDatas = reservedAPI.reserve_arsId(station_num, bus_num);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread((new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // UI 관련
                            initUI();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }));
            }
        }).start();
    }

    @SuppressLint("SetTextI18n")
    public void initUI() {

        TextView leastTime = getView().findViewById(R.id.reserved_leastTime);
        TextView stationName = getView().findViewById(R.id.reserved_stationName);
        TextView stationNum = getView().findViewById(R.id.reserved_stationNum);
        TextView busNum = getView().findViewById(R.id.reserved_busNum);
        TextView adirection = getView().findViewById(R.id.reserved_adirection);
        // TextView busInfo = getView().findViewById(R.id.reserved_busInfo);
        Button btn = getView().findViewById(R.id.reserved_btn);

        leastTime.setText(mDatas.get(0).getArrmsgSec1());
        stationName.setText(mDatas.get(0).getStNm());
        stationNum.setText(mDatas.get(0).getArsId());
        busNum.setText(mDatas.get(0).getRtNm());
        adirection.setText(mDatas.get(0).getAdirection());
        // busInfo.setText(mDatas.get(0).getArrmsgSec1());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);

                            String Rbus = busNum.getText().toString();
                            String Rstation = stationNum.getText().toString();

                            builder.setTitle("예약 취소").
                                    setMessage(
                                            Html.fromHtml("정류소 : " + "<b>" + stationName.getText().toString() + "</b>" + "<br>" +
                                                            "버스 : " + "<b>" + busNum.getText().toString() + "</b>" + "<br>" +
                                                            "방면 : " + "<b>" + adirection.getText().toString() + "</b>" + "<br>" +
                                                            "남은시간 : " + "<b>" + leastTime.getText().toString() + "</b>" + "<br>" + "<br>" +
                                                            "<b>예약을 취소하시겠습니까?</b>"
                                                    , Html.FROM_HTML_MODE_LEGACY)
                                    );

                            builder.setPositiveButton("뒤로가기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(context.getApplicationContext(), "뒤로가기", Toast.LENGTH_SHORT).show();
                                }
                            });

                            builder.setNeutralButton("예약 취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(context.getApplicationContext(), "예약이 취소 되었습니다.", Toast.LENGTH_SHORT).show();

                                    // state 칼럼 : R(예약하면 디폴트로 들어감), D(예약취소-사용자), Y(탑승-버스기사), N(미탑승-버스기사), Z(기사님이 잊었거나, 기타 다른 이유)
                                    reservedDB = new ReservedDB(Rbus, Rstation, "z");
                                    db.reservedDAO().updateReserved(Rbus, Rstation);
                                    Log.d("StationHolder : ", "UPDATED reserved_table!");

                                }
                            });

                            Looper.prepare();
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            Looper.loop();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
    }
}