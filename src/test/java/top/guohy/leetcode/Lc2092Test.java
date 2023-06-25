package top.guohy.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * <a href="https://leetcode.cn/problems/find-all-people-with-secret/">2092. 找出知晓秘密的所有专家</a>
 */
@TestInstance(Lifecycle.PER_CLASS)
public class Lc2092Test {

    @Test
    List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        boolean[] knownSecret = new boolean[n];
        knownSecret[0] = true;
        knownSecret[firstPerson] = true;
        Arrays.sort(meetings, Comparator.comparingInt(p -> p[2]));

        Queue<Integer> queue = new LinkedList<>();
        int m = meetings.length;
        int i = 0;
        while (i < m) {
            int j = i;
            while (j + 1 < m && meetings[i][2] == meetings[j + 1][2]) {
                j++;
            }
            Set<Integer> set = new HashSet<>();
            Map<Integer, List<Integer>> edges = new HashMap<>();
            for (int k = i; k <= j; k++) {
                int u = meetings[k][0];
                int v = meetings[k][1];
                set.add(u);
                set.add(v);
                edges.computeIfAbsent(u, key -> new ArrayList<>()).add(v);
                edges.computeIfAbsent(v, key -> new ArrayList<>()).add(u);
            }
            for (Integer x : set) {
                if (knownSecret[x]) {
                    queue.add(x);
                }
            }
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (Integer next : edges.get(cur)) {
                    if (!knownSecret[next]) {
                        knownSecret[next] = true;
                        queue.add(next);
                    }
                }
            }
            i = j + 1;
        }

        ArrayList<Integer> ret = new ArrayList<>();
        for (int j = 0; j < knownSecret.length; j++) {
            if (knownSecret[j]) {
                ret.add(j);
            }
        }

        return ret;
    }

}
