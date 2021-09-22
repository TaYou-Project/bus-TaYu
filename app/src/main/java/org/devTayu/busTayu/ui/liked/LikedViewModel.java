package org.devTayu.busTayu.ui.liked;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LikedViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LikedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is liked 즐겨찾기 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}