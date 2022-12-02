package com.hashem.workshopmidexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.hashem.workshopmidexam.databinding.ActivityQuestion4Binding;

import java.util.ArrayList;
import java.util.List;

public class Question3Activity extends AppCompatActivity {
    ActivityQuestion4Binding binding;
    List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestion4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fragments = new ArrayList<>();

        for (int i = 1; i <= 500 ; i++) {
            fragments.add(Question3Fragment.newInstance(i));
        }
        NumberAdapter adapter = new NumberAdapter(this,fragments);

        binding.VP2.setAdapter(adapter);
    }
}