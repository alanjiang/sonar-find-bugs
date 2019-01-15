package com.agilean.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		CountDownLatch cd=new CountDownLatch(3);
		WorkRecord wr1=new WorkRecord(cd,"Zhangshan");
		WorkRecord wr2=new WorkRecord(cd,"Lisi");
		WorkRecord wr3=new WorkRecord(cd,"Wangwu3");
		
		BossCheck bc=new BossCheck(cd);
		 
		Thread t1=new Thread(wr1);
		Thread t2=new Thread(wr2);
		Thread t3=new Thread(wr3);
		Thread t4=new Thread(bc);
	    ExecutorService executor = Executors.newCachedThreadPool(); 
		executor.execute(t1);
		executor.execute(t2);
		executor.execute(t3);
		executor.execute(t4);
		//t1.start();
		//t2.start();
		//t3.start();
		//t4.start();
		
		executor.shutdown();  
	}

}
