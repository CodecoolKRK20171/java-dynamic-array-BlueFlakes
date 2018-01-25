package com.codecool.LinkedList;

import com.codecool.Node;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> last;
    private int length;

    // head / tail / length / add / insert / remove / iterate

    public void add(T data) {
        if (isFirstCall()) {
            initialize(data);

        } else {
            Node<T> newLastNode = new Node<>(data);
            this.last.setNext(newLastNode);
            this.last = newLastNode;
        }

        increaseLength();
    }

    private boolean isFirstCall() {
        return this.head == null;
    }

    private void initialize(T data) {
        this.head = new Node<>(data);
        this.last = this.head;
    }

    private void increaseLength() {
        this.length++;
    }

    private void decreaseLength() {
        this.length--;
    }

    private int lastFilledIndex() {
        return this.length - 1;
    }

    public void insert(int index, T data) {
        if (index > lastFilledIndex()) {
            add(data);
            return;
        } else if (index >= 0) {
            insertBetweenBounds(index, data);
            return;
        }

        throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }

    private void insertBetweenBounds(int index, T data) {
        Node<T> currentNode = this.head;

        for (int i = 1; i < index && currentNode != null; i++) {
            currentNode = currentNode.getNext();
        }

        Node<T> newNode = new Node<>(data);
        Node<T> nextNode = currentNode.getNext();
        currentNode.setNext(newNode);
        newNode.setNext(nextNode);
        increaseLength();
    }

    public void remove(int index) {
        validateStateForRemoveAndHandleIndexEqualsZeroCase(index);

        Node<T> currentNode = this.head;
        Node<T> previousNode = this.head;

        for (int i = 0; i < index && currentNode != null; i++) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        previousNode.setNext(currentNode.getNext());
        decreaseLength();
    }

    private void validateStateForRemoveAndHandleIndexEqualsZeroCase(int index) {
        if (index > lastFilledIndex()) {
            throw new IndexOutOfBoundsException();

        } else if (index == 0) {
            this.head = this.head.getNext();
        }
    }

    public boolean isEmpty() {
        return this.length != 0;
    }

    public void iterate() {
        // impl Iterator<T>
    }

    //region setters and getters
    public Node<T> getHead( ) {
        return this.head;
    }

    public Node<T> getTail( ) {
        return this.last;
    }

    public int length( ) {
        return this.length;
    }
    //endregion
}
