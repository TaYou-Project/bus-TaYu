/*package org.devTayu.busTayu.ui.route;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.daum.mf.map.api.MapView;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.activity.LocationActivity;
import org.devTayu.busTayu.activity.RouteActivity;

public class RouteFragment extends Fragment {

    RouteActivity routeActivity;
    LocationActivity locationActivity;
    public MapView m_MapView;
    public ViewGroup m_mapViewContainer;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //routeActivity = new RouteActivity(context, getActivity());
        locationActivity = new LocationActivity(context, getActivity());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_route, container, false);

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
        Log.d("유소정", "**************** RouteFragment 찍힘 ****************");
        //Intent aboutScreen = new Intent(getContext(), RouteActivity.class);
        //this.startActivity(aboutScreen);
        //routeActivity.getTest();
        //((RouteActivity) getActivity()).getMap();

        Intent intent = new Intent(getContext(), RouteActivity.class);
        this.startActivity(intent);

    }
}*/

//기본 제공 : ViewModel 사용법 보려고 남겨둔 것
/*
public class RouteFragment extends Fragment {

    private RouteViewModel routeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        routeViewModel =
                new ViewModelProvider(this).get(RouteViewModel.class);
//        View root = inflater.inflate(R.layout.activity_map, container, false);
        View root = inflater.inflate(R.layout.fragment_route, container, false);
        final TextView textView = root.findViewById(R.id.text_route);
        routeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }
}
*/