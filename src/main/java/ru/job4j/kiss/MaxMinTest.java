package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaxMinTest {

    @Test
    void empty() {
        List<Integer> list = new ArrayList<>();
        assertNull(new MaxMin().max(list, Comparator.naturalOrder()));
        assertNull(new MaxMin().min(list, Comparator.naturalOrder()));
    }

    @Test
    void max() {
        List<Integer> list = List.of(1, 4, 2, 10, 15, 6, 3, 8);
        int expected = 15;
        int result = new MaxMin().max(list, Comparator.naturalOrder());
        assertEquals(expected, result);
    }

    @Test
    void min() {
        List<Integer> list = List.of(10, 10, 12, 11, 13, 9, 4, 5, 1);
        int expected = 1;
        int result = new MaxMin().min(list, Comparator.naturalOrder());
        assertEquals(expected, result);
    }
}