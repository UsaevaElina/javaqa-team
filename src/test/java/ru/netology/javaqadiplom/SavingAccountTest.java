package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test // исключение для процентной ставки
    public void NegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    1_000,
                    10_000,
                    -5
            );
        });
    }

    @Test // исключение когда минимальный баланс больше максимального баланса
    public void MinBalanceMoreMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    10_000,
                    1_000,
                    5
            );
        });
    }

    @Test // исключение когда минимальный баланс меньше 0
    public void NegativeMinBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    -1_000,
                    10_000,
                    5
            );
        });
    }

    @Test // исключение когда начальный баланс больше максимального
    public void InitialBalanceOutOfMaxTolerance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    1_000,
                    1_500,
                    5
            );
        });
    }

    @Test // исключение когда начальный баланс меньше минимального баланса
    public void InitialBalanceOutOfMinTolerance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    1_000,
                    2_000,
                    10_000,
                    5
            );
        });
    }

    @Test //  добавляем к сумме на балансе 3_000 и должны получить сумму текущего баланса и  пополнения
    public void AddLessAmountAdd() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test // добавляем к сумме 0 и должны получить текущий баланс
    public void shouldAddLessZeroAdd() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

//        account.add(0);

        Assertions.assertFalse(account.add(0));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test // добавляем к текущему балансу сумму, чтобы выйти за максимальный баланс
    public void shouldAddLessThanMaxBalanceAdd() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertFalse(account.add(10_000));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test // делаем покупку с суммой меньше/равно 0
    public void AmountZeroPay() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertFalse(account.pay(0));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test // делаем покупку с такой суммой, чтобы оставшийся баланс был меньше минимального
    public void shouldAddLessThanMinBalancePay() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertFalse(account.pay(1_500));
        Assertions.assertEquals(2_000, account.getBalance());
    }


    @Test // совершаем удачную покупку
    public void GoodBuy() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertTrue(account.pay(500));
        Assertions.assertEquals(1500, account.getBalance());
    }


    @Test  // устанавливаем процентную ставку, чтобы получилось ровное число
    public void YearChangesIntegral() {
        SavingAccount account = new SavingAccount(
                2_00,
                1_00,
                10_000,
                15
        );

        Assertions.assertEquals(30, account.yearChange());
    }

    @Test  // устанавливаем такую цену, чтобы получилось неровное число. Проверяем округление до целого
    public void YearChangesFloat() {
        SavingAccount account = new SavingAccount(
                1_50,
                1_00,
                10_000,
                15
        );

        Assertions.assertEquals(15, account.yearChange());
    }

    @Test  // устанавляваем процентную ставку 0
    public void YearChangesZeroRate() {
        SavingAccount account = new SavingAccount(
                1000,
                1_00,
                10_000,
                0
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test  // устанавляваем сумму на балансе 0
    public void YearChangesZeroBalance() {
        SavingAccount account = new SavingAccount(
                1000,
                1_00,
                10_000,
                0
        );

        Assertions.assertEquals(0, account.yearChange());
    }
}
