package org.devTayu.busTayu.ui.setup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SetupViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SetupViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is 설정 setup fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}