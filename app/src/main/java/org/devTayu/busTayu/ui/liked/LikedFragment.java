package org.devTayu.busTayu.ui.liked;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.adapter.LikedAdapter;
import org.devTayu.busTayu.database.TaYuDatabase;
import org.devTayu.busTayu.model.Liked;
import org.devTayu.busTayu.model.LikedDB;

import java.util.ArrayList;
import java.util.List;

public class LikedFragment extends Fragment {

    private RecyclerView mPostRecyclerView;
    private LikedAdapter mAdpater;
    private List<Liked> mDatas;
    private TaYuDatabase db;
    private TextView mResultTextView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_liked, container, false);

        mResultTextView = root.findViewById(R.id.textview11111);
        // 데이터베이스 생성
        db = Room.databaseBuilder(getContext(), TaYuDatabase.class, "TaYu_database").build();
        // .build()앞에 .addCallback(sRoomDatabaseCallback)를 적어주기만 하면 미리 데이터를 저장해 놓을 수 있습니다
        //.fallbackToDestructiveMigration() : Entity에 Data를 추가하거나 변경하여 버전(version)을 바꿔야 할때 적어주면 Migration 메서드를 따로 만들지 않아도 됩니다.
        //.allowMainThreadQueries()를 호출하면 메인스레드에서 DB접근을 허용할 수 있지만, 데이터를 받아오는 작업이 길어질 경우 UI가 장시간 멈추기 때문에 실제 어플에서는 사용을 권장하지 않는다고 합니다.
        /*
        아래와 같은 것
        db.likedDAO().getAll().observe(this, dataList -> {
            mResultTextView.setText(dataList.toString());
        });
        */
        //UI 갱신 (라이브데이터 Observer 이용, 해당 디비값이 변화가생기면 실행됨)
        // UI 갱신 (라이브데이터 Observer 이용, 해당 디비값이 변화가생기면 실행됨)
        // LiveData가 데이터 변화를 감지하여 UI를 업데이트하게 됩니다.^^ 그 소스는 onCreate에서 Oberver를 통해 onChanged를 Callback받아 실행한 코드를 보면 알수 있습니다
        db.likedDAO().getAll().observe(getViewLifecycleOwner(), new Observer<List<LikedDB>>() {
            @Override
            public void onChanged(List<LikedDB> dataList) {
                mResultTextView.setText(dataList.toString());
            }
        });
        //DB 데이터 불러오기 (SELECT)
        mResultTextView.setText(db.likedDAO().getAll().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                init(root);
            }
        }).start();

        return root;
    }


    public void init(View root) {
        mPostRecyclerView = getView().findViewById(R.id.recyclerView_liked);

        mDatas = new ArrayList<>(); // 샘플 데이터 추가
        mDatas.add(new Liked("정류장명", "정류장번호", "방면", "버스", "도착정보1", "도착정보2"));
        mDatas.add(new Liked("정류장명", "정류장번호", "방면", "버스", "도착정보1", "도착정보2"));
        mDatas.add(new Liked("정류장명", "정류장번호", "방면", "버스", "도착정보1", "도착정보2"));
        mDatas.add(new Liked("정류장명", "정류장번호", "방면", "버스", "도착정보1", "도착정보2"));
        mDatas.add(new Liked("정류장명", "정류장번호", "방면", "버스", "도착정보1", "도착정보2"));
        mDatas.add(new Liked("정류장명", "정류장번호", "방면", "버스", "도착정보1", "도착정보2"));
        mDatas.add(new Liked("정류장명", "정류장번호", "방면", "버스", "도착정보1", "도착정보2"));
        mDatas.add(new Liked("정류장명", "정류장번호", "방면", "버스", "도착정보1", "도착정보2"));
        // Adapter, LayoutManager 연결
        mAdpater = new LikedAdapter(mDatas);
        mPostRecyclerView.setAdapter(mAdpater);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}

/*@Override
public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    new Thread(new Runnable() {
        @Override
        public void run() {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //initUI(root);
                }
            });
        }
    }).start();
}*/
/*
public class LikedFragment extends Fragment {
    private LikedViewModel likedViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        likedViewModel =
                new ViewModelProvider(this).get(LikedViewModel.class);
        View root = inflater.inflate(R.layout.fragment_liked, container, false);
        final TextView textView = root.findViewById(R.id.text_liked);
        likedViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
*/