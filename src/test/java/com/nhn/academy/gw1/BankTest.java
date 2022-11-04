package com.nhn.academy.gw1;

import com.nhn.academy.gw1.Bank;
import com.nhn.academy.gw1.CUBank;
import com.nhn.academy.gw1.Currency;
import com.nhn.academy.gw1.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;

class BankTest {
    Bank cuBank;
    Money korMoney;
    Money usaMoney;

    @BeforeEach
    void initMoney() {
        cuBank = new CUBank();
        korMoney = new Money(Currency.WON, 1006L);
        usaMoney = new Money(Currency.DOLLAR, 5.255);
}

    @Test
    @DisplayName("환전 테스트")
    void exchange_test() {
        Money exchangedUsaMoney = cuBank.exchange(korMoney);
        assertThat(exchangedUsaMoney).isEqualTo(new Money(Currency.DOLLAR,1.01));

        Money exchangedKorMoney = cuBank.exchange(usaMoney);
        assertThat(exchangedKorMoney).isEqualTo(new Money(Currency.WON,5260));
    }

    @Test
    @DisplayName("반올림 테스트")
    void rounding_off_money_test() {
        double korMoneyResult = cuBank.roundingOffMoney(korMoney).getAmount();
        double usaMoneyResult = cuBank.roundingOffMoney(usaMoney).getAmount();

        assertThat(korMoneyResult).isEqualTo(1010L);
        assertThat(usaMoneyResult).isEqualTo(5.26);
    }

    @Test
    @DisplayName("달러인지 확인")
    void is_dollar_test() {
        assertThat(cuBank.isDollar(korMoney)).isFalse();
        assertThat(cuBank.isDollar(usaMoney)).isTrue();
    }
}