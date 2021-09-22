package org.devTayu.busTayu.ui.route;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.devTayu.busTayu.MainActivity;
import org.devTayu.busTayu.R;
import org.devTayu.busTayu.activity.RouteActivity;

public class RouteFragment extends Fragment {

    private RouteActivity routeActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        routeActivity = new RouteActivity();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_route, container, false);

        //해시키
        //routeActivity.getHashKey();

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
        Log.d("안녕", "**************** 루트 프래그먼트 찍힘 ****************");
        //Intent aboutScreen = new Intent(getContext(), RouteActivity.class);
        //this.startActivity(aboutScreen);
        //routeActivity.openMap();
    }
}

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