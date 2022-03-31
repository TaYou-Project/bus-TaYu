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

public class ReservedFragment2 extends Fragment {

    private RecyclerView mPostRecyclerView;
    private ReserveAdapter mAdpater;
    private List<Reserved> mDatas;  //데이터를 넣은 리스트 변수

    // private List<List> joined;
    ArrayList<List> what;

    private TaYuDatabase db;
    private ReservedDB reservedDB;
    private ReservedAPI reservedAPI;

    ArrayList<Reserved> joined = new ArrayList<>();
    ArrayList<Reserved> tempList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reserved, container, false);

        joined = new ArrayList<>();
        joined.clear();

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

                // DB에 있는 Bus번호, Station번호 stackList에 담기
                ArrayList<Reserved> stackList = new ArrayList<>();
                for (int i = 0; i < dataList.size(); i++) {
                    // callAPI(dataList.get(i).getStationNumber(), dataList.get(i).getBusNumber());
                    // System.out.println(dataList.get(i).getStationNumber() + dataList.get(i).getBusNumber() + " stackList 저장");

                    Reserved temp = new Reserved();
                    /*
                        temp.setStackBus(dataList.get(i).getBusNumber());
                        temp.setStackStation(dataList.get(i).getStationNumber());
                    */
                    stackList.add(temp);
                }

                // 데이터 리스트 사이즈 만큼 반복해서 호출
                // 스택 사이즈로 할까?
                //for (int i = 0; i < dataList.size(); i++) {
                // call2(dataList.get(i).getStationNumber(), dataList.get(i).getBusNumber(), root);

                // 한번 돌릴때마다 어레이 리스트 만들고
                // call1(i, dataList.get(i).getStationNumber(), dataList.get(i).getBusNumber());
                // call4(dataList.get(i).getStationNumber(), dataList.get(i).getBusNumber());

                // joined.addAll(listName);
                //}
                System.out.println(dataList.size() + " 데이터 리스트 사이즈");

                // stackList에 담았던 것 확인차 출력 시키기
                for (Reserved i : stackList) {
                    // mDatas = new ArrayList<>();

                    /*
                        System.out.println(i.getStackStation() + i.getStackBus() + " stackList 출력");
                    */

                    // call2(i.getStackStation(), i.getStackBus(), root);

                    // tempList = new ArrayList<>();
                    // tempList = call5(i.getStackStation(), i.getStackBus());
                    // joined.addAll(tempList);
                }
                // mDatas.addAll(stackList);

                call3(root);
                // System.out.println(dataList+"데이터리스트");
                System.out.println("여기까지");
            }
        });

        return root;
    }

    public ArrayList<Reserved> call5(String string_num2, String bus_num2) {
        ArrayList<Reserved> temp = new ArrayList<>();
        temp.addAll(reservedAPI.reserve_arsId(string_num2, bus_num2));
        return temp;
    }

    public List<Reserved> call1(int i, String string_num2, String bus_num2) {
        String tempName = "callList" + i;
        // ArrayList<Reserved> tempName = new ArrayList<>();
        //listName = reservedAPI.reserve_arsId(string_num2, bus_num2);
//        listName.add(i, a.get(i).getStackBus()); 여기 고치고 있었음
        // 배열을 합칠라고 하다가 갑자기 꼬여서, 배열안에 있는 내용들을 각각 가져와서 새로 배열 만드는 길로 가고있었던 것 같음
        // 어느 방향으로 갈지 다시 봐야할듯
        // 새로 배열 만드는 길로 갈때 만약에 값이 없어서 null이 되면 안되니까 이건 model에서 처리를 하든 뭘 하든 해야 함

        // joined.addAll(listName);

        return tempList;
    }

    public void call3(View root) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    joined = new ArrayList<>();
                    mDatas.addAll(joined);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            initUI(root);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    public void callAPI(String station_num, String bus_num) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // reservedAPI = new ReservedAPI();

                // what = new ArrayList<>();
                try {
                    // api 돌려서 결과 값들이 나올거 아님? 그럼 그 결과 값들을 다른데서 했던것 처럼 mData에 넣어줘야하는데 그 전에 모아서 줘야 하니까
                    // stackDatas = new ArrayList<>();
//                    ArrayList<String> stringlist = new ArrayList<>();

//                    stringlist = reservedAPI.reserve_arsId(station_num, bus_num);
//                    Reserved entity = new Reserved();

/*
                    joined= new ArrayList<>();
                    joined.add(reservedAPI.reserve_arsId(station_num,bus_num));
                    mDatas.addAll(joined);
*/
                    // what.add(reservedAPI.reserve_arsId(station_num, bus_num));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void initUI(View root) {
        mPostRecyclerView = getView().findViewById(R.id.recyclerView_reserved);
        mPostRecyclerView.setHasFixedSize(true); // 리사이클러뷰 기존 성능 강화

        // mDatas = new ArrayList<>(); // 샘플 데이터 추가
        // mDatas.add(new Reserved("남은 시간", "버스번호", "정류소번호", "도착예정시간", "버스 정보"));
        // mDatas.add(new Reserved("2 분 06초 남음", "6638", "17001", "2021.10.17 12:25", "버스 상태 : 여유"));

        mDatas.add(new Reserved("남은 시간", "버스번호", "정류소번호", "버스명", "정류소명", "도착정보1", "도착정보2"));

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
