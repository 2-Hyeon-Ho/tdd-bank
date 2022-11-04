package com.nhn.academy.gw1;

import com.nhn.academy.gw1.Currency;
import com.nhn.academy.gw1.DifferentTypeException;
import com.nhn.academy.gw1.IllegalAmountException;
import com.nhn.academy.gw1.Money;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {
    Money korMoney;
    Money usaMoney;
    Money negativeKorMoney;
    Money negativeUsaMoney;

    @BeforeEach
    void initMoney() {
        korMoney = new Money(Currency.WON, 1000L);
        usaMoney = new Money(Currency.DOLLAR, 1);
        negativeKorMoney = new Money(Currency.WON, -1000L);
        negativeUsaMoney = new Money(Currency.DOLLAR, -1);
    }

    @Test
    @DisplayName("돈을 더하고 결과를 테스트")
    void add_money_test() {
        assertThat(korMoney.addMoney(korMoney)).isEqualTo(2000);
        assertThat(usaMoney.addMoney(usaMoney)).isEqualTo(2.0);

        assertThatThrownBy(() -> korMoney.addMoney(usaMoney))
                .isInstanceOf(DifferentTypeException.class)
                .hasMessageContaining("Type is different");
    }

    @Test
    @DisplayName("2000원과 2000원은 같다 => 서로다른 객체의 amount가 같으면 같다?!")
    void money_type_check_test() {
        assertThat(korMoney.isSameCurrency(usaMoney)).isFalse();
        assertThat(korMoney.isSameCurrency(korMoney)).isTrue();
        assertThat(usaMoney.isSameCurrency(usaMoney)).isTrue();
    }

    @Test
    @DisplayName("돈이 음수인지 양수인지 확인")
    void check_negative_number_test() {

        assertThatThrownBy(() -> negativeKorMoney.checkNegativeAmount())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Money can't be negative");

    }

    @Test
    @DisplayName("돈을 뺐는데 음수가 나왔다...")
    void subMoney_test() {
        assertThatThrownBy(() -> korMoney.subMoney(new Money(Currency.WON, 1001)))
                .isInstanceOf(IllegalAmountException.class)
                .hasMessageContaining("amount can't be negative");

        Assertions.assertDoesNotThrow(() -> korMoney.subMoney(korMoney));
    }

    @Test
    @DisplayName("소수점 나타내기")
    void decimalPoint_test() {
        Money usaMoney = new Money(Currency.DOLLAR, 5.153);
        Money usaMoney2 = new Money(Currency.DOLLAR, 5.148);

        assertThat(usaMoney.addMoney(usaMoney2)).isEqualTo(10.30);
    }
}