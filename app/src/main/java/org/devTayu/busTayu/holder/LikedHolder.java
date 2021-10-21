package org.devTayu.busTayu.holder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.adapter.LikedAdapter;

public class LikedHolder extends RecyclerView.ViewHolder {

    // ViewHolder 에 필요한 데이터들을 적음.
    public TextView stationName;
    public TextView stationNum;
    public TextView adirection;
    public TextView rtNm;
    public TextView arrmsgSec1;
    public TextView arrmsgSec2;

    // 아이템 뷰를 저장하는 클래스
    public LikedHolder(@NonNull View itemView, final LikedAdapter.OnItemClickEventListener likedClickListener) {
        super(itemView);
        // 아이템 뷰에 필요한 View
        // 아이템 뷰에 필요한 View
        stationName = itemView.findViewById(R.id.liked_stationName);
        stationNum = itemView.findViewById(R.id.liked_stationNum);
        adirection = itemView.findViewById(R.id.liked_adirection);
        rtNm = itemView.findViewById(R.id.liked_rtNm);
        arrmsgSec1 = itemView.findViewById(R.id.liked_arrmsgSec1);
        arrmsgSec2 = itemView.findViewById(R.id.liked_arrmsgSec2);

        // itemView : click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = getAdapterPosition();
                Context context = v.getContext();

                if (position != RecyclerView.NO_POSITION) {
                    likedClickListener.onItemClick(position);
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
