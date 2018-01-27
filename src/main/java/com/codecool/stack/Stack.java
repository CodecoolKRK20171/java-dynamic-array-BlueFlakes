package com.codecool.stack;

import com.codecool.Node;

public class Stack<T> {
    private Node<T> head;
    private int length = 0;
    private int capacity;


    public Stack(int capacity) throws ImproperArgumentException {
        if (capacity < 1)
            throw new ImproperArgumentException("Capacity should be positive number");

        this.capacity = capacity;
    }

    public T pop() throws CallOnEmptyStackException {
        protectFromActionOnEmptyStack();

        try {
            return this.head.getData();

        } finally {
            this.head = this.head.getNext();
            this.length--;
        }
    }

    private boolean isCapacityExceeded() {
        int nextIndex = this.length + 1;
        return nextIndex > this.capacity;
    }


    private void protectFromActionOnEmptyStack() throws CallOnEmptyStackException {
        if (this.isEmpty()) {
            throw new CallOnEmptyStackException("Illegal action on empty stack.");
        }
    }

    public void push(T data) throws StackExceededCapacityException {
        if (isCapacityExceeded())
            throw new StackExceededCapacityException("Tried to push into full stack.");

        this.head = new Node<>(data, this.head);
        this.length++;
    }

    public T peek() throws CallOnEmptyStackException {
        protectFromActionOnEmptyStack();
        return this.head.getData();
    }

    public boolean isEmpty() {
        return this.length == 0;
    }
}
