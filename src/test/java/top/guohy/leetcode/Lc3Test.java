package top.guohy.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * <a href="https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/">3. 无重复字符的最长子串</a>
 */
@TestInstance(Lifecycle.PER_CLASS)
public class Lc3Test {

    @Test
    void test() {
        int maxLen = lengthOfLongestSubstring("abcabcbb");
        assertEquals(3, maxLen);
        System.out.println("max substring length: " + maxLen);
    }

    /**
     * 无重复字符的最长子串
     */
    private int lengthOfLongestSubstring(String s) {
        int length;
        if (null == s || (length = s.length()) == 0) {
            return 0;
        }

        // 预估Map大小，避免重新分配内存
        int size = length < 3 ? length + 1 : (int) ((float) length / 0.75f + 1.0f);
        Map<Character, Integer> map = new HashMap<>(size);

        int maxLen = 0, maxBegin = 0;
        int begin, end;
        for (begin = end = 0; end < length; ++end) {
            final char ch = s.charAt(end);
            // 记录字符在字符串中的位置
            Integer idx = map.get(ch);
            if (null != idx) {
                // 出现重复字符
                int len = end - begin;
                // 记录当前最长子串的长度和开始位置
                if (maxLen < len) {
                    maxLen = len;
                    maxBegin = begin;
                }

                for (int i = begin; i < idx; ++i) {
                    // 因为会从重复字符之后的位置重新开始计算新的子串，所以清除重复字符之前的字符计数
                    map.put(s.charAt(i), null);
                }

                // 设置新的子串开始位置为重复字符的位置+1
                begin = idx + 1;
            }

            // 记录当前字符位置
            map.put(ch, end);
        }

        // 检查最后一个子串长度
        int len = end - begin;
        // 记录当前最长子串的长度和开始位置
        if (maxLen < len) {
            maxLen = len;
            maxBegin = begin;
        }

        return maxLen;
    }

}
