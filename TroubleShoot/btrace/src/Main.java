public class Main {
    @Override
    public String toString() {
        int a = 0;
        if( 0==a   ){

        }
        return "Main{}";

    }

    /*
        btrace DEBUG: client TraceScript: got com.sun.btrace.comm.MessageCommand@df42a15
        btrace DEBUG: client TraceScript: got com.sun.btrace.comm.MessageCommand@27279627
        btrace DEBUG: client TraceScript: got com.sun.btrace.comm.MessageCommand@1dafc243
        btrace DEBUG: client TraceScript: got com.sun.btrace.comm.MessageCommand@14d9e4fc
        btrace DEBUG: client TraceScript: got com.sun.btrace.comm.MessageCommand@3851d0f0
        result is 5
        * */
    /*
    由于是字节码注入的原理，想要应用恢复到正常情况，需要重启应用。
    * */
    public static void main(String[] args) {

        Calc calc = new Calc();
        while(true) {
            int result = calc.add(1, 4);
            System.out.println("result is " + result);
            try {
                Thread.sleep(1000);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
