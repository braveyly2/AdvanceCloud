public class DeadLockDemo {

    static String A = "A";
    static String B = "B";
    public static void main(String[] args){
        Thread thread1 = new Thread(new Lock1());
        Thread thread2 = new Thread(new Lock2());
        thread1.start();
        thread2.start();
        try{
            thread1.join();
            thread2.join();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

class Lock1 implements Runnable{
    @Override
    public void run(){
        synchronized(DeadLockDemo.A){
            System.out.println("Lock1 enter lock A");
            synchronized (DeadLockDemo.B){
                System.out.println("Lock1 enter lock B");
            }
        }
    }
}

class Lock2 implements Runnable{
    @Override
    public void run(){
        synchronized (DeadLockDemo.B){
            System.out.println("Lock2 enter lock B");
            synchronized (DeadLockDemo.A){
                System.out.println("Lock2 enter lock A");
            }
        }
    }
}
/*
方法：
#jstack pid
*/
/*
输出日志：
2020-12-17 13:03:07
Full thread dump OpenJDK 64-Bit Server VM (25.275-b01 mixed mode):

"Attach Listener" #11 daemon prio=9 os_prio=0 tid=0x00007f8d68001000 nid=0x624c waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Thread-1" #10 prio=5 os_prio=0 tid=0x00007f8d9824c800 nid=0x17c4 waiting for monitor entry [0x00007f8d80762000]
   java.lang.Thread.State: BLOCKED (on object monitor)
	at Lock2.run(DeadLockDemo.java:37)
	- waiting to lock <0x00000000f59f4590> (a java.lang.String)
	- locked <0x00000000f59f45c0> (a java.lang.String)
	at java.lang.Thread.run(Thread.java:748)

"Thread-0" #9 prio=5 os_prio=0 tid=0x00007f8d9824b000 nid=0x17c3 waiting for monitor entry [0x00007f8d80863000]
   java.lang.Thread.State: BLOCKED (on object monitor)
	at Lock1.run(DeadLockDemo.java:25)
	- waiting to lock <0x00000000f59f45c0> (a java.lang.String)
	- locked <0x00000000f59f4590> (a java.lang.String)
	at java.lang.Thread.run(Thread.java:748)

"Service Thread" #8 daemon prio=9 os_prio=0 tid=0x00007f8d98191800 nid=0x17c1 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C1 CompilerThread2" #7 daemon prio=9 os_prio=0 tid=0x00007f8d98186000 nid=0x17c0 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread1" #6 daemon prio=9 os_prio=0 tid=0x00007f8d98184800 nid=0x17bf waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" #5 daemon prio=9 os_prio=0 tid=0x00007f8d98181800 nid=0x17be waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Signal Dispatcher" #4 daemon prio=9 os_prio=0 tid=0x00007f8d9817e800 nid=0x17bd runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" #3 daemon prio=8 os_prio=0 tid=0x00007f8d9814e000 nid=0x17bc in Object.wait() [0x00007f8d8152e000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000000f5988ee0> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:144)
	- locked <0x00000000f5988ee0> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:165)
	at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:216)

"Reference Handler" #2 daemon prio=10 os_prio=0 tid=0x00007f8d98149800 nid=0x17bb in Object.wait() [0x00007f8d8162f000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000000f5986c00> (a java.lang.ref.Reference$Lock)
	at java.lang.Object.wait(Object.java:502)
	at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
	- locked <0x00000000f5986c00> (a java.lang.ref.Reference$Lock)
	at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

"main" #1 prio=5 os_prio=0 tid=0x00007f8d9800a800 nid=0x17b5 in Object.wait() [0x00007f8d9f290000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000000f59f4780> (a java.lang.Thread)
	at java.lang.Thread.join(Thread.java:1252)
	- locked <0x00000000f59f4780> (a java.lang.Thread)
	at java.lang.Thread.join(Thread.java:1326)
	at DeadLockDemo.main(DeadLockDemo.java:11)

"VM Thread" os_prio=0 tid=0x00007f8d98140000 nid=0x17ba runnable

"GC task thread#0 (ParallelGC)" os_prio=0 tid=0x00007f8d98020000 nid=0x17b6 runnable

"GC task thread#1 (ParallelGC)" os_prio=0 tid=0x00007f8d98021800 nid=0x17b7 runnable

"GC task thread#2 (ParallelGC)" os_prio=0 tid=0x00007f8d98023800 nid=0x17b8 runnable

"GC task thread#3 (ParallelGC)" os_prio=0 tid=0x00007f8d98025800 nid=0x17b9 runnable

"VM Periodic Task Thread" os_prio=0 tid=0x00007f8d98194000 nid=0x17c2 waiting on condition

JNI global references: 5


Found one Java-level deadlock:
=============================
"Thread-1":
  waiting to lock monitor 0x00007f8d70006218 (object 0x00000000f59f4590, a java.lang.String),
  which is held by "Thread-0"
"Thread-0":
  waiting to lock monitor 0x00007f8d50002338 (object 0x00000000f59f45c0, a java.lang.String),
  which is held by "Thread-1"

Java stack information for the threads listed above:
===================================================
"Thread-1":
	at Lock2.run(DeadLockDemo.java:37)
	- waiting to lock <0x00000000f59f4590> (a java.lang.String)
	- locked <0x00000000f59f45c0> (a java.lang.String)
	at java.lang.Thread.run(Thread.java:748)
"Thread-0":
	at Lock1.run(DeadLockDemo.java:25)
	- waiting to lock <0x00000000f59f45c0> (a java.lang.String)
	- locked <0x00000000f59f4590> (a java.lang.String)
	at java.lang.Thread.run(Thread.java:748)

Found 1 deadlock.

* */