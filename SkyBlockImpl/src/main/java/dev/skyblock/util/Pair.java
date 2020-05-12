package dev.skyblock.util;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Objects;

@ThreadSafe
public class Pair<K, V> {

    private final Object lock;

    private K type1;
    private V type2;

    public Pair(K type1, V type2) {
        this.lock = new Object();

        this.type1 = type1;
        this.type2 = type2;
    }

    public K getType1() {
        return this.type1;
    }

    public void setType1(K type1) {
        synchronized (this.lock) {
            this.type1 = type1;
        }
    }

    public V getType2() {
        return this.type2;
    }

    public void setType2(V type2) {
        synchronized (this.lock) {
            this.type2 = type2;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(type1, pair.type1) &&
          Objects.equals(type2, pair.type2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type1, type2);
    }

    @Override
    public String toString() {
        return "Pair{" +
          "type1=" + type1 +
          ", type2=" + type2 +
          '}';
    }
}
