package com.executorpool.map;

public class MyMap<K, V> {

	int DEFAULT_BUCKET_COUNT = 10;

	Entry<K, V>[] buckets;

	public MyMap() {
		buckets = new Entry[DEFAULT_BUCKET_COUNT];
	}

	public MyMap(int capacity) {
		buckets = new Entry[capacity];
	}

	public int bucketIndexForKey(K key) {
		int bucketIndex = key.hashCode() % buckets.length;
		return bucketIndex;
	}

	private void throwIfKeyNull(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key may not be null");
		}
	}

	public V get(K key) {

		throwIfKeyNull(key);
		Entry<K, V> entry = buckets[bucketIndexForKey(key)];
		while (entry != null && !entry.getKey().equals(key)) {
			entry = entry.getNext();
		}
		return entry != null ? entry.getValue() : null;

	}

	public void put(K key, V value) {

		int bucketIndex = bucketIndexForKey(key);
		Entry<K, V> entry = buckets[bucketIndex];

		if (null != entry) {
			boolean done = false;
			while (!done) {
				if (key.equals(entry.getKey())) {
					entry.setValue(value);
					done = true;
				} else if (entry.getNext() == null) {
					entry.setNext(new Entry<K, V>(key, value));
					done = true;
				}
				entry = entry.getNext();
			}
		} else {
			// nothing there at all; just shove the new entry in
			buckets[bucketIndex] = new Entry<K, V>(key, value);
		}
	}
}
