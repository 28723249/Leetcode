package top.guohy.leetcode;

import java.util.Arrays;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * <a href="https://leetcode.cn/problems/minimum-operations-to-make-array-equal-ii/">2541. 使数组中所有元素相等的最小操作数 II</a>
 */
@TestInstance(Lifecycle.PER_CLASS)
public class Lc2541Test {

    @Test
    void test() {
        for (int j = 0; j < 10; ++j) {
            int k = RandomUtils.nextInt(1, 10_001);
            int n = RandomUtils.nextInt(2, 10001);
            int[] num1 = new int[n];
            int[] num2 = new int[n];

            if (j % 2 == 0) {
                for (int i = 0; i < n; ++i) {
                    num1[i] = RandomUtils.nextInt(0, 1_000_001);
                    num2[i] = RandomUtils.nextInt(0, 1_000_001);
                }
            } else {
                for (int i = 0; i < n - 1; i += 2) {
                    num1[i] = RandomUtils.nextInt(0, 1_000_001);

                    int r = RandomUtils.nextInt(0, 50);
                    num2[i] = num1[i] + k * r;
                    num2[i + 1] = num1[i + 1] - k * r;
                }

                num2[n - 1] = num1[n - 1];
            }

            long result = minOperations(num1, num2, k);

            System.out.printf("#%d, result: %d\nnum1: %s\nnum2: %s\nk: %d\n\n",
                j, result, Arrays.toString(num1), Arrays.toString(num2), k);
        }
    }

    public long minOperations(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;

        if (k == 0) {
            boolean same = true;
            for (int i = 0; i < n; i++) {
                if (nums1[i] != nums2[i]) {
                    same = false;
                    break;
                }
            }
            return (same == true ? 0 : -1);
        }

        long add_cnt = 0L;
        long sub_cnt = 0L;
        for (int i = 0; i < n; i++) {
            int diff = nums1[i] - nums2[i];
            if (diff % k != 0) {
                return -1;
            }
            if (diff > 0) {
                add_cnt += diff / k;
            } else {
                sub_cnt += diff / k;
            }
        }

        long res = (add_cnt + sub_cnt == 0 ? add_cnt : -1);
        return res;
    }

}
