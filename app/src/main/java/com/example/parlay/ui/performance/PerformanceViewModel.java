package com.example.parlay.ui.performance;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PerformanceViewModel extends ViewModel
{

    private MutableLiveData<String> mText;

    public PerformanceViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("Performance fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}