package com.example.financeobserver.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.financeobserver.models.Currency;

@Database(entities = Currency.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CurrencyDao getCurrencyDao();

}
