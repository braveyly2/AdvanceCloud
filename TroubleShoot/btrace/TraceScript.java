import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class TraceScript {
    /*
    使用方法：btrace -v 1543 TraceScript.java
    * */
    /*
    显示信息：
DEBUG: received com.sun.btrace.comm.MessageCommand@799d4f69
调用堆栈！！
DEBUG: received com.sun.btrace.comm.MessageCommand@49c43f4e
input parameter 1：1
DEBUG: received com.sun.btrace.comm.MessageCommand@290dbf45
input parameter 2：4
DEBUG: received com.sun.btrace.comm.MessageCommand@12028586
return parameter：5
DEBUG: received com.sun.btrace.comm.MessageCommand@17776a8
Calc.add(Calc.java:3)
Main.main(Main.java:7)
    * */
    @OnMethod(
            clazz="Calc",
            method="add",
            location=@Location(Kind.RETURN)
    )
    public static void traceExecute(int a, int b, @Return int sum){
        println("调用堆栈！！");
        println(strcat("input parameter 1：",str(a)));
        println(strcat("input parameter 2：",str(b)));
        println(strcat("return parameter：",str(sum)));
        jstack();
    }

}
