package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.javaqadiplom.Account;
import ru.netology.javaqadiplom.Bank;
import ru.netology.javaqadiplom.SavingAccount;

import java.awt.datatransfer.Transferable;

public class BankTest {
    @Test
    public void TransferTest() {
        Bank bank = new Bank();
        CreditAccount creditAccount = new CreditAccount(
                0,
                5_000,
                15
        );
        SavingAccount savingAccount = new SavingAccount(
                1000,
                1_00,
                10_000,
                0
        );
        Assertions.assertEquals(bank.transfer(savingAccount, creditAccount, 500), true);
        Assertions.assertEquals(creditAccount.getBalance(), savingAccount.getBalance());
    }
    @Test
    public void TransferTestIfCreditLimitLess() {
        Bank bank = new Bank();
        CreditAccount creditAccount = new CreditAccount(
                0,
                1_00,
                15
        );
        SavingAccount savingAccount = new SavingAccount(
                1000,
                1_00,
                10_000,
                0
        );
        Assertions.assertEquals(bank.transfer(creditAccount, savingAccount, 500), false);
        Assertions.assertEquals(savingAccount.getBalance(),1000);
        Assertions.assertEquals(creditAccount.getBalance(),0);
    }
    @Test
    public void TransferTestIfSavingLimitLess() {
        Bank bank = new Bank();
        CreditAccount creditAccount = new CreditAccount(
                2_000,
                1_000,
                15
        );
        SavingAccount savingAccount = new SavingAccount(
                1_000,
                1_00,
                10_000,
                0
        );
        Assertions.assertEquals(bank.transfer(savingAccount, creditAccount, 1000), false);
        Assertions.assertEquals(savingAccount.getBalance(),1_000);
        Assertions.assertEquals(creditAccount.getBalance(),2_000);
    }
    @Test
    public void TransferTestIfSavingLimitMore() {
        Bank bank = new Bank();
        CreditAccount creditAccount = new CreditAccount(
                20_000,
                1_000,
                15
        );
        SavingAccount savingAccount = new SavingAccount(
                1_000,
                1_00,
                10_000,
                0
        );
        Assertions.assertEquals(bank.transfer(creditAccount, savingAccount, 19_000), false);
        Assertions.assertEquals(savingAccount.getBalance(),1_000);
        Assertions.assertEquals(creditAccount.getBalance(),20_000);
    }
    @Test
    public void TransferTestFromCreditLimitToCreditLimit() {
        Bank bank = new Bank();
        CreditAccount creditAccount1 = new CreditAccount(
                2_000,
                1_000,
                15
        );
        CreditAccount creditAccount2 = new CreditAccount(
                1_000,
                1_000,
                15
        );
        Assertions.assertEquals(bank.transfer(creditAccount1, creditAccount2, 1_000), true);
        Assertions.assertEquals(creditAccount1.getBalance(),1_000);
        Assertions.assertEquals(creditAccount2.getBalance(),2_000);
    }
    @Test
    public void TransferTestFromCreditLimitToCreditLimitMore() {
        Bank bank = new Bank();
        CreditAccount creditAccount1 = new CreditAccount(
                2_000,
                1_000,
                15
        );
        CreditAccount creditAccount2 = new CreditAccount(
                1_000,
                1_000,
                15
        );
        Assertions.assertEquals(bank.transfer(creditAccount1, creditAccount2, 4_000), false);
        Assertions.assertEquals(creditAccount1.getBalance(),2_000);
        Assertions.assertEquals(creditAccount2.getBalance(),1_000);
    }
    @Test
    public void TransferTestFromSavingToSaving() {
        Bank bank = new Bank();
        SavingAccount savingAccount1 = new SavingAccount(
                2_000,
                1_000,
                10_000,
                0
        );
        SavingAccount savingAccount2 = new SavingAccount(
                1_000,
                1_000,
                10_000,
                0
        );
        Assertions.assertEquals(bank.transfer(savingAccount1, savingAccount2, 1_000), true);
        Assertions.assertEquals(savingAccount1.getBalance(),1_000);
        Assertions.assertEquals(savingAccount2.getBalance(),2_000);
    }
    @Test
    public void TransferTestFromSavingToSavingLimitLess() {
        Bank bank = new Bank();
        SavingAccount savingAccount1 = new SavingAccount(
                2_000,
                1_000,
                10_000,
                0
        );
        SavingAccount savingAccount2 = new SavingAccount(
                1_000,
                1_000,
                10_000,
                0
        );
        Assertions.assertEquals(bank.transfer(savingAccount1, savingAccount2, 4_000), false);
        Assertions.assertEquals(savingAccount1.getBalance(),2_000);
        Assertions.assertEquals(savingAccount2.getBalance(),1_000);
    }
}
