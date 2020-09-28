package com.example.financeobserver.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.financeobserver.models.CurrencyHistoryResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyHistoryRepo {
    private final MutableLiveData<CurrencyHistoryResponse> mCurrencyHistoryResponse= new MutableLiveData<>();

    private void makeRequest(){
        Date date= Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
        Calendar calendar =Calendar.getInstance();
        calendar.add(Calendar.DATE,-7);
        String lastDate= simpleDateFormat.format(calendar.getTime());
        String currentDate=simpleDateFormat.format(date);

        APIRepo apiRepo= new APIRepo();
        apiRepo.getCurrencyHistoruApi().getData(lastDate, currentDate,"R01235").
                enqueue(new Callback<CurrencyHistoryResponse>() {
            @Override
            public void onResponse(Call<CurrencyHistoryResponse> call, Response<CurrencyHistoryResponse> response) {
                mCurrencyHistoryResponse.postValue(response.body());
            }

            @Override
            public void onFailure(Call<CurrencyHistoryResponse> call, Throwable t) {
                Log.d("Test", "Failed to loud data " + t.getMessage(), t);
                t.printStackTrace();
            }
        });

    }

    public MutableLiveData<CurrencyHistoryResponse> getData(){
        makeRequest();
        return mCurrencyHistoryResponse;
    }

}
