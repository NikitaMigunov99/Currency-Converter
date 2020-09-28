package com.example.financeobserver.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.financeobserver.R;
import com.example.financeobserver.utils.Constants;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.HashMap;


public class CurrencyConverterFragment extends Fragment {

    private static String CURRENCY_CHAR_CODE= "CURRENCY_CHAR_CODE";
    private static String CURRENCY_VALUE="CURRENCY_VALUE";
    private String charCode;
    private Float currencyValue;
    private HashMap<String, Integer> images;
    private TextView textView;
    private EditText editText;
    private  MyTextWatcher textWatcher;

    public CurrencyConverterFragment() {

    }

    public static CurrencyConverterFragment newInstance(String currencyCharCode, String currencyValue) {
        CurrencyConverterFragment fragment = new CurrencyConverterFragment();
        Bundle args = new Bundle();
        args.putString(CURRENCY_CHAR_CODE, currencyCharCode);
        args.putString(CURRENCY_VALUE, currencyValue);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            charCode = getArguments().getString(CURRENCY_CHAR_CODE);
            currencyValue= Float.parseFloat(getArguments().getString(CURRENCY_VALUE).replace(",","."));
        }

        Constants constants = new Constants();
        images= constants.getImages();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_currency_converter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView= view.findViewById(R.id.result);
        editText= view.findViewById(R.id.edit_number);

        textWatcher = new MyTextWatcher(textView, currencyValue);
        editText.addTextChangedListener(textWatcher);

        CircularImageView foreignFlagImage= view.findViewById(R.id.foreign_country_flag);
        CircularImageView russiaFlagImage= view.findViewById(R.id.russia_flag);

        Glide.with(getActivity()).load(images.get(charCode)).apply(RequestOptions.circleCropTransform()).into(foreignFlagImage);
        Glide.with(getActivity()).load(images.get("RUB")).apply(RequestOptions.circleCropTransform()).into(russiaFlagImage);
    }
}
