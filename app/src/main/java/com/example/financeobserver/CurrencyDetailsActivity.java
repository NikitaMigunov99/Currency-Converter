package com.example.financeobserver;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;


import com.example.financeobserver.ui.CurrencyConverterFragment;
import com.example.financeobserver.ui.CurrencyHistoryFragment;
import com.google.android.material.tabs.TabLayout;


public class CurrencyDetailsActivity extends FragmentActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_details);

        Intent intent= getIntent();

        String charCode= intent.getStringExtra("currency_CharCode");
        String numCode= intent.getStringExtra("currency_NumCode");
        String value= intent.getStringExtra("currencyValue");

        tabLayout= findViewById(R.id.tab_layout);
        viewPager= findViewById(R.id.view_pager);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), charCode, numCode, value));

    }


    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private String charCode;
        private String numCode;
        private String currencyValue;

        public ViewPagerAdapter(FragmentManager manager, String charCode,String numCode, String currencyValue) {
            super(manager);
            this.charCode= charCode;
            this.numCode= numCode;
            this.currencyValue= currencyValue;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return CurrencyConverterFragment.newInstance(charCode, currencyValue);
                case 1:
                    return CurrencyHistoryFragment.newInstance(numCode);
                default:
                    return CurrencyConverterFragment.newInstance(charCode, currencyValue);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0:
                    return "Конвертер";
                case 1:
                    return "История";
                default:
                    return "";
            }
        }

    }
}
