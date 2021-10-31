package org.devTayu.busTayu.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.adapter.SearchAdapter;
import org.devTayu.busTayu.model.Search;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private SearchView searchView;
    private DatabaseReference database;
    private RecyclerView mPostRecyclerView;
    private SearchAdapter mAdpater;
    private List<Search> mDatas;  //데이터를 넣은 리스트 변수

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                init(root);
            }
        }).start();

        return root;
    }

    private void init(View root) {
        searchView = getView().findViewById(R.id.searchView_search);

        // searchView 설정
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("유소정", "클릭-" + searchView.isIconified());
                if (searchView.isIconified()) {
                    searchView.setIconified(false);
                    Log.e("유소정", "클릭-" + searchView.isIconified());
                }
            }
        });

        // searchView 설정
        int searchCloseButtonId = searchView.getContext().getResources().getIdentifier("android:id/search_close_btn", null, null);
        ImageView closeButton = (ImageView) this.searchView.findViewById(searchCloseButtonId);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("유소정", "닫기버튼-" + searchView.isIconified());
                searchView.setIconified(true);
                Log.e("유소정", "닫기버튼-" + searchView.isIconified());
            }
        });

        // searchView 설정
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.e("유소정", "포커스변경-" + searchView.isIconified());
                if (searchView.isIconified()) {
                    searchView.setIconified(false);
                }
                Log.e("유소정", "포커스변경-" + searchView.isIconified());
            }
        });

        mPostRecyclerView = getView().findViewById(R.id.recyclerView_search);
        mPostRecyclerView.setHasFixedSize(true); // 리사이클러뷰 기존 성능 강화

        mDatas = new ArrayList<>(); // 객체 담을 arrayList
        // mDatas.add(new Search("샘플","데이터","xcode","ycode"));

        // Adapter, LayoutManager 연결
        mAdpater = new SearchAdapter(mDatas);
        mPostRecyclerView.setAdapter(mAdpater);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 전체 데이터 불러오기
        searchAllData();

        // searchView Listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 검색 버튼 누를 시 호출
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            // 검색 입력값 변경 시 호출
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("SearchFragment", "검색값 onQueryTextChange : " + newText);
                // 검색 데이터 호출
                searchData(newText);
                return true;
            }
        });
    }

    // 전체 리스트
    private void searchAllData() {
        database = FirebaseDatabase.getInstance().getReference("DATA");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아옴
                mDatas.clear(); // 기존 배열리스트가 존재하지 않게 초기화
                // 반복문으로 데이터 List를 추출
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    // 만들어 뒀던 객체에 데이터를 담는다
                    Search search = dataSnapshot.getValue(Search.class);
                    // 담은 데이터들을 배열리스트에 넣고 리사이클러 뷰로 보낼 준비
                    mDatas.add(search);
                }
                // 리스트 저장 및 새로고침해야 반영이 됨
                mAdpater.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 디비를 가져오던중 에러 발생 시 / 로그에 에러 찍힘
                Log.d("유소정", "SearchFragment 디비 에러 : " + String.valueOf(error.toException()));
            }
        });
    }

    // 검색된 데이터 리스트
    private void searchData(String searchText) {
        database = FirebaseDatabase.getInstance().getReference("DATA");
        database.orderByChild("stop_nm").startAt(searchText).endAt(searchText + "\uf8ff").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아옴
                mDatas.clear(); // 기존 배열리스트가 존재하지 않게 초기화

                // 반복문으로 데이터 List를 추출
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    // 만들어 뒀던 객체에 데이터를 담는다
                    Search search = dataSnapshot.getValue(Search.class);
                    // 담은 데이터들을 배열리스트에 넣고 리사이클러 뷰로 보낼 준비
                    mDatas.add(search);
                }
                Log.e("이은지", "검색 결과 데이터 수 : " + String.valueOf(mDatas.size()));

                // 리스트 저장 및 새로고침해야 반영이 됨
                mAdpater.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 디비를 가져오던중 에러 발생
                Log.d("유소정", "SearchFragment 디비 에러 : " + String.valueOf(error.toException()));
            }
        });
        mAdpater = new SearchAdapter(mDatas);
        mPostRecyclerView.setAdapter(mAdpater); // 리사이클러뷰에 어댑터 연결
    }
}

/*
@Override
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
}
*/
/*
public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_setup, container, false);
        final TextView textView = root.findViewById(R.id.text_setup);
        searchViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
*/
/*
database.orderByKey().startAt(what).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
    @Override
    public void onComplete(@NonNull Task<DataSnapshot> task) {
        if(!task.isSuccessful()){
            Log.e("firebase", "Error getting data", task.getException());
        }else{
            for(DataSnapshot dataSnapshot : task.) {
                Log.d("MainActivity", "ValueEventListener : " + snapshot.getValue());
            }
        }
    }
});
*/