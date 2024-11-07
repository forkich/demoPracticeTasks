package org.example;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RingBufferTest {

    private static final int MAX_SIZE = 3;
    private RingBuffer buffer;

    @BeforeEach
    void beforeEach() {
        buffer = new RingBuffer(MAX_SIZE);
    }

    @Test
    void tryGetItemWhenBufferIsEmptyThenThrowException() {
        assertThrows(RingBufferIsEmptyException.class, () -> buffer.get());
    }

    @Test
    void putItemWhenBufferIsNotFullThenGetItem() {
        buffer.put(5);

        assertEquals(buffer.get(), 5);
    }

    @Test
    void putItemWhenBufferIsNotEmptyThenGetItemInCorrectOrder() {
        initializeBuffer(List.of(5, 4, 3));

        assertEquals(buffer.get(), 5);
        assertEquals(buffer.get(), 4);
        assertEquals(buffer.get(), 3);
    }

    @Test
    void tryPutItemWhenBufferIsFullThenThrowsException() {
        initializeBuffer(List.of(5, 4, 3));

        assertThrows(RingBufferIsFullException.class, () -> buffer.put(6));
    }

    @Test
    void getItemWhenBufferHasBeenRePutThenFifoResultCorrect() {
        initializeBuffer(List.of(5, 4, 3));
        buffer.get();
        buffer.put(6);

        assertEquals(buffer.get(),4);
    }

    private void initializeBuffer(List<Integer> items) {
        items.forEach(
                it -> buffer.put(it)
        );
    }
}