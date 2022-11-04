package com.nhn.academy.gw1;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Getter
@Slf4j
public class Money {
    double amount;
    Currency currency;

    public Money(Currency currency, double amount) {
        this.currency = currency; //100.5
        this.amount = Math.round(amount*100)/100.0; //1.01
    }

    public Money(Currency currency, long amount) {
        this.currency = currency;
        this.amount = amount;
    }

    private boolean isCurrencyKor(Money money) {
        return money.getCurrency().equals(Currency.WON);
    }

    public double addMoney(Money money) {
        if (!isSameCurrency(money)) {
            throw new DifferentTypeException();
        }
        return this.amount + money.amount;
    }

    public double subMoney(Money money) {
        double result = this.amount - money.amount;
        if(result < 0) {
            throw new IllegalAmountException("amount can't be negative");
        }
        if(isCurrencyKor(money)) {
            return (long) result;
        }
        return result;
    }

    public boolean isSameCurrency(Money money) {
        return this.getCurrency().equals(money.getCurrency());
    }

    public boolean isPositiveAmount() {
        return this.getAmount() > 0;
    }

    public void checkNegativeAmount() {
        if(!isPositiveAmount()) {
            throw new IllegalArgumentException("Money can't be negative!!!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount && currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }


}
