package com.example.financeobserver.api;

import com.example.financeobserver.models.CurrencyHistoryResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyHistoryApi {

    @GET("/scripts/XML_dynamic.asp")
    Call<CurrencyHistoryResponse> getData(@Query("date_req1") String lastDate,
                                          @Query("date_req2") String currentDate,
                                          @Query("VAL_NM_RQ") String currencyID);
}
