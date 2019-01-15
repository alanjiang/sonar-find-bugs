package com.agilean.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class WorkRecord implements Runnable {
	private CountDownLatch countDownLatch;
	private String name;
	public WorkRecord(CountDownLatch countDownLatch, String name) {
		super();
		this.countDownLatch = countDownLatch;
		this.name = name;
	}
	@Override
	public void run() {
		working();
		try {
			 TimeUnit.SECONDS.sleep(new Random().nextInt(10));  
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		this.countDownLatch.countDown();
		System.out.println(">>"+name+" finished the work<<<<");
	}
	
	private void working() {
		
		System.out.println(">>>>"+name+" is working");;
		
	}
	
	
	
	
}
