package com.nhn.academy.gw1;

public interface Bank {
    Money exchange(Money money);
    Money roundingOffMoney(Money money);
    Boolean isDollar(Money money);
}
