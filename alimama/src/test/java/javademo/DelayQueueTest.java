package javademo;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 一个业务一个DelayQueue队列相当于是DelayQueue = BlockingQueue +PriorityQueue + Delayed个有序的队列+延迟
同一个业务场景，延迟的时间必须要是一样的Delayed，否则会出问题。类似于rocketmq的一个时间定义1 3 5
如果同一个业务，有不同的时间延迟Delayed，这种感觉不太适合，这种场景用时间轮定时器
 * @author zzm
 *
 */
public class DelayQueueTest {
	 public static void main(String[] args) {  
	        // 创建延时队列  
	        DelayQueue<Message> queue = new DelayQueue<Message>();  
	        // 添加延时消息,m1 延时3s  
	        Message m1 = new Message(1, "world", 3000);  
	        // 添加延时消息,m2 延时10s  
	        Message m2 = new Message(2, "hello", 1000);  
	        //将延时消息放到延时队列中
	        queue.offer(m2);  
	        queue.offer(m1);  
	        // 启动消费线程 消费添加到延时队列中的消息，前提是任务到了延期时间 
	        ExecutorService exec = Executors.newFixedThreadPool(1);
	        exec.execute(new Consumer(queue));
	        exec.shutdown();
	    }  
}
