package com.example.financeobserver.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.financeobserver.R;
import com.example.financeobserver.databinding.CurrencyItemBinding;
import com.example.financeobserver.models.Currency;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurrencyListAdapter extends RecyclerView.Adapter<CurrencyListAdapter.CurrencyListHolder> {

    private ArrayList<Currency> data= new ArrayList<>();
    private Resources resources;
    private Context context;
    private String packageName;
    private OnCurrencyClickListener onCurrencyClickListener;
    private HashMap<String, Integer> images;


    public CurrencyListAdapter(Context context, HashMap<String, Integer> images){
        resources= context.getResources();
        packageName= context.getPackageName();
        this.context= context;
        this.images= images;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        data.addAll(currencyList);
        notifyDataSetChanged();
    }
    public void setOnCurrencyClickListener(OnCurrencyClickListener onCurrencyClickListener){
        this.onCurrencyClickListener=onCurrencyClickListener;
    }

    @NonNull
    @Override
    public CurrencyListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CurrencyItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.currency_list_item, parent, false);
        return new CurrencyListHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyListHolder holder, int position) {
        final Currency currency= data.get(position);
        final String charCode=currency.getCharCode();

        String currencyName= resources.getString(resources.getIdentifier(charCode, "string",packageName));
        currency.setName(currencyName);

        holder.bind(currency);
        Glide.with(context).load(images.get(charCode)).apply(RequestOptions.circleCropTransform()).into(holder.flagImage);

        holder.itemBinding.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCurrencyClickListener.onCurrencyClick(v, charCode, currency.getNumCode(), currency.getValue() );
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


     public class CurrencyListHolder extends RecyclerView.ViewHolder {
        CurrencyItemBinding itemBinding;
        ImageView flagImage;

        public CurrencyListHolder(CurrencyItemBinding currencyItemBinding) {
            super(currencyItemBinding.getRoot());
            itemBinding= currencyItemBinding;
            flagImage=itemView.findViewById(R.id.flag_image);

        }

         public void bind(Currency currency) {
            itemBinding.setCurrency(currency);
            itemBinding.executePendingBindings();
         }

    }

    public interface OnCurrencyClickListener{
        void onCurrencyClick(View view, String currencyCharCode,String currencyNumCode, String value);
    }
}
