package org.devTayu.busTayu.holder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.adapter.ReserveAdapter;

public class ReserveHolder extends RecyclerView.ViewHolder {

    // ViewHolder 에 필요한 데이터들을 적음.
    public TextView leastTime;
    public TextView stationName;
    public TextView stationNum;
    public TextView busNumber;
    public TextView adirection;
    public TextView busInfo;
    // public TextView expectationTime;

    // 아이템 뷰를 저장하는 클래스
    public ReserveHolder(@NonNull View itemView, final ReserveAdapter.OnItemClickEventListener reserveClickListener) {
        super(itemView);
        // 아이템 뷰에 필요한 View
        leastTime = itemView.findViewById(R.id.reserved_leastTime);
        stationName = itemView.findViewById(R.id.reserved_stationName);
        stationNum = itemView.findViewById(R.id.reserved_stationNum);
        busNumber = itemView.findViewById(R.id.reserved_busNum);
        adirection = itemView.findViewById(R.id.reserved_adirection);
        busInfo = itemView.findViewById(R.id.reserved_busInfo);
        // expectationTime = itemView.findViewById(R.id.reserved_expectationTime);

        // itemView : click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = getAdapterPosition();
                Context context = v.getContext();

                if (position != RecyclerView.NO_POSITION) {
                    reserveClickListener.onItemClick(position);
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

    }
}
