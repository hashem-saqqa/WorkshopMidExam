package com.hashem.workshopmidexam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hashem.workshopmidexam.databinding.FragmentQuestion4Binding;

public class Question3Fragment extends Fragment {

    private static final String ARG_NUMBER = "number";

    private int number;

    public Question3Fragment() {
        // Required empty public constructor
    }

    public static Question3Fragment newInstance(int number) {
        Question3Fragment fragment = new Question3Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            number = getArguments().getInt(ARG_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentQuestion4Binding binding = FragmentQuestion4Binding.inflate(inflater);

        for (int i = 1; i <= 12; i++) {
            int result = number * i;
            String text = number+" x "+i + " = "+result +"\n\n";
            binding.tableTv.append(text);
        }
        return binding.getRoot();
    }
}