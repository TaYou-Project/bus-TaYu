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

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.activity.StationActivity;
import org.devTayu.busTayu.adapter.StationAdapter;
import org.devTayu.busTayu.model.Station;
import org.devTayu.busTayu.adapter.StationAdapter.OnItemClickEventListener;

public class StationHolder extends RecyclerView.ViewHolder {

    // 아이템 뷰를 저장하는 클래스

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

        // station_likeBtn : 즐겨찾기 : click
        itemView.findViewById(R.id.station_likeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = getAdapterPosition();
                Context context = v.getContext();
                Toast.makeText(context, position +" : 즐겨찾기", Toast.LENGTH_SHORT).show();
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
