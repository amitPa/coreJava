package com.executorpool.concurrency;

import java.util.Arrays;

public class MyMap<K, V> {

	int DEFAULT_BUCKET_COUNT = 10;

	Entry<K, V>[] buckets;

	int size;

	public MyMap() {
		buckets = new Entry[DEFAULT_BUCKET_COUNT];
		// this.capacity = DEFAULT_BUCKET_COUNT;
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

			checkSize();
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
			this.size++;

	}

	public int getSize() {
		return this.size;
	}

	private void checkSize() {

		int reSize = 0;
		if (this.size > 0 && this.size == buckets.length) {
			Entry<K, V>[] bucketsNew;
			reSize = buckets.length * 2;
			bucketsNew = new Entry[reSize];

			for (Entry<K, V> entryObj : buckets) {
				if (entryObj != null) {
					K key = entryObj.getKey();
					int bucketIndex = key.hashCode() % reSize;
					bucketsNew[bucketIndex] = entryObj;
				}
			}
			buckets = null;
			buckets = bucketsNew;
		}
	}

	public static void main(String args[]) throws InterruptedException {
		MyMap<String, String> map = new MyMap<String, String>();
//		map.put("goo03", "goose2");
//		map.put("goo04", "goose2");
//		map.put("goo05", "goose2");
//		map.put("goo06", "goose2");
//		map.put("goo07", "goose2");
//		map.put("goo08", "goose2");
//		map.put("amit0", "amit2");
//		map.get("amit0");
//		map.get("duck");
//		map.put("duck", "duck1");
//		map.put("goose", "goose");
//		map.put("goo", "goose2");
//		map.put("goo1", "goose2");
//		map.put("goo2", "goose2");
//		map.put("goo3", "goose2");
//		map.put("goo4", "goose2");
//		map.put("goo5", "goose2");
//		map.put("goo6", "goose2");
//		map.put("goo7", "goose2");
//		map.put("goo8", "goose2");
//		map.put("amit", "amit2");
//		map.put("goose", "goose");
//		map.put("goo0", "goose2");
//		map.put("goo01", "goose2");
//		map.put("goo02", "goose2");
//		map.get("amit0");
//		map.get("duck");

		Thread t1 = new Thread() {
			public void run() {
				map.put("goo1", "goose2");
				map.put("goo2", "goose2");
				map.put("goo3", "goose2");
				map.put("goo4", "goose2");
				map.put("goo5", "goose2");

			}
		};

		Thread t2 = new Thread() {
			public void run() {

				map.put("goo03", "goose2");
				map.put("goo04", "goose2");
				map.put("goo05", "goose2");
				map.put("goo06", "goose2");
				map.put("goo07", "goose2");
				map.put("goo08", "goose2");
				map.put("amit0", "amit2");
				map.get("amit0");
			}
		};

		// t1.join();
		// t2.join();

		System.out.println(map.get("goo1") + " , " + map.get("goo2") + "," + map.get("goo2") + "," + map.get("amit"));
		System.out.println(map.getSize() + " , " + "thread1");

	}

}
