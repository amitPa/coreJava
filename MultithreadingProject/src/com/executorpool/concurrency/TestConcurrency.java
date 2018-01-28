package com.executorpool.concurrency;

public class TestConcurrency {

	public static void main(String args[]) {

		ConcurrentHashMap<String, String> cHashMap = new ConcurrentHashMap();
		cHashMap.put("Amit", "Amit");
		cHashMap.put("Amit1", "Amit");
		cHashMap.put("Amit2", "Amit");
		cHashMap.put("amit", "Amitqq");

		System.out.println(cHashMap.get("Amit") + " , " + cHashMap.get("Amit1") + "," + cHashMap.get("Amit2") + ","
				+ cHashMap.get("amit"));
		
		Thread t1 = new Thread() {
			public void run() {
				cHashMap.put("goo1", "goose2");
				cHashMap.put("goo2", "goose2");
				cHashMap.put("goo3", "goose2");
				cHashMap.put("goo4", "goose2");
				cHashMap.put("goo5", "goose2");

			}
		};

		Thread t2 = new Thread() {
			public void run() {

				cHashMap.put("goo03", "goose2");
				cHashMap.put("goo04", "goose2");
				cHashMap.put("goo05", "goose2");
				cHashMap.put("goo06", "goose2");
				cHashMap.put("goo07", "goose2");
				cHashMap.put("goo08", "goose2");
				cHashMap.put("amit0", "amit2");
				cHashMap.get("amit0");
			}
		};

		t1.run();
		t2.run();
		 try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 System.out.println(cHashMap.get("goo5")+" , " +cHashMap.get("goo08"));
		 System.out.println(cHashMap.getSize());
	}

}
