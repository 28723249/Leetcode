package top.guohy.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 协程测试，基于Alibaba dragonwell-8
 *
 * @author guogf
 * @since 2023.06.30 21:29
 */
public class CoroutineDemo {

    private static final ExecutorService THREAD_POOL = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        BlockingQueue<Byte> q1 = new LinkedBlockingQueue<>(), q2 = new LinkedBlockingQueue<>();
        // thread A
        THREAD_POOL.submit(() -> pingPong(q2, q1));
        // thread B
        Future<?> f = THREAD_POOL.submit(() -> pingPong(q1, q2));
        q1.put((byte) 1);
        System.out.println(f.get() + " ms");
    }

    private static long pingPong(BlockingQueue<Byte> in, BlockingQueue<Byte> out) throws Exception {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 1_000_000; i++) {
            out.put(in.take());
        }

        return System.currentTimeMillis() - start;
    }

}
