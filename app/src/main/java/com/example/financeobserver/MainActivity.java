package com.example.financeobserver;


import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.financeobserver.adapters.CurrencyListAdapter;
import com.example.financeobserver.models.Currency;

import com.example.financeobserver.utils.LiveConnectionUtil;
import com.example.financeobserver.viewmodels.MainViewModel;

import java.util.List;

public class MainActivity extends FragmentActivity {

    private MainViewModel viewModel;
    private CurrencyListAdapter adapter;
    private RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private LiveConnectionUtil networkConnectionUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.setContext(this);
        recycler= findViewById(R.id.currency_list);
        recycleViewConfig();
        viewModelConfig();

    }

    private void recycleViewConfig(){
        layoutManager= new LinearLayoutManager(this);
        adapter= new CurrencyListAdapter(this, viewModel.getImages());
        adapter.setOnCurrencyClickListener(new CurrencyListAdapter.OnCurrencyClickListener() {
            @Override
            public void onCurrencyClick(View view, String currencyCharCode,String currencyNumCode, String value ) {
                replaceActivity(currencyCharCode, currencyNumCode, value);
            }
        });
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(layoutManager);
    }

    private void viewModelConfig(){
        if(viewModel.isFreshCurrenciesData()){
            getData();
        }

        else {
            networkConnectionUtils= new LiveConnectionUtil(this);
            if(networkConnectionUtils.isInternetOn()){
                viewModel.loadCurrenciesFromServer();
                getData();
            }
            else {
                viewModel.loadCurrenciesFromLocal();
                getData();
                Toast toast= Toast.makeText(this,"Нет интернет соединения", Toast.LENGTH_SHORT);
                toast.show();
                networkConnectionUtils.observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean ) {
                            Log.d("Test", "NetworkConnectionUtils aBoolean= true");
                            viewModel.loadCurrenciesFromServer();
                            getData();
                        }
                    }
                });
            }
        }

    }

    private void replaceActivity(String currencyCharCode,String currencyNumCode, String value){
        Intent intent= new Intent(this, CurrencyDetailsActivity.class);
        intent.putExtra("currency_CharCode", currencyCharCode);
        intent.putExtra("currency_NumCode", currencyNumCode);
        intent.putExtra("currencyValue", value);
        startActivity(intent);
    }

    private void getData(){
        viewModel.getCurrencies().observe(this, new Observer<List<Currency>>() {
            @Override
            public void onChanged(List<Currency> currencies) {
                adapter.setCurrencyList(currencies);
            }
        });
    }

}


