import java.util.ArrayList;
import java.util.List;

public class MemoryLeakDemo {

    /*
    方法1：
    java -Xms128m -Xmx128m -XX:+HeapDumpOnOutOfMemoryError -jar MemoryLeak.jar
    生成dump后，采用jvirsualvm.exe查看和分析
    方法2：
    jmap -dump:format=b,file=javaml.hprof 16289
    手动主动生成dump

    然后用JProfiler来分析，查看OOM的信息或者在未OOM时查看最大对象
    * */
    /*
    ML Demo 1519770
    ML Demo 1519771
    Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
	at MemoryLeakDemo.main(MemoryLeakDemo.java:10)
    * */
    void HeapOOM(){
        List list = new ArrayList<String>();
        int i = 0;
        while(true){
            list.add(new String("liwei"+i));
            i++;
            System.out.println("ML Demo "+i);
        }
    }

    public static void main(String[] args) {

    }
}
