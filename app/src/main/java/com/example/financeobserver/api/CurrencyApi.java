package com.example.financeobserver.api;


import com.example.financeobserver.models.CurrencyResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyApi {
    @GET("/scripts/XML_daily.asp")
    Call<CurrencyResponse> getData(@Query("date_req") String date);
}
