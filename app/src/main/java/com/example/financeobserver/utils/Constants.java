package com.example.financeobserver.utils;

import android.util.Log;

import com.example.financeobserver.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class  Constants {

    private HashMap< String, Integer> images= new HashMap<>();

    private  final List<Integer> CURRENCY_FLAGS = Arrays.asList(
            R.drawable.eur, R.drawable.cad, R.drawable.hkd, R.drawable.dkk, R.drawable.huf,
            R.drawable.czk, R.drawable.aud, R.drawable.ron, R.drawable.sek, R.drawable.inr,
            R.drawable.brl, R.drawable.jpy, R.drawable.chf, R.drawable.sgd, R.drawable.pln,
            R.drawable.bgn, R.drawable.cny, R.drawable.nok, R.drawable.zar, R.drawable.usd,
            R.drawable.gbp, R.drawable.krw, R.drawable.tur, R.drawable.uah, R.drawable.usd,
            R.drawable.uzs, R.drawable.amd, R.drawable.tmt, R.drawable.kgs, R.drawable.mdl,
            R.drawable.kzt, R.drawable.dkk, R.drawable.byn, R.drawable.azn, R.drawable.tjs,
            R.drawable.rub);

    private  final List<String>  CURRENCY_COUNTRIES= Arrays.asList(
            "EUR", "CAD", "HKD", "DKK", "HUF", "CZK", "AUD", "RON", "SEK", "INR",
            "BRL", "JPY", "CHF", "SGD", "PLN", "BGN", "CNY", "NOK", "ZAR", "USD",
            "GBP", "KRW", "TUR", "UAH", "USD", "UZS", "AMD", "TMT", "KGS", "MDL",
            "KZT", "DKK", "BYN","AZN","TJS","RUB"
    );



    public  HashMap<String, Integer> getImages() {
        for (int i=0; i<CURRENCY_COUNTRIES.size(); i++){
            images.put(CURRENCY_COUNTRIES.get(i),CURRENCY_FLAGS.get(i));
        }
        Log.d("Test", "value images "+images.size());
        return images;
    }
}
