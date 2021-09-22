package org.devTayu.busTayu.ui.around;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AroundViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AroundViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is around 주위 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}