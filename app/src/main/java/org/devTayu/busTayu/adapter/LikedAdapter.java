package org.devTayu.busTayu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.holder.LikedHolder;
import org.devTayu.busTayu.model.Liked;

import java.util.List;

public class LikedAdapter extends RecyclerView.Adapter<LikedHolder> {

    // 해당 어댑터의 ViewHolder를 상속받는다.
    private final List<Liked> mItemList;

    public LikedAdapter(List<Liked> datas) {
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
    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public LikedHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // ViewHodler 객체를 생성 후 리턴한다.
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_liked, viewGroup, false);
        return new LikedHolder(view, mItemClickListener);
    }

    // 필수 2 : 뷰홀더가 재활용될 때 실행되는 메서드
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull LikedHolder likedHolder, int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        // ViewHolder 가 재활용 될 때 사용되는 메소드
        Liked data = mItemList.get(position);

        likedHolder.stationName.setText(data.getStNm());
        likedHolder.stationNum.setText(data.getStationNum());
        likedHolder.adirection.setText(data.getAdirection());
        likedHolder.rtNm.setText(data.getRtNm());
        likedHolder.arrmsgSec1.setText(data.getArrmsgSec1());
        likedHolder.arrmsgSec2.setText(data.getArrmsgSec2());
    }

    // 필수 3 : 아이템 개수를 조회
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mItemList.size();
        //return localDataSet.length;
    }
}
