package com.codecool.LinkedList;

import com.codecool.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    private LinkedList<Integer> linkedList;

    @BeforeEach
    void setFreshInstance() {
        this.linkedList = new LinkedList<>();
    }

    @Test
    void testAddMethodByCountingElementsInCollection() {
        final int expectedAmount = 10;
        fillLinkedListWithElements(expectedAmount);

        int numberOfItemsFound = countElementsInCollection(linkedList);

        assertEquals(expectedAmount, numberOfItemsFound);
    }

    private void fillLinkedListWithElements(int expectedAmount) {
        for (Integer i = 0; i < expectedAmount; i++) {
            linkedList.add(i);
        }
    }

    private <T> int countElementsInCollection(LinkedList<T> linkedList) {
        Node<T> headNode = linkedList.getHead();
        int counter = 0;

        while (headNode != null) {
            counter++;
            headNode = headNode.getNext();
        }

        return counter;
    }

    @Test
    void insertShouldAddElementToCollection() {
        this.linkedList.insert(0, 100);
        int numberOfItemsFound = countElementsInCollection(linkedList);

        assertEquals(1, numberOfItemsFound);
    }

    @Test
    void outOfUpperBoundInsertShouldAddAtEnd() {
        fillLinkedListWithElements(5);

        int outOfUpperBoundIndex = 1000;
        Integer randomChosenNumber = 314;

        this.linkedList.insert(outOfUpperBoundIndex, randomChosenNumber);
        Node<Integer> headNode = this.linkedList.getHead();
        Integer lastNodeValue = null;

        while (headNode.getNext() != null) {
            headNode = headNode.getNext();
            lastNodeValue = headNode.getData();
        }

        assertEquals(randomChosenNumber, lastNodeValue);
    }

    @Test
    void shouldInsertBetweenBounds() {
        fillLinkedListWithElements(10);

        int inBounds = 3;
        Integer randomChosenNumber = 314;

        this.linkedList.insert(inBounds, randomChosenNumber);
        Node<Integer> headNode = this.linkedList.getHead();
        Integer lastNodeValue = null;
        int counter = 0;

        while (headNode.getNext() != null) {
            lastNodeValue = headNode.getData();
            headNode = headNode.getNext();

            if (counter == inBounds)
                break;

            counter++;
        }

        assertEquals(randomChosenNumber, lastNodeValue);
    }

    @Test
    void shouldRemoveOneElement() {
        int itemsAmount = 5;

        fillLinkedListWithElements(itemsAmount);
        this.linkedList.remove(0);

        int expectedLengthAfterRemove = itemsAmount - 1;
        int actualLength = countElementsInCollection(this.linkedList);

        assertEquals(expectedLengthAfterRemove, actualLength);
    }

    @Test
    void shouldContainAny() {
        this.linkedList.insert(0,1);
        assertTrue(this.linkedList.length() != 0);
    }
}