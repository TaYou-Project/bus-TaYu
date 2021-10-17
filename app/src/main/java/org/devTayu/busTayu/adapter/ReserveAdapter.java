package org.devTayu.busTayu.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.holder.ReserveHolder;
import org.devTayu.busTayu.model.Reserve;

import java.util.List;

public class ReserveAdapter extends RecyclerView.Adapter<ReserveHolder> {

    // 해당 어댑터의 ViewHolder를 상속받는다.
    private List<Reserve> mItemList;

    public ReserveAdapter(List<Reserve> datas) {
        this.mItemList = datas;
        //localDataSet = dataSet
    }

    public interface OnItemClickEventListener {
        void onItemClick(int a_position);
    }

    private OnItemClickEventListener mItemClickListener = new OnItemClickEventListener() {
        @Override
        public void onItemClick(int a_position) {
        }
    };

    // 필수 1 : 뷰홀더를 생성(레이아웃 생성)
    @NonNull
    @Override
    public ReserveHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_reserve, viewGroup, false);
        return new ReserveHolder(view, mItemClickListener);
    }

    // 필수 2 : 뷰홀더가 재활용될 때 실행되는 메서드
    @Override
    public void onBindViewHolder(@NonNull ReserveHolder reserveHolder, int position) {

        Reserve data = mItemList.get(position);

        reserveHolder.leastTime.setText(data.getLeastTime());
        reserveHolder.busNumber.setText(data.getBusNumber());
        reserveHolder.stationName.setText(data.getStationName());
        reserveHolder.expectationTime.setText(data.getExpectationTime());
        reserveHolder.busInfo.setText(data.getBusInfo());
    }

    // 필수 3 : 아이템 개수를 조회
    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
