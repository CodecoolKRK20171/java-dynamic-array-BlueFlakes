package com.codecool.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    private Queue<Integer> queue;

    @BeforeEach
    void setNewQueue() {
        this.queue = new Queue<>();
    }

    @Test
    void shouldThrowIfCallDequeueOnEmpty() {
        assertThrows(CallOnEmptyQueueException.class, () -> this.queue.dequeue());
    }

    @Test
    void shouldThrowIfCallPeekOnEmpty() {
        assertThrows(CallOnEmptyQueueException.class, () -> this.queue.peek());
    }

    @Test
    void shouldReturnOutputInSameOrderAsInput() throws CallOnEmptyQueueException {
        List<Integer> values = Arrays.asList(1,2,3,4,5);

        values.forEach(v -> this.queue.enqueue(v));
        List<Integer> returnedValues = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {
            returnedValues.add(this.queue.dequeue());
        }

        boolean isOrderTheSame = IntStream.range(0, 5).allMatch(idx -> {
            Integer fromValues = values.get(idx);
            Integer fromQueue = returnedValues.get(idx);
            return fromValues.equals(fromQueue);
        });

        assertTrue(isOrderTheSame);
    }

    @Test
    void peekShouldAlwaysReturnFirstItem() throws CallOnEmptyQueueException {
        Integer firstNumber = 5;
        int randomRange = 0;
        List<Integer> peek = new ArrayList<>();

        for (Integer i = firstNumber; i >= randomRange; i--) {
            this.queue.enqueue(i);
            peek.add(this.queue.peek());
        }

        assertTrue(peek.stream()
                       .allMatch(n -> n.equals(firstNumber)));
    }

    @Test
    void peekShouldNotAffectItemsAmount() throws CallOnEmptyQueueException {
        assertFalse(doPeekAffectNumberOfItems());
    }

    private boolean doPeekAffectNumberOfItems() {
        int randomRange = 5;

        for (int i = 0; i < randomRange; i++) {
            this.queue.enqueue(i);
        }

        int outOfQueueSize = randomRange * 2;

        for (int i = 0; i < outOfQueueSize; i++) {
            try {
                this.queue.peek();
            } catch (Exception e) {
                return true;
            }
        }

        return false;
    }
}