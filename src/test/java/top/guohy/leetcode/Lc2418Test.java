package top.guohy.leetcode;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * <a href="https://leetcode.cn/problems/sort-the-people/">2418. 按身高排序</a>
 */
@TestInstance(Lifecycle.PER_CLASS)
public class Lc2418Test {

    @Test
    void test() {
        // 获取英文名字集合
        Set<String> nameSet = new HashSet<>(1500);
        String path = this.getClass().getClassLoader().getResource("names.txt").getPath();
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(path)))
        ) {
            String line = null;
            while (null != (line = reader.readLine()) && line.length() > 0) {
                nameSet.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 名字集合转为数组
        int nameNum = nameSet.size();
        String[] nameArray = new String[nameNum];
        int i = -1;
        for (String name : nameSet) {
            nameArray[++i] = name;
        }

        for (int n = 0; n < 5; ++n) {
            int size = RandomUtils.nextInt(100, 501);
            String[] names = new String[size];
            int[] scores = new int[size];
            Set<Integer> usedScores = new HashSet<>(200);
            for (i = 0; i < size; ++i) {
                int score = RandomUtils.nextInt(1, 1001);
                while (usedScores.contains(score)) {
                    score = RandomUtils.nextInt(1, 1001);
                }

                usedScores.add(score);
                scores[i] = score;
                names[i] = nameArray[RandomUtils.nextInt(0, nameNum)];
            }

            String[] result = sortPeople(names, scores);

            System.out.println("#" + n + ":");
            System.out.println(format(names));
            System.out.println(Arrays.toString(scores));
            System.out.println(format(result) + "\n");
        }
    }

    private String[] sortPeople(String[] names, int[] scores) {
        int n = names.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, (a, b) -> scores[b] - scores[a]);
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            res[i] = names[indices[i]];
        }

        return res;
    }

    private String format(String[] names) {
        StringBuilder sb = new StringBuilder("[");
        for (String name : names) {
            sb.append('"').append(name).append("\",");
        }
        sb.deleteCharAt(sb.length() - 1).append(']');

        return sb.toString();
    }

}
