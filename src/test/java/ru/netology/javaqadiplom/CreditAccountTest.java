package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    //Пополнение на 3000, изначальный баланс 0
    //Пополнение на 3000, изначальный баланс 0
    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertEquals(account.add(3_000), true);
        Assertions.assertEquals(3_000, account.getBalance());
    }

    //Пополнение на 0, изначальный баланс 6_000
    @Test
    public void shouldAddToBalanceZero() {
        CreditAccount account = new CreditAccount(
                6_000,
                5_000,
                15
        );

        Assertions.assertEquals(account.add(0), false);

        Assertions.assertEquals(6_000, account.getBalance());
    }

    //пополнение на отрицательное число, изначальный баланс 6_000
    @Test
    public void shouldAddToBalanceMinus() {
        CreditAccount account = new CreditAccount(
                6_000,
                5_000,
                15
        );

        Assertions.assertEquals(account.add(-100), false);

        Assertions.assertEquals(6_000, account.getBalance());
    }

    //Покупка на 5_000, если сумма изначально 6_000
    @Test
    public void shouldPayFromCardSumEqualPlus() {
        CreditAccount account = new CreditAccount(
                6_000,
                8_000,
                10
        );
        Assertions.assertEquals(account.pay(5_000), true);
        Assertions.assertEquals(1_000, account.getBalance());

    }

    //Суммы на балансе не хватает, но хватает лимита
    @Test
    public void shouldPayFromCardSumEqualMinus() {
        CreditAccount account = new CreditAccount(
                5_000,
                8_000,
                10
        );
        Assertions.assertEquals(account.pay(7_000), true);
        Assertions.assertEquals(-2_000, account.getBalance());
    }

    //Сумма на балансе и в лимите равна сумме покупки
    @Test
    public void shouldPayFromCardSumEqualZero() {
        CreditAccount account = new CreditAccount(
                5_000,
                8_000,
                10
        );
        Assertions.assertEquals(account.pay(13_000), true);
        Assertions.assertEquals(-8_000, account.getBalance());
    }

    //Сумма покупки больше, чем сумма на балансе и в лимите
    @Test
    public void shouldPayFromCardSumEqualFalse() {
        CreditAccount account = new CreditAccount(
                5_000,
                8_000,
                10
        );
        Assertions.assertEquals(account.pay(13_001), false);
        Assertions.assertEquals(5_000, account.getBalance());
    }

    //Сумма покупки равна 0
    @Test
    public void shouldPayFromCardSumEqualFalse2() {
        CreditAccount account = new CreditAccount(
                5_000,
                8_000,
                10
        );
        Assertions.assertEquals(account.pay(0), false);
        Assertions.assertEquals(5_000, account.getBalance());
    }

    //Исключение для процентной ставки
    @Test
    public void ThrowExceptionForRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    5_000,
                    8_000,
                    -10
            );
        });
    }

    //Исключение для баланса
    @Test
    public void ThrowExceptionForInitialBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    -15_000,
                    8_000,
                    10
            );
        });
    }

    //Исключение для лимита
    @Test
    public void ThrowExceptionForCreditLimit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    5_000,
                    -8_000,
                    10
            );
        });
    }

    //Баланс -200, сумма процентов -30
    @Test
    public void ShouldBalanceEqualsMinus() {
        CreditAccount account = new CreditAccount(
                -200,
                5_000,
                15
        );
        int expected = -30;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }

    //Баланс 200, сумма процентов 0
    @Test
    public void ShouldBalanceEqualsPlus() {
        CreditAccount account = new CreditAccount(
                200,
                5_000,
                15
        );
        int expected = 0;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }

    //Баланс 0, сумма процентов 0
    @Test
    public void ShouldBalanceEqualsZero() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        int expected = 0;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }
}
