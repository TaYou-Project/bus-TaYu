package org.devTayu.busTayu.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.activity.SearchActivity;
import org.devTayu.busTayu.adapter.SearchAdapter;
import org.devTayu.busTayu.model.Search;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

    SearchActivity searchActivity;
    private SearchView searchView;

    //검색에 필요한 데이터 가져오기
    private RecyclerView mPostRecyclerView;
    private SearchAdapter mAdpater;
    private List<Search> mDatas;  //데이터를 넣은 리스트 변수
    public TextView result_stationName;
    public TextView result_stationNumber;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);


        Log.e("intent","onCreateView");

        new Thread(new Runnable() {
            @Override
            public void run() {
                initUI(root);






            }
        }).start();

        return root;
    }

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

    public void initUI(View root) {

        Intent intent = new Intent(getContext(), SearchActivity.class);
        this.startActivity(intent);

    }
}

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