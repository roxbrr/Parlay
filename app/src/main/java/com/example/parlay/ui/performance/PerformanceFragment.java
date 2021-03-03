package com.example.parlay.ui.performance;

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

public class PerformanceFragment extends Fragment
{

    private PerformanceViewModel performanceViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        performanceViewModel =
                ViewModelProviders.of(this).get(PerformanceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_performance, container, false);
        final TextView textView = root.findViewById(R.id.text_performance);
        performanceViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>()
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