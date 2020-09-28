package com.example.financeobserver.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.financeobserver.db.AppDatabase;
import com.example.financeobserver.db.CurrencyDao;
import com.example.financeobserver.models.Currency;
import com.example.financeobserver.models.CurrencyResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyRepo {

    private final MutableLiveData<List<Currency>> mCurrencies = new MutableLiveData<>();
    private Context context;





    public void setContext(Context context) {
        this.context = context;
    }

    private void makeRequest() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        String currentDate = simpleDateFormat.format(date);

        APIRepo apiRepo = new APIRepo();
        apiRepo.getCurrencyApi().getData(currentDate).enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {

                if (response.body() != null) {
                    for (int i = 0; i < response.body().getCurrencies().size(); i++) {
                        if (response.body().getCurrencies().get(i).getCharCode().equals("XDR")) {
                            response.body().getCurrencies().remove(i);
                        }
                        if (response.body().getCurrencies().get(i).getCharCode().equals("TRY")) {
                            response.body().getCurrencies().get(i).setCharCode("TUR");
                        }
                    }

                    updateLocalData(response.body().getCurrencies());
                    Log.d("Test", "Test   mCurrencies.postValue()");
                    Log.d("Test", "Test   Date"+response.body().getDate());
                    mCurrencies.postValue(response.body().getCurrencies());
                }
            }

            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                Log.d("Test", "Failed to loud data " + t.getMessage(), t);
                t.printStackTrace();

            }
        });
    }

    public MutableLiveData<List<Currency>> getRemoteData() {
        makeRequest();

        Log.d("Test", "Test   return mCurrencies");
        return mCurrencies;
    }


    public MutableLiveData<List<Currency>> getLocalData(){
        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Test", "Test New Thread Insert");
                AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "exchange_rates-database").build();
                CurrencyDao dao=db.getCurrencyDao();
                List<Currency> currencies= dao.getCurrencies();
                mCurrencies.postValue(currencies);
            }
        });
        thread.start();
        return mCurrencies;
    }



    private void updateLocalData(final List<Currency> currencies) {
       Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Test", "Test New Thread Insert");
                AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "exchange_rates-database").build();
                CurrencyDao dao=db.getCurrencyDao();
                dao.insertAll(currencies);
            }
        });
        thread.start();
    }
}
