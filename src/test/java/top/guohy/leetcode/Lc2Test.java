package top.guohy.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * 2. 两数相加
 *
 * https://leetcode-cn.com/problems/add-two-numbers/
 */
@TestInstance(Lifecycle.PER_CLASS)
public class Lc2Test {

    @Test
    void test() {
        int n1 = RandomUtils.nextInt(0, 1_000_000);
        int n2 = RandomUtils.nextInt(0, 1_000_000);

        ListNode result = addTwoNumbers(toListNode(n1), toListNode(n2));
        ListNode expected = toListNode(n1 + n2);

        ListNode p1, p2;
        for (p1 = expected, p2 = result; p1 != null && p2 != null; p1 = p1.next, p2 = p2.next) {
            assertEquals(p1.val, p2.val);
        }

        assertTrue(null == p1 && null == p2);

        System.out.println(String.format("n1 = %d, n2 = %d, result = %d", n1, n2, n1 + n2));
    }

    private ListNode toListNode(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("输入参数不能 < 0: n = " + n);
        }

        // 把n以十进制形式转换为数字(0-9)数组，低位在前
        List<Integer> nums = new ArrayList<>(15);
        if (0 == n) {
            nums.add(0);
        } else {
            while (n > 0) {
                nums.add(n % 10);
                n /= 10;
            }
        }

        ListNode head = new ListNode(nums.get(0));
        ListNode p = head;
        for (int i = 1; i < nums.size(); ++i) {
            p.next = new ListNode(nums.get(i));
            p = p.next;
        }

        return head;
    }

    ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        }

        if (null == l2) {
            return l1;
        }

        // 结果均向l1上累加
        ListNode p1, p2;
        for (p1 = l1, p2 = l2; p1 != null && p2 != null; p1 = p1.next, p2 = p2.next) {
            p1.val += p2.val;
            // 当前位之和<10
            if (p1.val < 10) {
                if (p1.next == null) {
                    p1.next = p2.next;
                    p1 = p1.next;
                    break;
                }

                continue;
            }

            //  当前位之和>=10，保留余数，高位进1
            p1.val -= 10;
            if (p1.next != null) {
                ++p1.next.val;
            } else {
                if (p2.next == null) {
                    p1.next = new ListNode(1);
                    return l1;
                } else {
                    p1.next = p2.next;
                    ++p1.next.val;
                    p1 = p1.next;
                    break;
                }
            }
        }

        for (; p1 != null; p1 = p1.next) {
            if (p1.val < 10) {
                continue;
            }

            p1.val -= 10;
            if (p1.next != null) {
                ++p1.next.val;
            } else {
                p1.next = new ListNode(1);
            }
        }

        return l1;
    }

    private static class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
