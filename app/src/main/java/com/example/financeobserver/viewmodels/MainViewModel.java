package com.example.financeobserver.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.financeobserver.models.Currency;
import com.example.financeobserver.models.CurrencyHistoryResponse;
import com.example.financeobserver.repository.CurrencyHistoryRepo;
import com.example.financeobserver.repository.CurrencyRepo;
import com.example.financeobserver.utils.Constants;

import java.util.HashMap;
import java.util.List;


public class MainViewModel extends ViewModel {


    private Context context;
    private CurrencyRepo repo= new CurrencyRepo();
    private LiveData<List<Currency>> currencies;
    private boolean freshCurrenciesData= false;
    private LiveData<CurrencyHistoryResponse> currencyHistory;
    private HashMap<String, Integer> images;


    public void setContext(Context context) {
        if(this.context==null) {
            this.context = context;
            repo.setContext(this.context);
        }
    }
    public void loadCurrenciesFromServer(){

        currencies = repo.getRemoteData();
        freshCurrenciesData=true;
        Log.d("Test", "Test MainViewModel loadCurrenciesFromServer()");
    }

    public void loadCurrenciesFromLocal(){

        currencies = repo.getLocalData();
        freshCurrenciesData=false;
        Log.d("Test", "Test MainViewModel loadCurrenciesFromLocal()");
    }


    public boolean isFreshCurrenciesData(){

        return freshCurrenciesData;
    }


    public LiveData<List<Currency>> getCurrencies(){
        Log.d("Test", "Test MainViewModel getData()");
        return currencies;
    }


/*
    public void loadCurrencyHistory(){
        if(currencyHistory!=null) {
            return;
        }
        currencyHistory = currencyHistoryRepo.getData();
    }

    public LiveData<CurrencyHistoryResponse> getCurrencyHistory(){
        Log.d("Test", "Test MainViewModel getData()");
        return currencyHistory;
    }*/

    public HashMap<String, Integer> getImages(){
        if(images==null){
            Constants constants= new Constants();
            images=constants.getImages();
            Log.d("Test", "load Images ");
        }
        return images;
    }

}
