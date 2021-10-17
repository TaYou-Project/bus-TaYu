package org.devTayu.busTayu.ui.reserve;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.devTayu.busTayu.R;
import org.devTayu.busTayu.activity.LikedActivity;
import org.devTayu.busTayu.activity.ReserveActivity;
import org.devTayu.busTayu.adapter.ReserveAdapter;

public class ReserveFragment extends Fragment {

    ReserveActivity reserveActivity;

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

        Intent intent = new Intent(getContext(), ReserveActivity.class);
        this.startActivity(intent);
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
