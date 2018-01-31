package thread;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;


/**
 * 线程异常测试
 * @author zzm
 *
 */
public class ThreadTest {
	
	
   public static void main(String[] args) {
	test8();
}
   
   
   
   /**
    * 打印
    */
   public static void test9(){
	   ThreadFactory threadFactory = new ThreadFactoryBuilder()
		        .setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
					public void uncaughtException(Thread t, Throwable e) {
						System.out.println("=="+t.getName()+"e:"+e);
					}
				})
		        .build();
	   java.util.concurrent.ExecutorService executorService= Executors.newSingleThreadExecutor(threadFactory);
	   Future<Object> future = executorService.submit(() -> {
		            throw new RuntimeException("test");
		        });
		
		Object object;
		try {
             //future.get 会触发异常
			 object = future.get();
			 System.out.println("======================"+object);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}
      
   }

   
   /**
    * 打印 future.get(); 会抛出异常，一般要在 线程中处理异常
    */
   public static void test8(){
	   ThreadFactory threadFactory = new ThreadFactoryBuilder()
		        .setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
					public void uncaughtException(Thread t, Throwable e) {
						System.out.println("=="+t.getName()+"e:"+e);
					}
				})
		        .build();
		Future<Object> future = Executors.newSingleThreadExecutor(threadFactory)
		        .submit(() -> {
		            throw new RuntimeException("test");
		        });
		
		Object object;
		try {
             //future.get 会触发异常
			 object = future.get();
			 System.out.println("======================"+object);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}
      
   }

   
   
   /**
    * 打印
    */
   public static void test7(){
	   ThreadFactory threadFactory = new ThreadFactoryBuilder()
		        .setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
					public void uncaughtException(Thread t, Throwable e) {
						System.out.println("=="+t.getName()+"e:"+e);
					}
				})
		        .build();
		Executors.newSingleThreadExecutor(threadFactory)
		        .execute(() -> {
		            throw new RuntimeException("test");
		        });
   }
   
   /**
    * 不打印
    */
   public static void test6(){
	   ThreadFactory threadFactory = new ThreadFactoryBuilder()
		        .setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
					public void uncaughtException(Thread t, Throwable e) {
						System.out.println("=="+t.getName()+"e:"+e);
					}
				})
		        .build();
		Executors.newSingleThreadExecutor(threadFactory)
		        .submit(() -> {
		            throw new RuntimeException("test");
		        });
   }

   
   
   /**
    * 不打日志
    * try {
    result = c.call();
    ran = true;
} catch (Throwable ex) {
    result = null;
    ran = false;
    setException(ex);
}

protected void setException(Throwable t) {
    if (UNSAFE.compareAndSwapInt(this, stateOffset, NEW, COMPLETING)) {
        outcome = t;
        UNSAFE.putOrderedInt(this, stateOffset, EXCEPTIONAL); // final state
        finishCompletion();
    }
}
    */
   public static void test5(){
	   Executors.newSingleThreadExecutor().submit(() -> {
		    throw new RuntimeException("My runtime exception");
		});
	   System.out.println("==========================================");
   }

   /**
    * 打日志
    */
   public static void test4(){
	   Executors.newSingleThreadExecutor().execute(() -> {
		    throw new RuntimeException("My runtime exception");
		});
	   System.out.println("==========================================");
   }

   
   
   public static void test3(){
	   Thread t = new Thread(() -> System.out.println(1 / 0));
	   /*t.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println("=="+t.getName()+"e:"+e);
		}
	});*/
	   t.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("=="+t.getName()+"e:"+e);
			}
		});
	   
	   t.start();
   }
   
   
   public static void test1(){
	   Thread t = new Thread(() -> System.out.println(1 / 0));
	   t.start();
   }

   
   
   public static void test2(){
	   Thread t = new Thread(() -> {
		    try {
		        System.out.println(1 / 0);
		    } catch (Exception e) {
		       e.printStackTrace();
		    }
		});
		t.start();
		}
   
}
