package org.devTayu.busTayu.ui.reserved;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReservedViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReservedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is reserve 예약 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}