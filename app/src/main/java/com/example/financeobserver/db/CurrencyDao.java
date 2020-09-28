package com.example.financeobserver.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.financeobserver.models.Currency;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface CurrencyDao {

    @Insert(onConflict = REPLACE)
    void insertAll(List<Currency> currencies);

    @Query("SELECT * FROM currency")
    List<Currency> getCurrencies();

}
