package org.devTayu.busTayu.holder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.activity.StationActivity;
import org.devTayu.busTayu.adapter.StationAdapter.OnItemClickEventListener;
import org.devTayu.busTayu.database.TaYuDatabase;
import org.devTayu.busTayu.model.LikedDB;

public class StationHolder extends RecyclerView.ViewHolder {

    // ViewHolder 에 필요한 데이터들을 적음.
    public TextView rtNm;
    public TextView adirection;
    public TextView arrmsgSec1;
    public TextView arrmsgSec2;
    public TextView stationNum;

    TaYuDatabase likedDatabase;
    LikedDB likedDB;
    StationActivity stationActivity;

    public StationHolder(@NonNull View itemView, final OnItemClickEventListener stationClickListener) {
        super(itemView);
        // 아이템 뷰에 필요한 View
        rtNm = itemView.findViewById(R.id.station_rtNm);
        adirection = itemView.findViewById(R.id.station_adirection);
        arrmsgSec1 = itemView.findViewById(R.id.station_arrmsgSec1);
        arrmsgSec2 = itemView.findViewById(R.id.station_arrmsgSec2);

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
                Context context = v.getContext();

                if (position != RecyclerView.NO_POSITION) {
                    stationClickListener.onItemClick(position);
                    Toast.makeText(context, position + " : itemView", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, position + " : 즐겨찾기", Toast.LENGTH_SHORT).show();

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
                            likedDatabase = TaYuDatabase.getDatabase(context);

                            // busNumber (버스번호) 가져오기
                            String busNumber = rtNm.getText().toString();
                            // stationNum (정류소번호) 가져오기
                            String stationNumber = stationNum.getText().toString();

                            Integer likedExist = likedDatabase.likedDAO().getCountLiked(busNumber, stationNumber);
                            // 즐겨찾기에 DELETE
                            if (likedExist > 0) {
                                likedDB = new LikedDB(busNumber, stationNumber);
                                likedDatabase.likedDAO().deleteLiked(busNumber, stationNumber);
                                Log.d("StationHolder : ", "DELETE liked_table!");
                            }
                            // 즐겨찾기에 INSERT
                            else {
                                likedDB = new LikedDB(busNumber, stationNumber);
                                likedDatabase.likedDAO().insertLiked(likedDB);
                                Log.d("StationHolder : ", "INSERT liked_table!");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        /*
                        디비에 작업하고 toast 보여주거나,
                        recyclerview 자체를 api에서 받아오고 해당 데이터 리스트들을 sql을 돌려서 이미 있는 것들은 효과를 따로 주거나?
                        그냥 냅두거나

                        stationActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });*/
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
                Toast.makeText(context, position + " : 첫 번째 버스", Toast.LENGTH_SHORT).show();
            }
        });

        // station_arrmsgSec2Btn : 두 번째 버스 : click
        itemView.findViewById(R.id.station_arrmsgSec2Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = getAdapterPosition();
                Context context = v.getContext();
                Toast.makeText(context, position + " : 두 번째 버스", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
