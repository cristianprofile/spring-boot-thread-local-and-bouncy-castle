package com.example.bouncycastle.threadlocal;

public class MyThreadLocal {


    public static final ThreadLocal<String> userThreadLocal = new ThreadLocal<String> ();

    public static void startTransaction(String generatedId) {
        //logic to start a transaction
        //...
        userThreadLocal.set(generatedId);
    }

    public static String getTransactionId() {
        return userThreadLocal.get();
    }

    public static void endTransaction() {
        //logic to end a transaction
        //…
        userThreadLocal.remove();
    }
}

