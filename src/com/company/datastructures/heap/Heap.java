package com.company.datastructures.heap;

import java.util.Arrays;

public class Heap {
    private int capacity = 16;
    private int size = 0;
    private String name;

    private int[] array = new int[capacity];

    public void add(int data) {
        ensureCapacity();
        array[size++] = data;
        trickleUp(size-1);
    }

    public int peak() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        int result = array[0];
        array[0] = array[size-1];
        array[--size] = 0;
        trickleDown();
        
        return result;
    }

    private void trickleDown() {
       trickleDown(0, size-1);
    }

    private void trickleDown(int index, int last) {
        if (index >= last) {
            return;
        }
        int left = 2*index + 1;
        int right = 2*index + 2;
        if (left <= last && array[index] < array[left]) {
            swap(index, left);
            trickleDown(left, last);
        }
        if (right <= last && array[index] < array[right]) {
            swap(index, right);
            trickleDown(right, last);
        }
    }

    private int max(int left, int right) {
        return array[left] > array[right] ? array[left] : array[right];
    }

    private void ensureCapacity() {
        if(size >= capacity) {
            array = Arrays.copyOf(array, 2*capacity);
            capacity *= 2;
        }
    }

    private void trickleUp(int index) {
        if (index <= 0) {
            return;
        }
        int parent = (index-1)/2;
        if (array[parent] < array[index]) {
            swap(index, parent);
            trickleUp(parent);
        }
    }

    private void swap(int from, int to) {
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }

    public void sort() {
        int length = size-1;
        for (int i = length; i >= 0; i--) {
            swap(0, i);
            length--;
            trickleDown(0, length);
        }
    }

    public void printHeap() {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.add(55);
        heap.add(17);
        heap.add(12);
        heap.add(8);
        heap.add(67);
        heap.add(7);
        heap.add(57);
        heap.add(4);
        heap.add(60);
        heap.printHeap();

        heap.sort();
        heap.printHeap();

        heap.peak();
        heap.peak();
        heap.peak();
        heap.peak();
        heap.peak();
        heap.printHeap();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Heap heap = (Heap) o;

        if (capacity != heap.capacity) return false;
        if (size != heap.size) return false;
        return name != null ? name.equals(heap.name) : heap.name == null;

    }

    @Override
    public int hashCode() {
        int result = capacity;
        result = 31 * result + size;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
