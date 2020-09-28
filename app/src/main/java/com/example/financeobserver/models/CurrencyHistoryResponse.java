package com.example.financeobserver.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root
public class CurrencyHistoryResponse {

    @Attribute(name = "ID")
    private String id;

    @Attribute(name = "DateRange1")
    private String firstDate;

    @Attribute(name = "DateRange2")
    private String secondDate;



    @Attribute(name = "name")
    private String name;

    @ElementList(inline = true, required = false)
    private ArrayList<CurrencyHistoryItem> data= new ArrayList<>();

    public CurrencyHistoryResponse(){

    }

    public CurrencyHistoryResponse(String id, String firstDate, String secondDate, String name, ArrayList<CurrencyHistoryItem> data) {
        this.id = id;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
        this.name= name;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public String getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(String secondDate) {
        this.secondDate = secondDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CurrencyHistoryItem> getData() {
        return data;
    }

    public void setData(ArrayList<CurrencyHistoryItem> data) {
        this.data = data;
    }

}
