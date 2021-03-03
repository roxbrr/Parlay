package com.example.parlay.ui.mybets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.parlay.R;

public class MyBetsFragment extends Fragment
{

    private MyBetsViewModel myBetsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        myBetsViewModel = ViewModelProviders.of(this).get(MyBetsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_bets, container, false);
        final TextView textView = root.findViewById(R.id.text_my_bets);
        myBetsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
                textView.setText(s);
            }
        });
        return root;
    }
}