package org.example;

public class RingBufferIsEmptyException extends RuntimeException{

    public RingBufferIsEmptyException() {
        super("Ring buffer is empty! Can't get object:");
    }
}
