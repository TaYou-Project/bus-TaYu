package org.devTayu.busTayu.ui.around;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.daum.mf.map.api.MapView;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.activity.AroundActivity;
import org.devTayu.busTayu.activity.LocationActivity;

public class AroundFragment extends Fragment {

    AroundActivity aroundActivity;
    LocationActivity locationActivity;
    public MapView m_MapView;
    public ViewGroup m_mapViewContainer;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //aroundActivity = new AroundActivity(context, getActivity());
        locationActivity = new LocationActivity(context, getActivity());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_around, container, false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                initUI(root);
                String getTextView = locationActivity.getTextView();

                Log.d("유소정", "**************** 주소 찍힘 ****************"+ "LocationActivity:"+ getTextView);
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
        Log.d("유소정", "**************** Around Fragment 찍힘 ****************");
        //Intent aboutScreen = new Intent(getContext(), AroundActivity.class);
        //this.startActivity(aboutScreen);
        //aroundActivity.getTest();
        //((AroundActivity) getActivity()).getMap();

        /* 사용자 현 위치 : 지금은 _구_동 까지 -> 변경하기 */
        TextView text = (TextView) root.findViewById(R.id.around_title);
        text.setText(locationActivity.getTextView());
        //text.setText("서울시 종로구 세종로");

        Intent intent = new Intent(getContext(), AroundActivity.class);
        this.startActivity(intent);

    }
}

// 기본 제공 AroundViewModel 사용 시 확인
/*
public class AroundFragment extends Fragment {

    private AroundViewModel mapViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mapViewModel = new ViewModelProvider(this).get(AroundViewModel.class);
        View root = inflater.inflate(R.layout.fragment_around, container, false);
        final TextView textView = root.findViewById(R.id.text_around);
        mapViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
*/