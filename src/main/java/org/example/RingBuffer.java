package org.example;

/**
 * 1. FIFO
 * 2. Ring buffer, use all space in the buffer. Throw exception in corner cases:  no free space (on put) or data (on get)e.g.
 * created empty buffer with size = 3   _,_,_
 * get -> no data, buffer is empty (exception: non thread-save)
 * put 1 ->     1,_,_
 * put 2 ->     1,2,_
 * put 3 ->     1,2,3
 * put 4 -> buffer is full  (exception: non thread-save)
 * get -> 1     _,2,3
 * put 4 ->    4,2,3
 * get -> 2     4,_,3
 */

public class RingBuffer {
    private final Object[] data;
    private int currentSize;
    private int currentReadIndex;
    private int currentWriteIndex;
    public RingBuffer(int maxBufferSize) {
        currentSize = 0;
        data = new Object[maxBufferSize];
    }

    public void put(Object o) {
        if(isFull()) {
            throw new RingBufferIsFullException(o);
        } else {
            currentSize++;
            data[currentWriteIndex] = o;
            currentWriteIndex = incrementIndex(currentWriteIndex);
        }
    }

    public Object get() {
        if(!isEmpty()) {
            Object currentValue = data[currentReadIndex];
            currentReadIndex = incrementIndex(currentReadIndex);
            currentSize--;

            return currentValue;
        } else {
            throw new RingBufferIsEmptyException();
        }
    }

    private Boolean isEmpty() {
        return currentSize == 0;
    }

    private int incrementIndex(int index) {
        if (index + 1 == data.length) {
            return 0;
        } else {
            return ++index;
        }
    }

    private Boolean isFull() {
        return currentSize == data.length;
    }
}