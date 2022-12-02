package com.hashem.workshopmidexam;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.hashem.workshopmidexam.databinding.FragmentMyDialogBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyDialog extends DialogFragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MSG = "dialogMsg";
    private static final String ARG_DIALOG_TYPE = "dialogType";
    private static final String ARG_PRODUCT_POSITION = "dialogPosition";

    private String dialogMsg;
    private int dialogType;
    private int productPosition;
    private OnDialogActionListener listener;

    public MyDialog() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (OnDialogActionListener) context;
    }

    public static MyDialog newInstance(String dialogMsg, int dialogType, int productPosition) {
        MyDialog fragment = new MyDialog();
        Bundle args = new Bundle();
        args.putString(ARG_MSG, dialogMsg);
        args.putInt(ARG_DIALOG_TYPE, dialogType);
        args.putInt(ARG_PRODUCT_POSITION, productPosition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dialogMsg = getArguments().getString(ARG_MSG);
            dialogType = getArguments().getInt(ARG_DIALOG_TYPE);
            productPosition = getArguments().getInt(ARG_PRODUCT_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMyDialogBinding binding = FragmentMyDialogBinding.inflate(inflater);

        binding.exitTv.setText(dialogMsg);

        binding.yesBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogType == 0)
                    getActivity().finish();
                else
                    listener.onYesClick(productPosition);dismiss();

            }
        });
        binding.noBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    public interface OnDialogActionListener {
        void onYesClick(int pos);
    }
}