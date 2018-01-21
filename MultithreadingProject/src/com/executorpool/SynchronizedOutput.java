package com.executorpool;

public class SynchronizedOutput {

	public static void displayList(String name, String[] list) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.length; ++i) {
			SampleClass t = (SampleClass) Thread.currentThread();
			t.randomWait();
			System.out.println(name + list[i]);
		}

	}

}
