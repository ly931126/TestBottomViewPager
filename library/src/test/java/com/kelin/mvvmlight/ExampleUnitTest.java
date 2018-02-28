package com.kelin.mvvmlight;

import org.junit.Test;

import java.util.concurrent.CompletionService;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        System.out.println("addition_isCorrect start");
        Account account = new Account("123456", 1000);
//        DrawMoneyRunnable drawMoneyRunnable = new DrawMoneyRunnable(account, 700);
//        Thread myThread1 = new Thread(drawMoneyRunnable);
//        Thread myThread2 = new Thread(drawMoneyRunnable);
        Thread myThread1 = new MyThread(account, 700);
        Thread myThread2 = new MyThread(account, 700);
        myThread1.start();
        myThread2.start();
        myThread1.join();
        myThread2.join();
        System.out.println("addition_isCorrect end");
    }

    class DrawMoneyRunnable implements Runnable {

        private Account account;
        private double drawAmount;

        public DrawMoneyRunnable(Account account, double drawAmount) {
            super();
            this.account = account;
            this.drawAmount = drawAmount;
        }

        public void run() {
            if (account.getBalance() >= drawAmount) {  //1
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("取钱成功， 取出钱数为：" + drawAmount);
                double balance = account.getBalance() - drawAmount;
                account.setBalance(balance);
                System.out.println("余额为：" + balance);
            }
        }
    }

    class MyThread extends Thread{
        private Account account;
        private double drawAmount;

        public MyThread(Account account, double drawAmount) {
            this.account = account;
            this.drawAmount = drawAmount;
        }

        public void run() {
            synchronized (this){
                if (account.getBalance() >= drawAmount) {  //1
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("取钱成功， 取出钱数为：" + drawAmount);
                    double balance = account.getBalance() - drawAmount;
                    account.setBalance(balance);
                    System.out.println("余额为：" + balance);
                }
            }
        }
    }

    class Account {

        private String accountNo;
        private double balance;

        public Account() {

        }

        public Account(String accountNo, double balance) {
            this.accountNo = accountNo;
            this.balance = balance;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(String accountNo) {
            this.accountNo = accountNo;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

    }
}