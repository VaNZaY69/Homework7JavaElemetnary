package com.vanzay.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void forEach(List<T> arr, Consumer<T> elemProcessor) {
        for (T elem : arr) {
            elemProcessor.accept(elem);
        }
    }

    public static <T> List<T> filter(List<T> arr, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T elem : arr) {
            if (predicate.test(elem)) {
                result.add(elem);
            }
        }
        return result;
    }

    public static <T, R> R reduce(List<T> list, R initValue, BiFunction<R, T, R> f) {
        for (T elem : list) {
            initValue = f.apply(initValue, elem);
        }
        return initValue;
    }

    public static <T> boolean anyMatch(List<T> arr, Predicate<T> predicate) {
        for (T elem : arr) {
            if (predicate.test(elem)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean allMatch(List<T> arr, Predicate<T> predicate) {
        int result = 0;
        for (T elem : arr) {
            if (predicate.test(elem)) {
                result++;
            }
        }
        return result == arr.size();
    }

    public static <T> List<T> map(List<T> arr, Function<T, T> f) {
        List<T> result = new ArrayList<>();
        for (T elem : arr) {
            result.add(f.apply(elem));
        }
        return result;
    }
}
