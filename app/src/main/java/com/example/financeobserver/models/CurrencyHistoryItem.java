package com.example.financeobserver.models;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "Record", strict = false)
public class CurrencyHistoryItem {

    @Attribute(name = "Date")
    private String date;

    @Attribute(name = "Id")
    private String id;

    @Element(name = "Nominal")
    private int nominal;

    @Element(name = "Value")
    private String value;

    public CurrencyHistoryItem(){

    }

    public CurrencyHistoryItem(String date, String id, int nominal, String value) {
        this.date = date;
        this.id = id;
        this.nominal = nominal;
        this.value = value;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
