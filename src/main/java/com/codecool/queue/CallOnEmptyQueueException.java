package com.codecool.queue;

public class CallOnEmptyQueueException extends Exception {
    public CallOnEmptyQueueException(String s) {
        super(s);
    }
}
