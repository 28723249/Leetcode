package top.guohy.coroutine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * 协程测试
 */
@TestInstance(Lifecycle.PER_CLASS)
public class CoroutineTest {

    final ExecutorService THREAD_POOL = Executors.newCachedThreadPool();

    @Test
    void test() throws Exception {
        BlockingQueue<Byte> q1 = new LinkedBlockingQueue<>(), q2 = new LinkedBlockingQueue<>();
        // thread A
        THREAD_POOL.submit(() -> pingPong(q2, q1));
        // thread B
        Future<?> f = THREAD_POOL.submit(() -> pingPong(q1, q2));
        q1.put((byte) 1);
        System.out.println(f.get() + " ms");
    }

    private long pingPong(BlockingQueue<Byte> in, BlockingQueue<Byte> out) throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            out.put(in.take());
        }
        return System.currentTimeMillis() - start;
    }

}
