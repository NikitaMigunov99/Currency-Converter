package com.example.financeobserver.models;
import com.example.financeobserver.models.Currency;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root
public class CurrencyResponse {

    @Attribute(name = "Date")
    private String date;

    @Attribute(name = "name")
    private String name;

    @ElementList(inline = true, required = false)
    private ArrayList<Currency> currencies= new ArrayList<>();

    public CurrencyResponse(){}

    public CurrencyResponse(String date, String name,ArrayList<Currency> currencies) {
        this.date = date;
        this.name = name;
        this.currencies =currencies;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(ArrayList<Currency> currencies) {
        this.currencies = currencies;
    }
}
