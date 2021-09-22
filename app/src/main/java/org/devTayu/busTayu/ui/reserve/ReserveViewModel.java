package org.devTayu.busTayu.ui.reserve;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReserveViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReserveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is reserve 예약 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}