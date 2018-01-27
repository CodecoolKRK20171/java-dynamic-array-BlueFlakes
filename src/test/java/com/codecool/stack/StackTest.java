package com.codecool.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {


    private Stack<Integer> stack;

    @BeforeEach
    void setFreshInstance() throws ImproperArgumentException {
        this.stack = new Stack<>(10);
    }

    @Test
    void shouldPullItemsInLIFOOrder() throws StackExceededCapacityException,
            CallOnEmptyStackException {

        List<Integer> initialValues = Arrays.asList(1,2,3,4,5);
        int valuesAmount = initialValues.size();

        for (Integer value : initialValues) {
            this.stack.push(value);
        }

        List<Integer> returnedValues = new ArrayList<>();
        for (int i = 0; i < valuesAmount; i++) {
            returnedValues.add(this.stack.pop());
        }

        Collections.reverse(returnedValues);
        boolean isOrderReversed = IntStream.range(0, valuesAmount).allMatch(idx -> {
            Integer fromInitialValues = initialValues.get(idx);
            Integer fromStack = returnedValues.get(idx);
            return fromInitialValues.equals(fromStack);
        });

        assertTrue(isOrderReversed);
    }

    @Test
    void shouldThrowIfInitialCapacityIsLowerThanOne() {
        assertThrows(ImproperArgumentException.class, () -> new Stack<>(0));
    }

    @Test
    void shouldThrowIfCallPopOnEmpty() {
        assertThrows(CallOnEmptyStackException.class, () -> this.stack.pop());
    }

    @Test
    void shouldThrowIfCallPeekOnEmpty() {
        assertThrows(CallOnEmptyStackException.class, () -> this.stack.peek());
    }
}