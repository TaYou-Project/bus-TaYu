package org.devTayu.busTayu.holder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.activity.StationActivity;
import org.devTayu.busTayu.adapter.SearchAdapter;

public class SearchHolder extends RecyclerView.ViewHolder {

    // ViewHolder 에 필요한 데이터들을 적음.
    public TextView stationName;
    public TextView stationNumber;
    public TextView stationWay;
    public TextView xCode;
    public TextView yCode;

    Intent searchIntent;

    // 아이템 뷰를 저장하는 클래스
    public SearchHolder(@NonNull View itemView, final SearchAdapter.OnItemClickEventListener searchClickListener) {
        super(itemView);
        // 아이템 뷰에 필요한 View
        stationName = itemView.findViewById(R.id.search_stationName);
        stationNumber = itemView.findViewById(R.id.search_stationNumber);
        stationWay = itemView.findViewById(R.id.search_stationWay);
        xCode = itemView.findViewById(R.id.search_xCode);
        yCode = itemView.findViewById(R.id.search_yCode);

        // itemView : click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = getAdapterPosition();
                Context context = v.getContext();

                if (position != RecyclerView.NO_POSITION) {
                    searchClickListener.onItemClick(position);
                    Toast.makeText(context, position + " : itemView", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // itemView : longClick
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("Recyclerview", "position = " + getAdapterPosition());
                return false;
            }
        });

        // search_btn_more : 더보기 : click
        itemView.findViewById(R.id.search_btn_more).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final int position = getAdapterPosition();
                Context context = v.getContext();
                Toast.makeText(context, position + " : 더보기", Toast.LENGTH_SHORT).show();

                // busNumber 가져오기
                String station_num = stationNumber.getText().toString();
                String station_name = stationName.getText().toString();

                // StationActivity로 station_num : 정류소 번호 전달, station_name : 정류소 명 전달
                searchIntent = new Intent(context, StationActivity.class);
                searchIntent.putExtra("station_num", station_num);
                searchIntent.putExtra("station_name", station_name);
                context.startActivity(searchIntent);

            }
        });
    }
}
