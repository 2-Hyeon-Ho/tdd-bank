package com.nhn.academy.gw1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CUBank implements Bank {
    @Override
    public Money exchange(Money money) {
        Money exchangedMoney;
        if (!isDollar(money)) { //원화 -> 달러
            double exchangedAmount = money.getAmount() / Currency.DOLLAR.getRate();
            exchangedMoney = new Money(Currency.DOLLAR, exchangedAmount);
        } else { // 달러 -> 원화
            long exchangedAmount = (long) (money.getAmount() * Currency.DOLLAR.getRate());
            exchangedMoney = roundingOffMoney(new Money(Currency.WON, exchangedAmount));
        }
        return exchangedMoney;
    }

    public Money roundingOffMoney(Money money) {
        if (money.getCurrency().equals(Currency.WON) && money.getAmount() % 10 >= 5) {
            return new Money(Currency.WON, (int)Math.round(money.getAmount()/10.0) * 10);
        }
            return new Money(money.getCurrency(), money.getAmount());
    }

    public Boolean isDollar(Money money) {
        return money.getCurrency().getType().equals("dollar");
    }

}
