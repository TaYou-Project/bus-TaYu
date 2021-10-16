package org.devTayu.busTayu.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.R;

public class SearchHolder extends RecyclerView.ViewHolder {

    // ViewHolder 에 필요한 데이터들을 적음.
    public TextView stationName;
    public TextView stationNumber;
    public TextView stationWay;
    public TextView xCode;
    public TextView yCode;

    // 아이템 뷰를 저장하는 클래스
    public SearchHolder(@NonNull View itemView) {
        super(itemView);
        // 아이템 뷰에 필요한 View
        stationName = itemView.findViewById(R.id.search_stationName);
        stationNumber = itemView.findViewById(R.id.search_stationNumber);
        stationWay = itemView.findViewById(R.id.search_stationWay);
        xCode = itemView.findViewById(R.id.search_xCode);
        yCode = itemView.findViewById(R.id.search_yCode);

    }
}
