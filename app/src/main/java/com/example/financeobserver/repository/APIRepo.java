package com.example.financeobserver.repository;

import com.example.financeobserver.api.CurrencyApi;
import com.example.financeobserver.api.CurrencyHistoryApi;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class APIRepo {

    private static CurrencyApi currencyApi;
    private static CurrencyHistoryApi currencyHistoryApi;
    private Retrofit retrofit;

    public APIRepo(){
        retrofit = new Retrofit.Builder()
            .baseUrl("http://www.cbr.ru")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build();
        currencyApi = retrofit.create(CurrencyApi.class);
        currencyHistoryApi= retrofit.create(CurrencyHistoryApi.class);
    }

    public  CurrencyApi getCurrencyApi() {

        return currencyApi;
    }

    public CurrencyHistoryApi getCurrencyHistoruApi() {

        return currencyHistoryApi;
    }
}
