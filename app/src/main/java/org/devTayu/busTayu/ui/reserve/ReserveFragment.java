package org.devTayu.busTayu.ui.reserve;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.adapter.ReserveAdapter;
import org.devTayu.busTayu.model.Reserve;

import java.util.ArrayList;
import java.util.List;

public class ReserveFragment extends Fragment {

    private RecyclerView mPostRecyclerView;
    private ReserveAdapter mAdpater;
    private List<Reserve> mDatas;  //데이터를 넣은 리스트 변수

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reserve, container, false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                initUI(root);
            }
        }).start();

        return root;
    }

    public void initUI(View root) {

        mPostRecyclerView = getView().findViewById(R.id.recyclerView_reserve);
        mDatas = new ArrayList<>(); // 샘플 데이터 추가

        mDatas.add(new Reserve("남은 시간", "버스번호", "정류소번호", "도착예정시간", "버스 정보"));
        mDatas.add(new Reserve("2 분 06초 남음", "6638", "17001", "2021.10.17 12:25", "버스 상태 : 여유"));

        // Adapter, LayoutManager 연결
        mAdpater = new ReserveAdapter(mDatas);
        mPostRecyclerView.setAdapter(mAdpater);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}

/*
public class ReserveFragment extends Fragment {

    private ReserveViewModel reserveViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        reserveViewModel =
                new ViewModelProvider(this).get(ReserveViewModel.class);
        View root = inflater.inflate(R.layout.fragment_reserve, container, false);
        reserveViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}*/
