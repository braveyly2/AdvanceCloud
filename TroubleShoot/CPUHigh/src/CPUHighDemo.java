public class CPUHighDemo {
/*
 方法：
 1、top 获取占CPU高的进程pid
 2、top -H -p pid获取占CPU高的线程tid
 3、printf "%x\n" tid   得到nid(native id)
 4、在线程堆栈中查询nid
 jstack pid | grep 'nid'
* */
/*
"Thread-0" #9 prio=5 os_prio=0 tid=0x00007f3038243000 nid=0x7e5e runnable [0x00007f3022051000]
   java.lang.Thread.State: RUNNABLE
        at java.io.FileOutputStream.writeBytes(Native Method)
        at java.io.FileOutputStream.write(FileOutputStream.java:326)
        at java.io.BufferedOutputStream.flushBuffer(BufferedOutputStream.java:82)
        at java.io.BufferedOutputStream.flush(BufferedOutputStream.java:140)
        - locked <0x00000000e0c13bd0> (a java.io.BufferedOutputStream)
        at java.io.PrintStream.write(PrintStream.java:482)
        - locked <0x00000000e0c04b30> (a java.io.PrintStream)
        at sun.nio.cs.StreamEncoder.writeBytes(StreamEncoder.java:221)
        at sun.nio.cs.StreamEncoder.implFlushBuffer(StreamEncoder.java:291)
        at sun.nio.cs.StreamEncoder.flushBuffer(StreamEncoder.java:104)
        - locked <0x00000000e0c04ae8> (a java.io.OutputStreamWriter)
        at java.io.OutputStreamWriter.flushBuffer(OutputStreamWriter.java:185)
        at java.io.PrintStream.newLine(PrintStream.java:546)
        - eliminated <0x00000000e0c04b30> (a java.io.PrintStream)
        at java.io.PrintStream.println(PrintStream.java:807)
        - locked <0x00000000e0c04b30> (a java.io.PrintStream)
        at CPUHighDemo$1.run(CPUHighDemo.java:20) //////这行看出调用
        at java.lang.Thread.run(Thread.java:748)

* */
    public static void main(String[] args) {

        System.out.println("Hello World!");
        Thread thread1 = new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    Thread.sleep(1000);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                while(true) {
                    try {
                        //Thread.sleep(10);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Thread1 ====");
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable(){
            @Override
            public void run(){
                while(true) {
                    try {
                        Thread.sleep(100);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Thread2 ====");
                }
            }
        });
        thread2.start();

        Thread thread3 = new Thread(new Runnable(){
            @Override
            public void run(){
                while(true) {
                    try {
                        Thread.sleep(1000);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Thread3 ====");
                }
            }
        });
        thread3.start();

        try{
            thread1.join();
            thread2.join();
            thread3.join();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }



}
