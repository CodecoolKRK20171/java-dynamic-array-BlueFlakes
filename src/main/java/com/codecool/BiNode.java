package com.codecool;

public class BiNode<T> {
    private T data;
    private BiNode<T> previousNode;
    private BiNode<T> nextNode;

    //region constructor with custom builders
    private BiNode(T data, BiNode<T> previousNode, BiNode<T> nextNode) {
        this.data = data;
        this.previousNode = previousNode;
        this.nextNode = nextNode;
    }

    public static <T> BiNode<T> createWithPrevious(T data, BiNode<T> previous) {
        return new BiNode<>(data, previous, null);
    }

    public static <T> BiNode<T> createWithNext(T data, BiNode<T> nextNode) {
        return new BiNode<>(data, null, nextNode);
    }

    public static <T> BiNode<T> createWithBothSides(T data, BiNode<T> previous, BiNode<T> nextNode) {
        return new BiNode<>(data, previous, nextNode);
    }
    //endregion
    //region setters and getters

    public T getData( ) {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BiNode<T> getPreviousNode( ) {
        return previousNode;
    }

    public void setPreviousNode(BiNode<T> previousNode) {
        this.previousNode = previousNode;
    }

    public BiNode<T> getNextNode( ) {
        return nextNode;
    }

    public void setNextNode(BiNode<T> nextNode) {
        this.nextNode = nextNode;
    }

    //endregion
}
