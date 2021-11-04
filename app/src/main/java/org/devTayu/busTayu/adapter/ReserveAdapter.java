package org.devTayu.busTayu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.holder.ReserveHolder;
import org.devTayu.busTayu.model.Reserved;

import java.util.List;

public class ReserveAdapter extends RecyclerView.Adapter<ReserveHolder> {

    // 해당 어댑터의 ViewHolder를 상속받는다.
    private List<Reserved> mItemList;

    public ReserveAdapter(List<Reserved> datas) {
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_reserved, viewGroup, false);
        return new ReserveHolder(view, mItemClickListener);
    }

    // 필수 2 : 뷰홀더가 재활용될 때 실행되는 메서드
    @Override
    public void onBindViewHolder(@NonNull ReserveHolder reserveHolder, int position) {

        Reserved data = mItemList.get(position);

        // reserveHolder.leastTime.setText(data.getLeastTime());
        reserveHolder.busNumber.setText(data.getRtNm());
        reserveHolder.stationName.setText(data.getStNm());
        reserveHolder.stationNum.setText(data.getArsId());
        reserveHolder.busNumber.setText(data.getRtNm());
        reserveHolder.adirection.setText(data.getAdirection());
        reserveHolder.busInfo.setText(data.getArrmsgSec1());
        /*
         *
         * 도착정보 지금 첫번째 정보만 나오게 해둔거라 예약을 첫번째 버스로 했는지 두 번째 버스로 했는지 걸러서 들어와야 함
         * 변경 필요
         *
         *
         */
        // reserveHolder.expectationTime.setText(data.get());
    }

    // 필수 3 : 아이템 개수를 조회
    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
