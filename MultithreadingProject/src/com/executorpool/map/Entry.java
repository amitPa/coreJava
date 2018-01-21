package com.executorpool.map;

public class Entry<K, V> {

	private final K key;
	private V value;
	private Entry<K, V> next;

	Entry(K key1, V value) {
		this.key = key1;
		this.value = value;
	}

	public void setNext(Entry<K, V> next) {
		this.next = next;
	}

	public Entry<K, V> getNext() {
		return next;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public K getKey() {
		return key;
	}

}
