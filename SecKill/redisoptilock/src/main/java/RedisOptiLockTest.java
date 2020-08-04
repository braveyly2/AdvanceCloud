import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisOptiLockTest {
    public static int prdNum = 100;
    public static int clientNum = 10000;
    public static String prdNumKey = "prdNum";
    public static String clientListKey = "clientList";
    public static JedisPool jedispool = null;

    public static void main(String[] args) throws Exception{
        long startTime = System.currentTimeMillis();
        System.out.println("hello world!");
        //jedispool = new JedisPool("127.0.0.1:6379");
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        jedispool = new JedisPool(config,"127.0.0.1",6379,1000,"123456",false);

        //JedisPool(GenericObjectPoolConfig poolConfig, String host, int port, int timeout, String password, boolean ssl)
        Thread.sleep(1000);
        initProduct();
        startSecKill();
        printResult();
        long endTime = System.currentTimeMillis();
        System.out.println("consume time is " + (endTime-startTime));
    }

    public static void initProduct(){
        Jedis jedis = jedispool.getResource();
        if(jedis.exists(prdNumKey)){
            jedis.del(prdNumKey);
        }

        if(jedis.exists(clientListKey)){
            jedis.del(clientListKey);
        }

        jedis.set(prdNumKey,String.valueOf(prdNum));
        jedis.close();
    }

    public static void startSecKill(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for(int i=0; i<clientNum; i++){
            cachedThreadPool.execute(new ClientThread(i));
        }
        cachedThreadPool.shutdown();

        while(true){
            if(cachedThreadPool.isTerminated()){
                System.out.println("all threads in pool is end");
                break;
            }

            try{
                Thread.sleep(1000);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void printResult(){
        Jedis jedis = jedispool.getResource();
        Set<String> clientList = jedis.smembers(clientListKey);
        int i = 1;
        for(String client : clientList){
            System.out.println("The " + i +" client name to succeed:" + client );
            i++;
        }
    }
}


class ClientThread implements Runnable{
    private Jedis jedis = null;
    private String clientName;

    public ClientThread(int i){
        clientName="No."+i;
    }

    public void run(){
        try{
            Thread.sleep((int)(Math.random())*1000);
        }catch(Exception e){
            e.printStackTrace();
        }

        while(true){
            String key = RedisOptiLockTest.prdNumKey;
            jedis = RedisOptiLockTest.jedispool.getResource();
            try {
                jedis.watch(key);
                int prdNum = Integer.parseInt(jedis.get(key));
                if (prdNum > 0) {
                    Transaction transaction = jedis.multi();
                    transaction.set(key, String.valueOf(prdNum-1));
                    List<Object> result = transaction.exec();
                    if (null == result || result.isEmpty()) {
                        System.out.println("client faild to sec kill");
                    } else {
                        jedis.sadd(RedisOptiLockTest.clientListKey, clientName);
                        System.out.println("Congratuation! Client succeed to sec kill");
                        break;
                    }
                } else {
                    System.out.println("product num is 0");
                    break;
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                jedis.unwatch();
                jedis.close();
            }
        }
    }

}