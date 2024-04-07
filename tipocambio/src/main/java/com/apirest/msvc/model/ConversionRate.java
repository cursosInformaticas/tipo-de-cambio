package com.apirest.msvc.model;

public class ConversionRate {
    private String currency;
    private Double rate;

    public ConversionRate(String currency, Double rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
