package com.executorpool.concurrency;

public class ConcurrentHashMap<K, V> {
	@SuppressWarnings("unused")
	private Segment<K, V>[] segments;

	int size;

	private int DEFAULT_MAP_CAPACITY = 32;

	@SuppressWarnings("unchecked")
	public ConcurrentHashMap() {
		this.segments = new Segment[DEFAULT_MAP_CAPACITY];
	}

	@SuppressWarnings("unchecked")
	public ConcurrentHashMap(int segmentCapacity, int hashMapCapacity) {
		this.segments = new Segment[segmentCapacity];
		this.DEFAULT_MAP_CAPACITY = segmentCapacity;
	}

	private void throwIfKeyNull(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key may not be null");
		}
	}

	public int getSize() {
		int size = 0;

		for (Segment<?, ?> seg : segments) {
			if (seg != null)
				size = size + seg.getSize();
		}

		return size;
	}

	public void put(K key, V value) {
		int segmentIndex = key.hashCode() % DEFAULT_MAP_CAPACITY;
		Segment<K, V> seg = segments[segmentIndex];
		if (seg == null) {
			seg = new Segment();
			segments[segmentIndex] = seg;
		}
		synchronized (seg) {
			MyMap<K, V> map = seg.getMap();
			map.put(key, value);
		}

	}

	public V get(K key) {
		int segmentIndex = key.hashCode() % DEFAULT_MAP_CAPACITY;
		Segment<K, V> seg = segments[segmentIndex];
		if (seg == null) {
			return null;
		}
		MyMap<K, V> map = seg.getMap();
		return map.get(key);

	}
}
