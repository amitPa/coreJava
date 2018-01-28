package com.executorpool.concurrency;

public final class Segment<K, V> {

	private final MyMap<K, V> map;

	public Segment() {
		this.map = new MyMap<K, V>();
	}

	public int getSize() {
		return map.getSize();
	}

	public MyMap<K, V> getMap() {
		return map;
	}

}
