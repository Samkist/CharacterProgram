package me.Samkist.Character.SamUtil;

import java.util.Map;

public class SamKV<K, V> implements Map.Entry<K, V> {

    private K key;
    private V value;

    @SuppressWarnings("unchecked")
    public SamKV(Object k, Object v) {
        this.key = (K) k;
        this.value = (V) v;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public V setValue(V value) {
        return this.value = value;
    }
}
