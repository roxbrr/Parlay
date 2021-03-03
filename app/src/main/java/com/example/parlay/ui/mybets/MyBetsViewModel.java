package com.example.parlay.ui.mybets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyBetsViewModel extends ViewModel
{

    private MutableLiveData<String> mText;

    public MyBetsViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("My Bets / Home Fragment");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}