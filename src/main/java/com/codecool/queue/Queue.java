package com.codecool.queue;

import com.codecool.Node;

public class Queue<T> {
    private Node<T> head;
    private Node<T> last;
    private int length;

    public void enqueue(T data) {
        if (isFirstCall()) {
            initialize(data);

        } else {
            Node<T> newNode = new Node<>(data);
            this.last.setNext(newNode);
            this.last = newNode;
        }

        this.length++;
    }

    private boolean isFirstCall() {
        return this.head == null;
    }

    private void initialize(T data) {
        Node<T> startNode = new Node<>(data);
        this.head = startNode;
        this.last = startNode;
        this.length++;
    }

    private void protectFromActionOnEmptyQueue() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Illegal action on empty stack.");
        }
    }

    public T dequeue() {
        protectFromActionOnEmptyQueue();

        try {
            return this.head.getData();
        } finally {
            this.head = head.getNext();
            this.length--;
        }
    }

    public T peek() {
        protectFromActionOnEmptyQueue();
        return this.head.getData();
    }

    public int size() {
        return this.length;
    }

    public boolean isEmpty() {
        return this.length == 0;
    }
}