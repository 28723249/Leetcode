package top.guohy.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * 在一个三维直角坐标系中，有一只蚂蚁从原点 (0, 0, 0) 开始，向目标点 (l, m, n) 前进，蚂蚁的前进规则如下：
 * 1、l, m, n >= 0，且 l + m + n > 0；
 * 2、蚂蚁每次只能沿X/Y/Z轴的方向前进一个单位，比如，当前蚂蚁在点(x0, y0, z0)，下一步只能前进到下面三个点中的任意一个：(x0 + 1, y0, z0)，或(x0, y0 + 1, z0)，或(x0, y0, z0 + 1)。
 *
 * 返回所有可以从原点 (0, 0, 0) 到目标点 (l, m, n) 的可行路径数量。
 * 说明：代码中所有的数值最大不会超过Long.MAX_VALUE。
 *
 * @author guoguanfei
 * @date 22.6.22 19:31
 */
@TestInstance(Lifecycle.PER_CLASS)
public class CountPathsTest {

    @Test
    void testCountPaths() {
        for (int i = 0; i < 10; ++i) {
            int l = RandomUtils.nextInt(5, 10);
            int m = RandomUtils.nextInt(5, 10);
            int n = RandomUtils.nextInt(5, 10);
            System.out.printf("%s: (%d, %d, %d)\n", LocalDateTime.now(), l, m, n);

            long t1 = System.currentTimeMillis();
            long num1 = countPaths(l, m, n);
            long t2 = System.currentTimeMillis();
            System.out.printf("\tcountPaths:            %d ms, num1 = %d\n", t2 - t1, num1);
            long t3 = System.currentTimeMillis();
            long num2 = countPathsRecursively(m, n, l);
            long t4 = System.currentTimeMillis();
            System.out.printf("\tcountPathsRecursively: %d ms, num2 = %d\n", t4 - t3, num2);

            assertEquals(num1, num2);
        }
    }

    /**
     * 使用组合数计算所有可行路径的数量
     *
     * @author guoguanfei
     * @date 22.6.22 19:36
     *
     * @param x 目标点的 X 坐标
     * @param y 目标点的 Y 坐标
     * @param z 目标点的 Z 坐标
     * @return 可行路径的数量
     */
    long countPaths(int x, int y, int z) {
        return cnr(x + y + z, x) * cnr(y + z, y);
    }

    /**
     * 计算组合数，即C(m, n)：
     *     从m个不同的整数中，取出n个不同的整数，总的取法数量
     *
     * @author guoguanfei
     * @date 22.6.22 19:38
     *
     * @param m 整数，≥ n
     * @param n 整数，≥ 0
     * @return 组合数
     */
    long cnr(int m, int n) {
        if (n <= 0 || m <= n) {
            return 1;
        }

        long result = m;
        int r = m - n;
        if (r < n) {
            n = r;
        }

        for (int i = 1; i < n; ++i) {
            result *= (m - i);
        }

        for (int i = n; i > 1; --i) {
            result /= i;
        }

        return result;
    }

    /**
     * 递归计算所有可行路径的数量
     *
     * @author guoguanfei
     * @date 22.6.22 19:36
     *
     * @param x 目标点的 X 坐标
     * @param y 目标点的 Y 坐标
     * @param z 目标点的 Z 坐标
     * @return 可行路径的数量
     */
    long countPathsRecursively(int x, int y, int z) {
        // 目标点在坐标轴上，直接返回1
        if (0 == x && (0 == y || 0 == z)) {
            return 1;
        }

        if (0 == y && 0 == z) {
            return 1;
        }

        if (0 == x) {
            return countPathsRecursively(0, y - 1, z) + countPathsRecursively(0, y, z - 1);
        }

        if (0 == y) {
            return countPathsRecursively(x - 1, 0, z) + countPathsRecursively(x, 0, z - 1);
        }

        if (0 == z) {
            return countPathsRecursively(x - 1, y, 0) + countPathsRecursively(x, y - 1, 0);
        }

        return countPathsRecursively(x - 1, y, z) + countPathsRecursively(x, y - 1, z)
            + countPathsRecursively(x, y, z - 1);
    }

}
