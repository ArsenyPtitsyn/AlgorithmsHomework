package ru.geekbrains.algorithms.lesson_d.ivan;

import java.util.Iterator;
import java.util.function.Consumer;

public interface MyIterator<T> extends Iterator {
    void reset();
    boolean atEnd();
    boolean atBegin();
    T deleteCurrent();
    void insertAfter(T c);
    void insertBefore(T c);
    T getCurrent();
}
