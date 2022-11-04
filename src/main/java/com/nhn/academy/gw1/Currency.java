package com.nhn.academy.gw1;

public enum Currency {
    WON("won",1),
    DOLLAR("dollar", 1000.0);

    private final String type;
    private final double rate;
    Currency(String type, double rate) {
        this.type = type;
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public double getRate() {
        return rate;
    }
}
