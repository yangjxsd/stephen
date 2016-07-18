package com.stephen.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ThreadPoolDemo {

	private int count = 0;
	
	@Before
	public void setUp() throws Exception {
		count = 200000;
	}
	/**
	 * 方法必须是public的，否则会初始化失败
	 */
	@Test
	public void createThreadPool(){
		
		System.out.println("TheardPool");
		long startTime = System.currentTimeMillis();
		final List<Integer> l = new LinkedList<Integer>();
		/**
		 * ThreadPoolExecutor 线程池 构造方法 
		 * int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue)
		 * 核心线程池大小   最大线程池大小 存活时间 时间单位 阻塞任务队列
		 * 线程池中超过corePoolSize数目的空闲线程最大存活时间；可以allowCoreThreadTimeOut(true)使得核心线程有效时间
		 * 阻塞任务队列
		 *  1.当线程池小于corePoolSize时，新提交任务将创建一个新线程执行任务，即使此时线程池中存在空闲线程。 
			2.当线程池达到corePoolSize时，新提交任务将被放入workQueue中，等待线程池中任务调度执行 
			3.当workQueue已满，且maximumPoolSize>corePoolSize时，新提交任务会创建新线程执行任务 
			4.当提交任务数超过maximumPoolSize时，新提交任务由RejectedExecutionHandler处理 
			5.当线程池中超过corePoolSize线程，空闲时间达到keepAliveTime时，关闭空闲线程 
			6.当设置allowCoreThreadTimeOut(true)时，线程池中corePoolSize线程空闲时间达到keepAliveTime也将关闭
			
		 */
		ThreadPoolExecutor tp = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(count));
		final Random random = new Random();
		for(int i = 0; i < count; i++){
			tp.execute(new Runnable() {
				@Override
				public void run() {
					l.add(random.nextInt());
				}
			});
		}
		tp.shutdown();
		try {
			tp.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
		System.out.println(l.size());
		
	}
	
	@Test
	public void createThreadNoPool(){
		System.out.println("TheardNoPool");
		long startTime = System.currentTimeMillis();
		final List<Integer> l = new LinkedList<Integer>();
		final Random random = new Random();
		for(int i = 0;i<count;i++){
			Thread thread = new Thread(){
				public void run() {
					l.add(random.nextInt());
				};
			};
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
		System.out.println(l.size());
	}
}
