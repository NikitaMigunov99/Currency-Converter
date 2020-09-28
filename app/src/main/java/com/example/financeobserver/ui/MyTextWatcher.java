package com.example.financeobserver.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MyTextWatcher implements TextWatcher {


    private TextView textView;
    private float currency;

    public MyTextWatcher(TextView textView, float currency){
        super();
        this.textView= textView;
        this.currency= currency;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        try {
            if(s.toString().isEmpty()){
                textView.setText("Result");
            }
            float value= Float.parseFloat(s.toString());
            Log.d("Test", "Input number: "+value );
            float result= value*currency;
            textView.setText(Float.toString(result));
            Log.d("Test", "Result: "+result);

        }catch (NumberFormatException e){
            if(!s.toString().isEmpty())
            textView.setText("Некорректные данные");
        }

    }
}
