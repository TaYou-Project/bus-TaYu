package org.devTayu.busTayu.holder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.activity.BusActivity;
import org.devTayu.busTayu.adapter.StationAdapter.OnItemClickEventListener;
import org.devTayu.busTayu.database.TaYuDatabase;
import org.devTayu.busTayu.model.LikedDB;
import org.devTayu.busTayu.model.ReservedDB;

public class StationHolder extends RecyclerView.ViewHolder {

    // ViewHolder 에 필요한 데이터들을 적음.
    public TextView rtNm;
    public TextView adirection;
    public TextView arrmsgSec1;
    public TextView arrmsgSec2;
    public TextView stationNum;
    public TextView stNm;

    private TaYuDatabase taYuDatabase;
    private LikedDB likedDB;
    private ReservedDB reservedDB;

    Intent stationIntent;

    public StationHolder(@NonNull View itemView, final OnItemClickEventListener stationClickListener) {
        super(itemView);

        // 아이템 뷰에 필요한 View
        rtNm = itemView.findViewById(R.id.station_rtNm);
        adirection = itemView.findViewById(R.id.station_adirection);
        arrmsgSec1 = itemView.findViewById(R.id.station_arrmsgSec1);
        arrmsgSec2 = itemView.findViewById(R.id.station_arrmsgSec2);

        // Alertdialog에 정류소 명 넣으려고 사용 : 숨김 처리, width/height 0 처리
        stNm = itemView.findViewById(R.id.station_stationName);
        stNm.setVisibility(View.GONE);
        stNm.setWidth(0);
        stNm.setHeight(0);

        // 디비에 정류소 번호 넣으려고 사용 : 숨김 처리, width/height 0 처리
        stationNum = itemView.findViewById(R.id.station_stationNum);
        stationNum.setVisibility(View.GONE);
        stationNum.setWidth(0);
        stationNum.setHeight(0);

        // itemView : click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = getAdapterPosition();
                // Context context = v.getContext();
                if (position != RecyclerView.NO_POSITION) {
                    stationClickListener.onItemClick(position);
                    Log.d("StationHolder Recyclerview", position + " : itemView");
                    // Toast.makeText(context, position + " : itemView", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // itemView : longClick
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("StationHolder Recyclerview", "position = " + getAdapterPosition());
                return false;
            }
        });

        // 파이어베이스 때문에 사용 중 아래로 두 줄
        //Station station = new Station();
        //DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Liked");

        // station_likeBtn : 즐겨찾기 : click
        itemView.findViewById(R.id.station_likeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = getAdapterPosition();
                Context context = v.getContext();
                //Toast.makeText(context, position + " : 즐겨찾기", Toast.LENGTH_SHORT).show();

                // 파이어베이스
                // station.setRtNm("setRtNm");
                /*
                reff.push().setValue(station); 이렇게 넣을 수 있는데 이렇게 그냥 넣으면 난수 값 아래에 데이터가 들어감
                사실 사용자 고유번호 설정하지 말고 그럼 그냥 위에 방식처럼 child 정하지 않는걸로 해서 난수 값 아래에 데이터 들어가게 하는것도 괜찮음
                대신 나중에 어떤 사용자의 데이터를 수정하고 싶을 때 문제가 발생
                liked > 사용자 고유번호 > 즐겨찾기 설정한 버스 번호(노선 번호), 정류소 번호
                나중에 노선 번호,정류소 번호로 api 돌려서 해당 버스가 정류장에 몇분 뒤에 오는지 알아낼 예정
                */
                //reff.child("liked1").setValue(station);
                //Toast.makeText(context,"Liked Data Inserted Sucessfully",Toast.LENGTH_SHORT).show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // SQLite
                            taYuDatabase = TaYuDatabase.getDatabase(context);

                            // busNumber (버스번호) 가져오기
                            String busNumber = rtNm.getText().toString();
                            // stationNum (정류소번호) 가져오기
                            String stationNumber = stationNum.getText().toString();
                            // stationName (정류소 명) 가져오기
                            String stationName = stNm.getText().toString();

                            Integer likedExist = taYuDatabase.likedDAO().getCountLiked(busNumber, stationNumber);
                            // 즐겨찾기에 DELETE
                            if (likedExist > 0) {
                                likedDB = new LikedDB(busNumber, stationNumber);
                                taYuDatabase.likedDAO().deleteLiked(busNumber, stationNumber);
                                Log.d("StationHolder : ", "DELETE liked_table!");

                                Looper.prepare();
                                Toast.makeText(context.getApplicationContext(), "즐겨찾기 해제.", Toast.LENGTH_SHORT).show();
                                Looper.loop();

                            }
                            // 즐겨찾기에 INSERT
                            else {
                                likedDB = new LikedDB(busNumber, stationNumber, stationName);
                                taYuDatabase.likedDAO().insertLiked(likedDB);
                                Log.d("StationHolder : ", "INSERT liked_table!");

                                Looper.prepare();
                                Toast.makeText(context.getApplicationContext(), "즐겨찾기 등록.", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        // station_arrmsgSec1Btn : 첫 번째 버스 : click
        itemView.findViewById(R.id.station_arrmsgSec1Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = getAdapterPosition();
                Context context = v.getContext();
                // Toast.makeText(context, position + " : 첫 번째 버스", Toast.LENGTH_SHORT).show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // SQLite
                            taYuDatabase = TaYuDatabase.getDatabase(context);

                            // 도착예정시간 : 현재시간 + 남은시간 추가
                            String Rbus = rtNm.getText().toString();
                            String Rstation = stationNum.getText().toString();
                            String RAdirection = adirection.getText().toString();
                            String RarrmsgSec1 = arrmsgSec1.getText().toString();

                            // 예약된 버스
                            Integer reservedBusExist = taYuDatabase.reservedDAO().getCountReservedBus(Rbus, Rstation);
                            // 예약 존재 여부
                            Integer reservedExist = taYuDatabase.reservedDAO().getCountReserved();
                            Log.d("유소정 디비", reservedExist.toString());

                            // 예약된 버스가 있는지 확인
                            if (reservedExist > 0) {
                                // 해당 버스가 이미 예약된 버스
                                if (reservedBusExist > 0) {
                                    Looper.prepare();
                                    Toast.makeText(context.getApplicationContext(), "이미 예약된 버스 입니다. 예약 탭에서 확인해 주세요", Toast.LENGTH_LONG).show();
                                    Looper.loop();
                                } else {
                                    Looper.prepare();
                                    Toast.makeText(context.getApplicationContext(), "예약된 다른 버스가 있습니다. 한번에 하나의 예약만 가능합니다", Toast.LENGTH_LONG).show();
                                    Looper.loop();
                                }
                            }
                            // 예약 시도
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                                builder.setTitle("버스 예약").setMessage(
                                        Html.fromHtml("정류소 : " + "<b>" + stNm.getText().toString() + "</b>" + "<br>" +
                                                        "버스 : " + "<b>" + rtNm.getText().toString() + "</b>" + "<br>" +
                                                        "방면 : " + "<b>" + adirection.getText().toString() + "</b>" + "<br>" +
                                                        "남은시간 : " + "<b>" + arrmsgSec1.getText().toString() + "</b>" + "<br>" + "<br>" +
                                                        "<b>예약하시겠습니까?</b>"
                                                , Html.FROM_HTML_MODE_LEGACY)
                                );

                                builder.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(context.getApplicationContext(), "취소 되었습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                /*
                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int id)
                                    {
                                        Toast.makeText(context.getApplicationContext(), "Cancel Click", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                */
                                builder.setNeutralButton("예약", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(context.getApplicationContext(), "예약 되었습니다.", Toast.LENGTH_SHORT).show();

                                        // state 칼럼 : R(예약하면 디폴트로 들어감), D(예약취소-사용자), Y(탑승-버스기사), N(미탑승-버스기사), Z(기사님이 잊었거나, 기타 다른 이유)
                                        reservedDB = new ReservedDB(Rbus, Rstation, "R");
                                        taYuDatabase.reservedDAO().insert(reservedDB);
                                        Log.d("StationHolder : ", "INSERT reserved_table!");
                                    }
                                });
                                Looper.prepare();
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                Looper.loop();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        /*
                        ((StationActivity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        */
                    }

                }).start();
            }
        });

        // station_arrmsgSec2Btn : 두 번째 버스 : click
        itemView.findViewById(R.id.station_arrmsgSec2Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = getAdapterPosition();
                Context context = v.getContext();
                // Toast.makeText(context, position + " : 두 번째 버스", Toast.LENGTH_SHORT).show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // SQLite
                            taYuDatabase = TaYuDatabase.getDatabase(context);

                            // 도착예정시간 : 현재시간 + 남은시간 추가
                            String Rbus = rtNm.getText().toString();
                            String Rstation = stationNum.getText().toString();
                            String RAdirection = adirection.getText().toString();
                            String RarrmsgSec1 = arrmsgSec1.getText().toString();


                            // 예약된 버스
                            Integer reservedBusExist = taYuDatabase.reservedDAO().getCountReservedBus(Rbus, Rstation);
                            // 예약 존재 여부
                            Integer reservedExist = taYuDatabase.reservedDAO().getCountReserved();
                            Log.d("유소정 디비", reservedExist.toString());

                            // 예약된 버스가 있는지 확인
                            if (reservedExist > 0) {
                                // 해당 버스가 이미 예약된 버스
                                if (reservedBusExist > 0) {
                                    Looper.prepare();
                                    Toast.makeText(context.getApplicationContext(), "이미 예약된 버스 입니다. 예약 탭에서 확인해 주세요", Toast.LENGTH_LONG).show();
                                    Looper.loop();
                                } else {
                                    Looper.prepare();
                                    Toast.makeText(context.getApplicationContext(), "예약된 다른 버스가 있습니다. 한번에 하나의 예약만 가능합니다", Toast.LENGTH_LONG).show();
                                    Looper.loop();
                                }
                            }
                            // 예약 시도
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                                builder.setTitle("버스 예약").setMessage(
                                        Html.fromHtml("정류소 : " + "<b>" + stNm.getText().toString() + "</b>" + "<br>" +
                                                        "버스 : " + "<b>" + rtNm.getText().toString() + "</b>" + "<br>" +
                                                        "방면 : " + "<b>" + adirection.getText().toString() + "</b>" + "<br>" +
                                                        "남은시간 : " + "<b>" + arrmsgSec2.getText().toString() + "</b>" + "<br>" + "<br>" +
                                                        "<b>예약하시겠습니까?</b>"
                                                , Html.FROM_HTML_MODE_LEGACY)
                                );

                                builder.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(context.getApplicationContext(), "취소 되었습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                builder.setNeutralButton("예약", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(context.getApplicationContext(), "예약 되었습니다.", Toast.LENGTH_SHORT).show();

                                        // state 칼럼 : R(예약하면 디폴트로 들어감), D(예약취소-사용자), Y(탑승-버스기사), N(미탑승-버스기사), Z(기사님이 잊었거나, 기타 다른 이유)
                                        reservedDB = new ReservedDB(Rbus, Rstation, "R");
                                        taYuDatabase.reservedDAO().insert(reservedDB);
                                        Log.d("StationHolder : ", "INSERT reserved_table!");
                                    }
                                });
                                Looper.prepare();
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                Looper.loop();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }).start();
            }
        });

        // 상세보기 버튼 : busActivity 이동
        itemView.findViewById(R.id.station_moreBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = getAdapterPosition();
                Context context = v.getContext();

                String station_num = stationNum.getText().toString();
                String bus_name = rtNm.getText().toString();
                String station_name = stNm.getText().toString();

                // BusActivity로  station_num : 정류소 번호 전달, bus_name : 버스 명 전달, station_name : 정류소 명 전달
                stationIntent = new Intent(context, BusActivity.class);
                stationIntent.putExtra("station_num", station_num);
                stationIntent.putExtra("bus_name", bus_name);
                stationIntent.putExtra("station_name", station_name);
                context.startActivity(stationIntent);

            }
        });
    }
}
