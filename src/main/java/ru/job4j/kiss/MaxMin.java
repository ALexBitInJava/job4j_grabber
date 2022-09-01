package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return search(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return search(value, comparator.reversed());
    }

    private <T> T search(List<T> value, Comparator<T> comparator) {
        if (value.isEmpty()) {
            return null;
        }
        T result = value.get(0);
        for (T v : value) {
            result = comparator.compare(result, v) > 0 ? result : v;
        }
        return result;
    }
}