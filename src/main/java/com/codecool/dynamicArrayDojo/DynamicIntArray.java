package com.codecool.dynamicArrayDojo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// put your code here!
public final class DynamicIntArray {
    private static final int defaultCapacity = 10;
    private static final int capacityFactor = 2;

    private int capacity;
    private int[] container;
    private int valuesAmount;

    public DynamicIntArray() {
        createContainerAndSetInitialCapacity(defaultCapacity);
    }

    public DynamicIntArray(int initialCapacity) {
        createContainerAndSetInitialCapacity(initialCapacity);
    }

    private void createContainerAndSetInitialCapacity(int initialCapacity) {
        this.container = new int[initialCapacity];
        this.capacity = initialCapacity;
    }

    public int length() {
        return this.valuesAmount;
    }

    public void add(int number) {
        handleTooSmallContainerCapacityPossibility();
        appendDeliveredValue(number);
        increaseValuesCount();
    }

    private void handleTooSmallContainerCapacityPossibility() {
        if (isContainerCapacityTooSmall()) {
            increaseContainerCapacity();
        }
    }

    private boolean isContainerCapacityTooSmall() {
        int nextIndex = this.valuesAmount + 1;
        return nextIndex > this.capacity;
    }

    private void increaseContainerCapacity() {
        int increasedCapacity = this.capacity * capacityFactor;

        this.container = Arrays.copyOf(this.container, increasedCapacity);
        this.capacity = increasedCapacity;
    }

    private void appendDeliveredValue(int number) {
        int nextIndex = this.valuesAmount;
        this.container[nextIndex] = number;
    }

    private void increaseValuesCount() {
        this.valuesAmount++;
    }
    
    public void remove(int index) {
        preventFromAccessingInvalidIndex(index);
        reduceIndexFromContainer(index);
        decreaseValuesCount();
    }

    public void replace(int index, int value) {
        preventFromAccessingInvalidIndex(index);
        this.container[index] = value;
    }

    public int get(int index) {
        preventFromAccessingInvalidIndex(index);
        return this.container[index];
    }

    private void preventFromAccessingInvalidIndex(int index) {
        if (isIndexOutOfRange(index)) {
            throw new ArrayIndexOutOfBoundsException("Index out of collection bounds");
        }
    }

    private boolean isIndexOutOfRange(int deliveredIndex) {
        return deliveredIndex < 0 || deliveredIndex > lastFilledIndex();
    }

    private void reduceIndexFromContainer(final int indexToRemove) {
        int[] temp = new int[this.capacity];

        DynamicIntArrayIterator iterator = new DynamicIntArrayIterator();
        int currentIndex = 0;

        do {
            if (currentIndex == indexToRemove) {
                iterator.skip(1);
            }

            if (iterator.hasNext()) {
                temp[currentIndex] = iterator.next();
                currentIndex++;
            }

        } while (iterator.hasNext());

        this.container = temp;
    }

    private void decreaseValuesCount( ) {
        this.valuesAmount--;
    }

    private int lastFilledIndex() {
        return this.valuesAmount - 1;
    }

    public void insert(int index, int number) {
        if (index > lastFilledIndex()) {
            add(number);
        } else {
            handleInBoundsInsert(index, number);
        }
    }

    private void handleInBoundsInsert(int index, int number) {
        handleTooSmallContainerCapacityPossibility();
        int[] temp = new int[this.capacity];

        Iterator<Integer> iterator = new DynamicIntArrayIterator();

        int currentIndex = 0;

        do {
            if (currentIndex == index) {
                temp[currentIndex] = number;
            } else {
                temp[currentIndex] = iterator.next();
            }

            currentIndex++;

        } while (iterator.hasNext());

        this.container = temp;
        increaseValuesCount();
    }

    private class DynamicIntArrayIterator implements Iterator<Integer> {
        private int index = 0;

        @Override
        public boolean hasNext( ) {
            return index < valuesAmount;
        }

        @Override
        public Integer next( ) {
            if (this.hasNext()) {
                return container[index++];
            }

            return null;
        }

        public void skip(int n) {
            this.index += n;
        }
    }

    
    // # TODO ~ Delegate toString to formatter service, make abstraction
    @Override
    public String toString( ) {
        String prefix = " ";
        String arrayAsString = getFormattedContentAsString();
        return prefix + arrayAsString;
    }
    
    private String getFormattedContentAsString() {
        int firstIndex = 0;

        return IntStream.range(firstIndex, this.valuesAmount)
                        .map(idx -> this.container[idx])
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" "));
    }
}
