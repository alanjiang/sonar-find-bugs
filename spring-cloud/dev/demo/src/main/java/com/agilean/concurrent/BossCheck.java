package com.agilean.concurrent;

import java.util.concurrent.CountDownLatch;

public class BossCheck implements Runnable {
    private CountDownLatch countDownLatch;
	public BossCheck(CountDownLatch countDownLatch) {
		super();
		this.countDownLatch = countDownLatch;
	}


	@Override
	public void run() {
		 System.out.println("老板正在等所有的工人干完活......");  
		try {
			this.countDownLatch.await();
			//countDownLatch.await();
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("所有的工人干完活啦......");  
	}

}
