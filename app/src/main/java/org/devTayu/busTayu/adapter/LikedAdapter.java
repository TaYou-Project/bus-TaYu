package org.devTayu.busTayu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.model.Liked;

import java.util.List;

public class LikedAdapter extends RecyclerView.Adapter<LikedAdapter.ViewHolder> {

    // 해당 어댑터의 ViewHolder를 상속받는다.
    private final List<Liked> datas;

    public LikedAdapter(List<Liked> datas) {
        this.datas = datas;
        //localDataSet = dataSet
    }

    // 필수 1 : 뷰홀더를 생성(레이아웃 생성)
    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // ViewHodler 객체를 생성 후 리턴한다.
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_liked, viewGroup, false);
        return new ViewHolder(view);
    }

    // 필수 2 : 뷰홀더가 재활용될 때 실행되는 메서드
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        // ViewHolder 가 재활용 될 때 사용되는 메소드
        Liked data = datas.get(position);

        viewHolder.stationName.setText(data.getStationName());
        viewHolder.stationNum.setText(data.getStationNum());
        viewHolder.adirection.setText(data.getAdirection());
        viewHolder.rtNm.setText(data.getRtNm());
        viewHolder.arrmsgSec1.setText(data.getArrmsgSec1());
        viewHolder.arrmsgSec2.setText(data.getArrmsgSec2());
    }

    // 필수 3 : 아이템 개수를 조회
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return datas.size();
        //return localDataSet.length;
    }

    // 아이템 뷰를 저장하는 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        // ViewHolder 에 필요한 데이터들을 적음.
        private TextView stationName;
        private TextView stationNum;
        private TextView adirection;
        private TextView rtNm;
        private TextView arrmsgSec1;
        private TextView arrmsgSec2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // 아이템 뷰에 필요한 View
            stationName = itemView.findViewById(R.id.liked_stationName);
            stationNum = itemView.findViewById(R.id.liked_stationNum);
            adirection = itemView.findViewById(R.id.liked_adirection);
            rtNm = itemView.findViewById(R.id.liked_rtNm);
            arrmsgSec1 = itemView.findViewById(R.id.liked_arrmsgSec1);
            arrmsgSec2 = itemView.findViewById(R.id.liked_arrmsgSec2);
        }
    }

}
