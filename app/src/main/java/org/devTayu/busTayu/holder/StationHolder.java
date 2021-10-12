package org.devTayu.busTayu.holder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.activity.StationActivity;
import org.devTayu.busTayu.adapter.StationAdapter;
import org.devTayu.busTayu.model.Station;
import org.devTayu.busTayu.adapter.StationAdapter.OnItemClickEventListener;

public class StationHolder extends RecyclerView.ViewHolder {

    // ViewHolder 에 필요한 데이터들을 적음.
    public TextView rtNm;
    public TextView adirection;
    public TextView arrmsgSec1;
    public TextView arrmsgSec2;
    public Button station_likeBtn;

    public StationHolder(@NonNull View itemView, final OnItemClickEventListener a_itemClickListener) {
        super(itemView);
        // 아이템 뷰에 필요한 View
        rtNm = itemView.findViewById(R.id.station_rtNm);
        adirection = itemView.findViewById(R.id.station_adirection);
        arrmsgSec1 = itemView.findViewById(R.id.station_arrmsgSec1);
        arrmsgSec2 = itemView.findViewById(R.id.station_arrmsgSec2);

        // 아래로 click listener 설정
        // itemView : click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = getAdapterPosition();
                Context context = v.getContext();

                if (position != RecyclerView.NO_POSITION) {
                    a_itemClickListener.onItemClick(position);
                    Toast.makeText(context, position +" : itemView", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // itemView : longClick
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("Recyclerview", "position = "+ getAdapterPosition());
                return false;
            }
        });


        // 파이어베이스 때문에 사용 중 아래로 두 줄
        Station station = new Station();
        DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Liked");
        // station_likeBtn : 즐겨찾기 : click
        itemView.findViewById(R.id.station_likeBtn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final int position = getAdapterPosition();
                Context context = v.getContext();
                Toast.makeText(context, position +" : 즐겨찾기", Toast.LENGTH_SHORT).show();

                station.setRtNm("setRtNm");
                // reff.push().setValue(station); 이렇게 넣을 수 있는데 이렇게 그냥 넣으면 난수 값 아래에 데이터가 들어감
                // 사실 사용자 고유번호 설정하지 말고 그럼 그냥 위에 방식처럼 child 정하지 않는걸로 해서 난수 값 아래에 데이터 들어가게 하는것도 괜찮음
                // 대신 나중에 어떤 사용자의 데이터를 수정하고 싶을 때 문제가 발생
                // liked > 사용자 고유번호 > 즐겨찾기 설정한 버스 번호(노선 번호), 정류소 번호
                // 나중에 노선 번호,정류소 번호로 api 돌려서 해당 버스가 정류장에 몇분 뒤에 오는지 알아낼 예정
                reff.child("liked1").setValue(station);
                Toast.makeText(context,"Liked Data Inserted Sucessfully",Toast.LENGTH_SHORT).show();
            }
        });

        // station_arrmsgSec1Btn : 첫 번째 버스 : click
        itemView.findViewById(R.id.station_arrmsgSec1Btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final int position = getAdapterPosition();
                Context context = v.getContext();
                Toast.makeText(context,  position +" : 첫 번째 버스", Toast.LENGTH_SHORT).show();
            }
        });

        // station_arrmsgSec2Btn : 두 번째 버스 : click
        itemView.findViewById(R.id.station_arrmsgSec2Btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final int position = getAdapterPosition();
                Context context = v.getContext();
                Toast.makeText(context,  position +" : 두 번째 버스", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
