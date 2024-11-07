package org.example;

public class RingBufferIsFullException extends RuntimeException{

    public RingBufferIsFullException(Object o) {
        super("Ring buffer is full! Can't put object: " + o);
    }
}
