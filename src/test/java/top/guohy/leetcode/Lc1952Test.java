package top.guohy.leetcode;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * <a href="https://leetcode.cn/problems/three-divisors/">1952. 三除数</a>
 */
@TestInstance(Lifecycle.PER_CLASS)
public class Lc1952Test {

    @Test
    void test() {
        for (int j = 0; j < 10; ++j) {
            int n = RandomUtils.nextInt(1, 100_001);
            System.out.println(n + ", " + isThree(n));
        }
    }

    public boolean isThree(int n) {
        int cnt = 0;
        int maxNum = (int) Math.pow(n, 0.5);
        for (int x = 1; x < maxNum; ++x) {
            if (n % x == 0) {
                cnt += 2;
                if (cnt > 3) {
                    break;
                }
            }
        }

        if (maxNum * maxNum == n) {
            ++cnt;
        }

        return cnt == 3;
    }

}
