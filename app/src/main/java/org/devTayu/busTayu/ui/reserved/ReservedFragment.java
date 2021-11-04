package org.devTayu.busTayu.ui.reserved;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.adapter.ReserveAdapter;
import org.devTayu.busTayu.database.TaYuDatabase;
import org.devTayu.busTayu.model.Reserved;
import org.devTayu.busTayu.model.ReservedDB;
import org.devTayu.busTayu.ui.station.ReservedAPI;

import java.util.ArrayList;
import java.util.List;

public class ReservedFragment extends Fragment {

    private RecyclerView mPostRecyclerView;
    private ReserveAdapter mAdpater;
    private List<Reserved> mDatas;  //데이터를 넣은 리스트 변수

    private TaYuDatabase db;
    private ReservedDB reservedDB;
    private ReservedAPI reservedAPI;

    ArrayList<ArrayList<List>> stackData = new ArrayList<>();
    ArrayList<List> integerList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reserved, container, false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mDatas = new ArrayList<>();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initUI(root);
                    }
                });
            }
        }).start();

        // 데이터베이스 생성
        db = Room.databaseBuilder(getContext(), TaYuDatabase.class, "TaYu_database").build();
        // UI 갱신 (라이브데이터 Observer 이용, 해당 디비값이 변화가생기면 실행됨)
        db.reservedDAO().getAll().observe(getViewLifecycleOwner(), new Observer<List<ReservedDB>>() {
            @Override
            public void onChanged(List<ReservedDB> dataList) {
                // mResultTextView.setText(dataList.toString());
                // mDatas.addAll(dataList);
                /*
                    mDatas.addAll(dataList); 와 같음
                    for (int i = 0; i < dataList.size(); i++) {
                        mDatas.add(dataList.get(i));
                    }
                */
                System.out.println("여기부터");

                for (int i = 0; i < dataList.size(); i++) {
                    System.out.println(dataList.get(i).getBusNumber());
                    System.out.println(dataList.get(i).getStationNumber());

                    callAPI(dataList.get(i).getStationNumber(), dataList.get(i).getBusNumber());
                }
                System.out.println(dataList);
                System.out.println("여기까지");

            }
        });

        return root;
    }

    public void initUI(View root) {
        mPostRecyclerView = getView().findViewById(R.id.recyclerView_reserved);
        mPostRecyclerView.setHasFixedSize(true); // 리사이클러뷰 기존 성능 강화

        // mDatas = new ArrayList<>(); // 샘플 데이터 추가
        // mDatas.add(new Reserved("남은 시간", "버스번호", "정류소번호", "도착예정시간", "버스 정보"));
        // mDatas.add(new Reserved("2 분 06초 남음", "6638", "17001", "2021.10.17 12:25", "버스 상태 : 여유"));

        mDatas.add(new Reserved("남은 시간", "버스번호", "정류소번호","버스명","정류소명","도착정보1","도착정보2"));

        for (int i = 0; i < stackData.size(); i++) {
//            mDatas.add(stackData.get(i));
        }

        // Adapter, LayoutManager 연결
        mAdpater = new ReserveAdapter(mDatas);
        mPostRecyclerView.setAdapter(mAdpater);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void callAPI(String station_num, String bus_num) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reservedAPI = new ReservedAPI();
                    try {
                        // api 돌려서 결과 값들이 나올거 아님? 그럼 그 결과 값들을 다른데서 했던것 처럼 mData에 넣어줘야하는데 그 전에 모아서 줘야 하니까
                        // stackDatas = new ArrayList<>();

                        //  = reservedAPI.reserved_arsId(station_num, bus_num);

                        integerList.add(reservedAPI.reserve_arsId(station_num,bus_num));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
