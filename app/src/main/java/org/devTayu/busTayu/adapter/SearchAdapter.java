package org.devTayu.busTayu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.holder.SearchHolder;
import org.devTayu.busTayu.model.Search;
import org.devTayu.busTayu.model.Station;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchHolder> {

    // 해당 어댑터의 ViewHolder를 상속받는다.
    private final List<Search> datas;

    public SearchAdapter(List<Search> datas) {
        this.datas = datas;
        //localDataSet = dataSet
    }

    // 필수 1 : 뷰홀더를 생성(레이아웃 생성)
    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // ViewHodler 객체를 생성 후 리턴한다.
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_search, viewGroup, false);
        return new SearchHolder(view);
    }

    // 필수 2 : 뷰홀더가 재활용될 때 실행되는 메서드
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull SearchHolder searchHolder, int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Search data = datas.get(position);

        searchHolder.stationName.setText(data.getStop_nm());
        searchHolder.stationNumber.setText(data.getStop_no());
        // 파베에 올린 데이터로는 방면 표시 불가라 한번 더 거쳐서 가져와야 함 searchHolder.stationWay.setText(data.());
        searchHolder.xCode.setText(data.getXcode());
        searchHolder.yCode.setText(data.getYcode());
    }

    // 필수 3 : 아이템 개수를 조회
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return datas.size();
        //return localDataSet.length;
    }

}
